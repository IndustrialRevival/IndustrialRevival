package org.irmc.industrialrevival.core.services;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.ProfiledBlock;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.PerformanceSummary;
import org.irmc.industrialrevival.api.objects.TimingViewRequest;
import org.irmc.industrialrevival.core.task.TickerTask;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.NumberUtils;
import org.irmc.industrialrevival.utils.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("unused")
public class ProfilerService {
    private static final int MAX_ITEMS = 20;
    public final Queue<TimingViewRequest> requests = new ConcurrentLinkedQueue<>();
    public final Map<ProfiledBlock, Long> profilingData = new ConcurrentHashMap<>();
    public final Map<Location, Long> startTimes = new ConcurrentHashMap<>();
    @Getter
    private final TickerTask task = new TickerTask(IndustrialRevival.getInstance().getConfig().getInt("options.armor-check-interval", 1));
    @Getter
    public PerformanceSummary summary = new PerformanceSummary(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), 0);

    public void requestTimingView(TimingViewRequest request) {
        if (!requests.add(request)) {
            throw new RuntimeException("Failed to add request to queue");
        }
    }

    @Nullable
    public TimingViewRequest pullTimingViewRequest() {
        if (requests.isEmpty()) {
            return null;
        }

        TimingViewRequest request = requests.poll();
        while (request == null || request.getRequester() == null) {
            if (requests.isEmpty()) {
                return null;
            }
            request = requests.poll();
        }

        return request;
    }

    @SuppressWarnings("deprecation")
    @NotNull
    @ParametersAreNonnullByDefault
    private TextComponent getHoverComponent(String groundText, String backGroundText) {
        TextComponent hoverComponent = new TextComponent(groundText);
        hoverComponent.setColor(ChatColor.GRAY);

        Content content = new Text(TextComponent.fromLegacyText(backGroundText));
        hoverComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, content));

        return hoverComponent;
    }

    public void respondToTimingView(@Nullable TimingViewRequest request) {
        if (request == null) {
            return;
        }

        Map<ProfiledBlock, Long> data = getProfilingData();
        Map<String, Long> dataByID = getProfilingDataByID();
        Map<ChunkPosition, Long> dataByChunk = getProfilingDataByChunk();
        Map<String, Long> dataByPlugin = getProfilingDataByPlugin();
        long tt = dataByChunk.values().stream().mapToLong(Long::longValue).sum();
        this.summary = new PerformanceSummary(data, dataByID, dataByChunk, dataByPlugin, tt);

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
        request.getRequester().sendMessage("&aTotal time: " + NumberUtils.round(NumberUtils.nsToMs(tt), 2));
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
        ProfiledBlock profiledBlock = new ProfiledBlock(location);
        profilingData.put(profiledBlock, timeTaken);
        startTimes.remove(location);
    }

    public void clearProfilingData() {
        profilingData.clear();
        startTimes.clear();
    }

    public Map<ProfiledBlock, Long> getProfilingData() {
        return new ConcurrentHashMap<>(profilingData);
    }

    @NotNull
    public Map<String, Long> getProfilingDataByID() {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<String, Long> profilingDataByID = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            String id = profiledBlock.getItem().getId();
            profilingDataByID.merge(id, profilingData.get(profiledBlock), Long::sum);
        }
        return profilingDataByID;
    }

    @NotNull
    public Map<ChunkPosition, Long> getProfilingDataByChunk() {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<ChunkPosition, Long> profilingDataByChunk = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            ChunkPosition chunkPosition = new ChunkPosition(profiledBlock.getLocation());
            profilingDataByChunk.merge(chunkPosition, profilingData.get(profiledBlock), Long::sum);
        }
        return profilingDataByChunk;
    }

    @NotNull
    public Map<String, Long> getProfilingDataByPlugin() {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<String, Long> profilingDataByPlugin = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            String pluginName = profiledBlock.getPlugin();
            profilingDataByPlugin.merge(pluginName, profilingData.get(profiledBlock), Long::sum);
        }
        return profilingDataByPlugin;
    }

    @NotNull
    public Map<ProfiledBlock, Long> getProfilingDataByID(String id) {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<ProfiledBlock, Long> profilingDataByID = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            if (profiledBlock.getItem().getId().equals(id)) {
                profilingDataByID.put(profiledBlock, profilingData.get(profiledBlock));
            }
        }
        return profilingDataByID;
    }

    @NotNull
    public Map<ProfiledBlock, Long> getProfilingDataByChunk(ChunkPosition chunkPosition) {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<ProfiledBlock, Long> profilingDataByChunk = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            if (new ChunkPosition(profiledBlock.getLocation()).equals(chunkPosition)) {
                profilingDataByChunk.put(profiledBlock, profilingData.get(profiledBlock));
            }
        }
        return profilingDataByChunk;
    }

    @NotNull
    public Map<ProfiledBlock, Long> getProfilingDataByPlugin(String pluginName) {
        Map<ProfiledBlock, Long> profilingData = getProfilingData();
        Map<ProfiledBlock, Long> profilingDataByPlugin = new ConcurrentHashMap<>();
        for (ProfiledBlock profiledBlock : profilingData.keySet()) {
            if (profiledBlock.getPlugin().equals(pluginName)) {
                profilingDataByPlugin.put(profiledBlock, profilingData.get(profiledBlock));
            }
        }
        return profilingDataByPlugin;
    }

    public long getTotalMachine(String id) {
        Map<ProfiledBlock, Long> profiledLocations = getProfilingData();
        long totalAmount = 0;
        for (ProfiledBlock profiledBlock : profiledLocations.keySet()) {
            if (profiledBlock.getItem().getId().equals(id)) {
                totalAmount += profiledLocations.get(profiledBlock);
            }
        }

        return totalAmount;
    }
}
