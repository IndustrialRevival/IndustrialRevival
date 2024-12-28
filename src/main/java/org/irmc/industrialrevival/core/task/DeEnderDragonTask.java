package org.irmc.industrialrevival.core.task;

import com.google.common.base.Preconditions;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Getter
public class DeEnderDragonTask implements Consumer<WrappedTask> {
    @NotNull
    private static final Set<String> preventEndDragon = new HashSet<>();
    @NotNull
    private static final Set<String> preventBedrock = new HashSet<>();
    private static boolean isEnabled;
    @Range(from = 1, to = 576)
    private final int checkRadius;
    public DeEnderDragonTask(@Range(from = 1, to = 576) int checkRadius) {
        Preconditions.checkArgument(checkRadius > 0, "checkRadius must be greater than 0");
        Preconditions.checkArgument(checkRadius <= 576, "checkRadius must be less than or equal to 576");

        if (isEnabled) {
            throw new IllegalStateException("DeEnderDragonTask is already running");
        }

        this.checkRadius = checkRadius;
        isEnabled = true;
    }

    public void accept(WrappedTask task) {
        if (task == null) {
            return;
        }

        if (!preventEndDragon.isEmpty()) {
            Set<String> invalidWorlds = new HashSet<>();
            for (String worldName : preventEndDragon) {
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    invalidWorlds.add(worldName);
                    continue;
                }
                Location endPortal = world.getBlockAt(0, 0, 0).getLocation();
                for (Player player : world.getPlayers()) {
                    if (player.getLocation().distance(endPortal) < checkRadius) {
                        Block block = world.getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                        if (block.getType() == Material.END_PORTAL) {
                            block.setType(Material.AIR);
                        }

                        preventEndDragon.remove(worldName);
                    }
                }
            }
            for (String worldName : invalidWorlds) {
                preventEndDragon.remove(worldName);
            }
        }

        if (!preventBedrock.isEmpty()) {
            Set<String> invalidWorlds = new HashSet<>();
            for (String worldName : preventBedrock) {
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    invalidWorlds.add(worldName);
                    continue;
                }
                Location bedrock = world.getBlockAt(0, 60, 0).getLocation();
                for (Player player : world.getPlayers()) {
                    if (player.getLocation().distance(bedrock) < checkRadius) {
                        Block block = world.getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                        if (block.getType() == Material.BEDROCK) {
                            block.setType(Material.AIR);
                        }

                        preventBedrock.remove(worldName);
                    }
                }
            }
            for (String worldName : invalidWorlds) {
                preventBedrock.remove(worldName);
            }
        }
    }

    public static void schedulePreventEndDragon(@NotNull String worldName) {
        Preconditions.checkNotNull(worldName, "worldName cannot be null");
        Preconditions.checkNotNull(Bukkit.getWorld(worldName), "world not found");
        preventEndDragon.add(worldName);
    }

    public static void schedulePreventBedrock(String worldName) {
        Preconditions.checkNotNull(worldName, "worldName cannot be null");
        Preconditions.checkNotNull(Bukkit.getWorld(worldName), "world not found");
        preventBedrock.add(worldName);
    }
}
