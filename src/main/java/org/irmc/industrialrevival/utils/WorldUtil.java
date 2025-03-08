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
import org.irmc.industrialrevival.core.task.AnitEnderDragonTask;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Provides utility methods for Minecraft world operations and generation management.
 * <p>
 * This class includes static utility methods for managing chunk populators ({@link BlockPopulator}),
 * creating custom worlds via {@link WorldCreator}, and implementing mechanisms to prevent Ender Dragon
 * spawning in the End dimension. Suitable for scenarios requiring dynamic world generation rules
 * or specialized world presets.
 * </p>
 * <p>
 * Example use cases:
 * <ul>
 *   <li>Adding custom terrain generators to specific worlds</li>
 *   <li>Creating End worlds with Ender Dragon spawning disabled</li>
 * </ul>
 * </p>
 *
 * @see BlockPopulator
 * @see WorldCreator
 * @see AnitEnderDragonTask
 * @since IndustrialRevival 1.0
 */
@UtilityClass
public class WorldUtil {

    /**
     * Adds a {@link BlockPopulator} to a world identified by its name.
     * <p>
     * Validates the existence of the world before adding the populator to its generator list.
     * This affects terrain generation for newly generated chunks.
     * </p>
     *
     * @param worldName  the name of the target world (must exist and not be null)
     * @param populator  the chunk populator instance to add (must not be null)
     * @return the modified {@link World} object, or {@code null} if the world does not exist
     * @throws NullPointerException if {@code worldName} or {@code populator} is null
     *
     * @see World#getPopulators()
     */
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

    /**
     * Adds a {@link BlockPopulator} directly to an existing {@link World} instance.
     * <p>
     * This is a convenience method for cases where the world reference is already available.
     * </p>
     *
     * @param world      the target world instance (must not be null)
     * @param populator  the chunk populator instance to add (must not be null)
     * @return the modified {@link World} object (for method chaining)
     * @throws NullPointerException if {@code world} or {@code populator} is null
     */
    @CanIgnoreReturnValue
    @NotNull
    public static World addPopulatorTo(@NotNull World world, @NotNull BlockPopulator populator) {
        Preconditions.checkNotNull(world, "world cannot be null");
        Preconditions.checkNotNull(populator, "populator cannot be null");

        world.getPopulators().add(populator);
        return world;
    }

    /**
     * Creates a world using the provided {@link WorldCreator} configuration.
     * <p>
     * Delegates to {@link #createWorld(WorldCreator, boolean)} with {@code deEnderDragon} set to {@code false}.
     * </p>
     *
     * @param worldCreator  the configuration for world creation (must not be null)
     * @return the created {@link World} object, or {@code null} if creation fails
     * @throws NullPointerException if {@code worldCreator} is null
     */
    @CanIgnoreReturnValue
    @Nullable
    public static World createWorld(@NotNull WorldCreator worldCreator) {
        return createWorld(worldCreator, false);
    }

    /**
     * Creates a world using the provided {@link WorldCreator} and optionally disables Ender Dragon mechanics.
     * <p>
     * If {@code deEnderDragon} is {@code true} and the world is an End dimension ({@link World.Environment#THE_END}):
     * <ol>
     *   <li>Places an {@link Material#END_PORTAL} block at (0, 0, 0) to prevent initial Ender Dragon spawning</li>
     *   <li>Schedules a task via {@link AnitEnderDragonTask#schedulePreventEndDragon(String)} to remove the portal
     *       when a player approaches it</li>
     *   <li>Schedules a task via {@link AnitEnderDragonTask#schedulePreventBedrock(String)} to prevent bedrock placement
     *       at (0, 60, 0) after the dragon's death</li>
     * </ol>
     * </p>
     *
     * @param worldCreator    the configuration for world creation (must not be null)
     * @param deEnderDragon   if {@code true}, disables Ender Dragon mechanics for End worlds
     * @return the created {@link World} object, or {@code null} if creation fails
     * @throws NullPointerException if {@code worldCreator} is null
     */
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

            // Set end portal to block dragon spawning
            Location endPortal = world.getBlockAt(0, 0, 0).getLocation();
            endPortal.getBlock().setType(Material.END_PORTAL);

            // Schedule tasks to handle portal and bedrock removal
            AnitEnderDragonTask.schedulePreventEndDragon(worldName);
            AnitEnderDragonTask.schedulePreventBedrock(worldName);
        }

        return world;
    }
}
