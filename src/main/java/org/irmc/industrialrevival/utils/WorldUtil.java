package org.irmc.industrialrevival.utils;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.BlockPopulator;
import org.irmc.industrialrevival.core.task.DeEnderDragonTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class WorldUtil {

    @CanIgnoreReturnValue
    @Nullable
    public static World addPopulatorTo(@NotNull String worldName, @NotNull BlockPopulator populator) {
        Preconditions.checkNotNull(worldName, "worldName cannot be null");
        Preconditions.checkNotNull(populator, "populator cannot be null");

        World world = Bukkit.getServer().getWorld(worldName);
        if (world == null) {
            return null;
        }

        world.getPopulators().add(populator);
        return world;
    }

    @CanIgnoreReturnValue
    @NotNull
    public static World addPopulatorTo(@NotNull World world, @NotNull BlockPopulator populator) {
        Preconditions.checkNotNull(world, "world cannot be null");
        Preconditions.checkNotNull(populator, "populator cannot be null");

        world.getPopulators().add(populator);
        return world;
    }

    @CanIgnoreReturnValue
    @Nullable
    public static World createWorld(@NotNull WorldCreator worldCreator) {
        return createWorld(worldCreator, false);
    }

    @CanIgnoreReturnValue
    @Nullable
    public static World createWorld(@NotNull WorldCreator worldCreator, boolean deEnderDragon) {
        Preconditions.checkNotNull(worldCreator, "worldCreator cannot be null");

        World world;
        try {
            world = worldCreator.createWorld();
            if (world == null) {
                return null;
            }
        } catch (Throwable e) {
            Debug.log("Failed to create world: " + worldCreator.name(), e);
            return null;
        }

        if (deEnderDragon && world.getEnvironment() == World.Environment.THE_END) {
            String worldName = world.getName();

            // Set a end portal block to prevent end dragon spawning
            Location endPortal = world.getBlockAt(0, 0, 0).getLocation();
            endPortal.getBlock().setType(Material.END_PORTAL);

            // When a player is near the end portal, remove the end portal block
            DeEnderDragonTask.schedulePreventEndDragon(worldName);

            // When Bukkit found that the end dragon is already "died", a bedrock will be placed at (0, 60, 0)
            // Same as end portal
            DeEnderDragonTask.schedulePreventBedrock(worldName);
        }

        return world;
    }
}
