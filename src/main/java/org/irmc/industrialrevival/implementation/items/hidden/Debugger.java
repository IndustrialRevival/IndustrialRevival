package org.irmc.industrialrevival.implementation.items.hidden;

import io.papermc.lib.PaperLib;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.items.handlers.ItemInteractHandler;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.PerformanceSummary;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemInteractEvent;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.NumberUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This item is used for debugging purposes.
 * Usage:
 * - Left-click on a block to break it normally.
 * - Right-click on a non-IR block to see its block state.
 * - Right-click on an IR block to see its properties.
 * - Right-click on air to see the chunk's timings.
 * - Shift+Left-click on a non-IR block to break it forcefully.
 * - Shift+Left-click on an IR block to remove its IR block data.
 * - Shift+Left-click on air to see Industrial Revival's status.
 * - Shift+Right-click on a block to place a Debug Head.
 * - Shift+Right-click on air to see the server's status.
 */
@SuppressWarnings("deprecation")
public class Debugger extends IndustrialRevivalItem {
    private static final String DEBUG_INFO_HEAD = "[IndustrialRevival Debugger]";
    private static final ChatColor red = ChatColor.RED;
    private static final ChatColor green = ChatColor.GREEN;
    private static final ChatColor yellow = ChatColor.YELLOW;
    private static final ChatColor blue = ChatColor.BLUE;
    private static final ChatColor white = ChatColor.WHITE;
    private static final ChatColor gray = ChatColor.GRAY;
    private static final ChatColor darkGray = ChatColor.DARK_GRAY;
    private static final ChatColor black = ChatColor.BLACK;
    public Debugger() {
        super();
        addItemHandlers((ItemInteractHandler) this::interact);
    }

    private void interact(IRItemInteractEvent e) {
        e.setCancelled(true);

        Player player = e.getPlayer();
        if (!player.isOp()) {
            player.sendMessage(red + "You do not have permission to use this item.");
        }

        player.sendMessage(DEBUG_INFO_HEAD);

        boolean isShift = player.isSneaking();
        boolean isRightClick = e.getAction().isRightClick();
        boolean isLeftClick = e.getAction().isLeftClick();
        boolean clickedNormalBlock = false;
        boolean clickedIRBlock = false;
        boolean clickedAir = false;
        Block block = e.getClickedBlock();
        if (block == null) {
            clickedAir = true;
        } else {
            Location location = block.getLocation();
            IRBlockData blockData = DataUtil.getBlockData(location);
            if (blockData != null) {
                clickedIRBlock = true;
            } else {
                clickedNormalBlock = true;
            }
        }

        if (isLeftClick && !isRightClick && !isShift) {
            breakBlock(e);
        }

        if (isRightClick && !isLeftClick && !isShift) {
            if (clickedNormalBlock) {
                seeBlockState(e);
            }
            if (clickedIRBlock) {
                seeIRBlockData(e);
            }
            if (clickedAir) {
                seeChunkTimings(e);
            }
        }

        if (isLeftClick && isShift && !isRightClick) {
            if (clickedNormalBlock) {
                forceBreakBlock(e);
            }
            if (clickedIRBlock) {
                removeIRBlockData(e);
            }
            if (clickedAir) {
                seeIRStatus(e);
            }
        }

        if (isRightClick && isShift && !isLeftClick) {
            if (clickedNormalBlock) {
                placeDebugHead(e);
            }
            if (clickedAir) {
                seeServerStatus(e);
            }
        }
    }

