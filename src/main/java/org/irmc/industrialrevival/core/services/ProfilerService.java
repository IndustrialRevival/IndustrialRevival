package org.irmc.industrialrevival.core.services;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.ProfiledLocation;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.TimingViewRequest;
import org.irmc.industrialrevival.core.task.TickerTask;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProfilerService {
    private static final int MAX_ITEMS = 20;
    public final Queue<TimingViewRequest> requests = new ConcurrentLinkedQueue<>();
    public final Map<ProfiledLocation, Long> profilingData = new ConcurrentHashMap<>();
    public final Map<Location, Long> startTimes = new ConcurrentHashMap<>();
    @Getter
    private final TickerTask task = new TickerTask(IndustrialRevival.getInstance().getConfig().getInt("options.armor-check-interval", 1));
    public void requestTimingView(TimingViewRequest request) {
        if (!requests.add(request)) {
            throw new RuntimeException("Failed to add request to queue");
        }
    }

    @Nullable
    public TimingViewRequest pullTimingViewRequest() {
        return requests.poll();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @ParametersAreNonnullByDefault
    private TextComponent getHoverComponent(String groundText, String backGroundText){
        TextComponent hoverComponent = new TextComponent(groundText);
        hoverComponent.setColor(ChatColor.GRAY);

        Content content = new Text(TextComponent.fromLegacyText(backGroundText));
        hoverComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, content));

        return hoverComponent;
    }
    public void respondToTimingView(TimingViewRequest request) {
        Map<String, Long> dataByID = getProfilingDataByID();
        Map<ChunkPosition, Long> dataByChunk = getProfilingDataByChunk();
        Map<String, Long> dataByPlugin = getProfilingDataByPlugin();

        List<String> sortedID = dataByID.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Long::compareTo))
                .map(Map.Entry::getKey)
                .toList();
        StringBuilder idBuilder = new StringBuilder();
        int got = 0;
        for (String id : sortedID) {
            if (got >= MAX_ITEMS) {
                break;
            }
            long totalTime = dataByID.get(id);
            String idFormat = MessageFormat.format(
                    StringUtil.color("{0} - total: {1} | avg: {2}"),
                    id, totalTime, totalTime / getTotalMachine(id));
            idBuilder.append(idFormat).append("\n");
            got++;
        }

        List<ChunkPosition> sortedChunks = dataByChunk.keySet().stream()
                .sorted(Comparator.comparingLong(dataByChunk::get))
                .toList();

        StringBuilder chunkBuilder = new StringBuilder();
        got = 0;
        for (ChunkPosition chunkPosition : sortedChunks) {
            if (got >= MAX_ITEMS) {
                break;
            }
            long totalTime = dataByChunk.get(chunkPosition);
            String chunkFormat = MessageFormat.format(
                    StringUtil.color("{0} - total: {1}"),
                    chunkPosition.humanize(), totalTime, totalTime / dataByChunk.size());
            chunkBuilder.append(chunkFormat).append("\n");
            got++;
        }

        List<String> sortedPlugins = dataByPlugin.keySet().stream()
                .sorted(Comparator.comparingLong(dataByPlugin::get))
                .toList();
        StringBuilder pluginBuilder = new StringBuilder();
        got = 0;
        for (String pluginName : sortedPlugins) {
            if (got >= MAX_ITEMS) {
                break;
            }
            long totalTime = dataByPlugin.get(pluginName);
            String pluginFormat = MessageFormat.format(
                    StringUtil.color("{0} - total: {1}"),
                    pluginName, totalTime);
            pluginBuilder.append(pluginFormat).append("\n");
            got++;
        }

        request.getRequester().sendMessage("&a====== Profiling Data ======");
        request.getRequester().sendMessage("&aTick count: " + task.getTicked());
        request.getRequester().sendMessage("&aInterval:" + task.getCheckInterval());
        request.getRequester().sendMessage("&aTPS: " + Arrays.toString(Bukkit.getTPS()));
        request.getRequester().sendMessage("&a===== Timing Data =====");
        request.getRequester().sendMessage(getHoverComponent("&a" + dataByID.size() + " Blocks (Hover for details)", idBuilder.toString()));
        request.getRequester().sendMessage(getHoverComponent("&a" + dataByChunk.size() + " Chunks (Hover for details)", chunkBuilder.toString()));
        request.getRequester().sendMessage(getHoverComponent("&a" + dataByPlugin.size() + " Plugins (Hover for details)", pluginBuilder.toString()));
    }
    public void startProfiling(Location location) {
        startTimes.put(location, System.nanoTime());
    }

    public void stopProfiling(Location location) {
        long endTime = System.nanoTime();
        long startTime = startTimes.get(location);
        long timeTaken = endTime - startTime;
        ProfiledLocation profiledLocation = new ProfiledLocation(location);
        profilingData.put(profiledLocation, timeTaken);
        startTimes.remove(location);
    }

    public void clearProfilingData() {
        profilingData.clear();
        startTimes.clear();
    }

    public Map<ProfiledLocation, Long> getProfilingData() {
        return new ConcurrentHashMap<>(profilingData);
    }

    @Nonnull
    public Map<String, Long> getProfilingDataByID() {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<String, Long> profilingDataByID = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            String id = profiledLocation.getItem().getId();
            profilingDataByID.merge(id, profilingData.get(profiledLocation), Long::sum);
        }
        return profilingDataByID;
    }
    @Nonnull
    public Map<ChunkPosition, Long> getProfilingDataByChunk() {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<ChunkPosition, Long> profilingDataByChunk = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            ChunkPosition chunkPosition = new ChunkPosition(profiledLocation.getLocation());
            profilingDataByChunk.merge(chunkPosition, profilingData.get(profiledLocation), Long::sum);
        }
        return profilingDataByChunk;
    }

    @Nonnull
    public Map<String, Long> getProfilingDataByPlugin() {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<String, Long> profilingDataByPlugin = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            String pluginName = profiledLocation.getPlugin();
            profilingDataByPlugin.merge(pluginName, profilingData.get(profiledLocation), Long::sum);
        }
        return profilingDataByPlugin;
    }

    @Nonnull
    public Map<ProfiledLocation, Long> getProfilingDataByID(String id) {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<ProfiledLocation, Long> profilingDataByID = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            if (profiledLocation.getItem().getId().equals(id)) {
                profilingDataByID.put(profiledLocation, profilingData.get(profiledLocation));
            }
        }
        return profilingDataByID;
    }

    @Nonnull
    public Map<ProfiledLocation, Long> getProfilingDataByChunk(ChunkPosition chunkPosition) {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<ProfiledLocation, Long> profilingDataByChunk = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            if (new ChunkPosition(profiledLocation.getLocation()).equals(chunkPosition)) {
                profilingDataByChunk.put(profiledLocation, profilingData.get(profiledLocation));
            }
        }
        return profilingDataByChunk;
    }

    @Nonnull
    public Map<ProfiledLocation, Long> getProfilingDataByPlugin(String pluginName) {
        Map<ProfiledLocation, Long> profilingData = getProfilingData();
        Map<ProfiledLocation, Long> profilingDataByPlugin = new ConcurrentHashMap<>();
        for (ProfiledLocation profiledLocation : profilingData.keySet()) {
            if (profiledLocation.getPlugin().equals(pluginName)) {
                profilingDataByPlugin.put(profiledLocation, profilingData.get(profiledLocation));
            }
        }
        return profilingDataByPlugin;
    }

    public long getTotalMachine(String id) {
        Map<ProfiledLocation, Long> profiledLocations = getProfilingData();
        long totalAmount = 0;
        for (ProfiledLocation profiledLocation : profiledLocations.keySet()) {
            if (profiledLocation.getItem().getId().equals(id)) {
                totalAmount += profiledLocations.get(profiledLocation);
            }
        }

        return totalAmount;
    }
}