    private void breakBlock(PlayerInteractEvent e) {
        BlockBreakEvent event = new BlockBreakEvent(e.getClickedBlock(), e.getPlayer());
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    private void seeBlockState(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        player.sendMessage("Checking block state: ");
        if (block == null) {
            player.sendMessage("No block was clicked.");
            return;
        }
        player.sendMessage(" - Location: " + simpleLocationToString(block.getLocation()));
        player.sendMessage(" - Type: " + block.getType().toString());
        player.sendMessage(" - Biome: " + block.getBiome().toString());
        player.sendMessage(" - Redstone Power: " + block.getBlockPower());
        player.sendMessage(" - Light level: " + block.getLightLevel());
        player.sendMessage(" - Light from sky: " + block.getLightFromSky());
        player.sendMessage(" - Humidity: " + block.getHumidity());
        player.sendMessage(" - Temperature: " + block.getTemperature());
        player.sendMessage(" - Chunk x: " + block.getChunk().getX());
        player.sendMessage(" - Chunk z: " + block.getChunk().getZ());
    }

    private void seeIRBlockData(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        player.sendMessage("Checking IR block data: ");
        if (block == null) {
            player.sendMessage("No block was clicked.");
            return;
        }

        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data == null) {
            player.sendMessage("This block has no IR block data.");
            return;
        }
        Location location = data.getLocation();

        player.sendMessage(" - Location: " + simpleLocationToString(location));
        player.sendMessage(" - ID: " + data.getId());
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(data.getId());
        boolean hasTicker;
        boolean ticking;
        BlockTicker ticker = iritem.getItemHandler(BlockTicker.class);
        if (ticker == null) {
            hasTicker = false;
        } else {
            hasTicker = true;
        }

        IRBlockData blockData = IndustrialRevival.getInstance().getProfilerService().getTask().getTickingBlocks().get(location);
        if (blockData != null) {
            ticking = true;
        } else {
            ticking = false;
        }

        player.sendMessage(" - Ticker: " + (hasTicker ? "√" : "×"));
        player.sendMessage(" - Ticking: " + (ticking ? "√" : "×"));
        if (hasTicker) {
            String id = data.getId();
            PerformanceSummary summary = IndustrialRevival.getInstance().getProfilerService().getSummary();
            long timingsOfThisBlock = summary.getDataByLocation().get(location);
            long totalTimingsOfThisBlock = summary.getDataByID().get(id);
            long avgTimingsOfThisBlock = totalTimingsOfThisBlock / summary.getDataByID().size();
            player.sendMessage("- Timings: ");
            player.sendMessage("  - This Timings: " + NumberUtils.round(NumberUtils.nsToMs(timingsOfThisBlock), 2) + "ms");
            player.sendMessage("  - Average Timings: " + NumberUtils.round(NumberUtils.nsToMs(avgTimingsOfThisBlock), 2) + "ms");
            player.sendMessage("  - Total Timings: " + NumberUtils.round(NumberUtils.nsToMs(totalTimingsOfThisBlock), 2) + "ms");
        }

        Map<String, String> dataMap = data.getData();
        if (!dataMap.isEmpty()) {
            player.sendMessage(" - Data: [");
            for (String key : dataMap.keySet()) {
                player.sendMessage("   - " + key + ": " + dataMap.get(key));
            }
            player.sendMessage(" ]");
        }
    }

    private void seeChunkTimings(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Checking chunk timings: ");
        Chunk chunk = player.getChunk();
        ChunkPosition position = new ChunkPosition(chunk);
        PerformanceSummary summary = IndustrialRevival.getInstance().getProfilerService().getSummary();

        long chunkTimings = summary.getDataByChunk().get(position);
        long avgTimingsPerMachine = chunkTimings / summary.getDataByLocation().keySet().stream().filter(location -> location.getChunk().equals(chunk)).toList().size();
        long avgTimingsPerChunk = summary.getTotalTime();
        player.sendMessage("- Timings: ");
        player.sendMessage("  - Total Chunk Timings: " + NumberUtils.round(NumberUtils.nsToMs(chunkTimings), 2) + "ms");
        player.sendMessage("  - Average Timings Per Machine in This Chunk: " + NumberUtils.round(NumberUtils.nsToMs(avgTimingsPerMachine), 2) + "ms");
        player.sendMessage("  - Average Timings Per Chunk: " + NumberUtils.round(NumberUtils.nsToMs(avgTimingsPerChunk), 2) + "ms");
    }

    private void forceBreakBlock(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        player.sendMessage("Force-breaking block: ");
        if (block == null) {
            player.sendMessage("No block was clicked.");
            return;
        }
        player.sendMessage(" - Location: " + simpleLocationToString(block.getLocation()));
        player.sendMessage(" - Type: " + block.getType().toString());
        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data != null) {
            player.sendMessage("Cannot force-break an IR block before removing its data.");
            return;
        }
        block.setType(Material.AIR);
        player.sendMessage("Block force-broken.");
    }

    private void removeIRBlockData(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        player.sendMessage("Removing IR block data: ");
        if (block == null) {
            player.sendMessage("No block was clicked.");
            return;
        }
        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data == null) {
            player.sendMessage("This block has no IR block data.");
            return;
        }

        Location location = data.getLocation();
        player.sendMessage(" - Location: " + simpleLocationToString(location));
        player.sendMessage(" - ID: " + data.getId());

        DataUtil.removeBlockData(location);
        player.sendMessage("IR block data removed.");
    }

    private void seeIRStatus(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Checking Industrial Revival status: ");

        PluginMeta pluginMeta = IndustrialRevival.getInstance().getPlugin().getPluginMeta();
        player.sendMessage(" - Enabled: " + booleanToSymbol(IndustrialRevival.getInstance().isEnabled()));
        player.sendMessage(" - Environment check: " + booleanToSymbol(IndustrialRevival.getInstance().environmentCheck()));
        player.sendMessage(" - Name: " + IndustrialRevival.getInstance().getPlugin().getName());
        player.sendMessage(" - Version: " + pluginMeta.getVersion());
        player.sendMessage(" - Authors: " + pluginMeta.getAuthors());
        player.sendMessage(" - Description: " + pluginMeta.getDescription());
        player.sendMessage(" - Website: " + pluginMeta.getWebsite());
        player.sendMessage(" - API version: " + pluginMeta.getAPIVersion());
        player.sendMessage(" - Issue tracker: " + IndustrialRevival.getInstance().getIssueTrackerURL());
        player.sendMessage(" - Installed addons: " + IndustrialRevival.getInstance().getAddons().size());

        IRRegistry registry = IndustrialRevival.getInstance().getRegistry();
        player.sendMessage(" - Loaded items: " + registry.getItems().size());
        player.sendMessage(" - Loaded item groups: " + registry.getItemGroups());
        player.sendMessage(" - Loaded recipe types: " + registry.getCraftables().size());
        player.sendMessage(" - Loaded menu presets: " + registry.getMenuPresets().size());
        player.sendMessage(" - Loaded player profiles: " + registry.getPlayerProfiles().size());
        player.sendMessage(" - Loaded display groups: " + registry.getDisplayGroups());
        player.sendMessage(" - Loaded researches: " + registry.getResearches().size());
        AtomicInteger recipes = new AtomicInteger();
        registry.getCraftables().forEach((type, craftables) -> {
            recipes.addAndGet(craftables.size());
        });
        player.sendMessage(" - Loaded recipes: " + recipes.get());
        AtomicInteger mobDrops = new AtomicInteger();
        registry.getMobDrops().forEach((type, drops) -> {
            mobDrops.addAndGet(drops.size());
        });
        player.sendMessage(" - Loaded mob drops: " + mobDrops.get());
        AtomicInteger blockDrops = new AtomicInteger();
        registry.getBlockDrops().forEach((type, drops) -> {
            blockDrops.addAndGet(drops.size());
        });
        player.sendMessage(" - Loaded block drops: " + blockDrops.get());
        player.sendMessage(" - Loaded listeners: " + IndustrialRevival.getInstance().getListenerManager().getListenerCount());
    }

    private void placeDebugHead(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Placing Debug Head: ");
        Block block = e.getClickedBlock();
        if (block == null) {
            player.sendMessage("No block was clicked.");
            return;
        }
        Location location = block.getRelative(e.getBlockFace()).getLocation();
        player.sendMessage(" - Location: " + simpleLocationToString(location));
        IRBlockPlaceEvent event = new IRBlockPlaceEvent(new BlockPlaceEvent(location.getBlock(), location.getBlock().getState(), block, IRItems.IRItemStacks.DEBUG_HEAD.clone(), player, true), IRItems.DEBUG_HEAD);
        IndustrialRevival.getInstance().getItemTextureService().blockPlacing(event);
        IndustrialRevival.getInstance().getDataManager().handleBlockPlacing(block.getLocation(), IRItems.IRItemStacks.DEBUG_HEAD.getId());

        BlockPlaceHandler handler = IRItems.DEBUG_HEAD.getItemHandler(BlockPlaceHandler.class);

        if (handler != null) {
            handler.onBlockPlace(event);
        }

        player.sendMessage("Debug Head placed.");
    }

    private void seeServerStatus(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Server server = Bukkit.getServer();
        player.sendMessage("Checking server status: ");
        player.sendMessage(" - Server software: " + (PaperLib.isPaper() ? "Paper" : PaperLib.isSpigot() ? "Spigot" : Bukkit.getName()));
        player.sendMessage(" - Name: " + server.getName());
        player.sendMessage(" - Server Version: " + server.getVersion());
        player.sendMessage(" - Bukkit version: " + server.getBukkitVersion());
        player.sendMessage(" - Minecraft version: " + server.getMinecraftVersion());
        player.sendMessage(" - Version message: " + Bukkit.getVersionMessage());
        player.sendMessage(" - Plugins: " + server.getPluginManager().getPlugins().length);
        player.sendMessage(" - TPS: " + server.getTPS()[0]);
        player.sendMessage(" - Average tick time: " + server.getAverageTickTime());
        player.sendMessage(" - Online players: " + server.getOnlinePlayers().size());
        player.sendMessage(" - Max players: " + server.getMaxPlayers());
        player.sendMessage(" - Worlds: " + server.getWorlds().size());
    }

    private String simpleLocationToString(Location location) {
        return "Location{world=" + location.getWorld().getName() + ", x=" + location.getBlockX() + ", y=" + location.getBlockY() + ", z=" + location.getBlockZ() + "}";
    }

    private String booleanToSymbol(boolean b) {
        return (b ? green + "✔" : red + "✘") + white;
    }
}
