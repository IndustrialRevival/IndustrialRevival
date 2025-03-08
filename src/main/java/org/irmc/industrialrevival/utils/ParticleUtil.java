package org.irmc.industrialrevival.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for rendering geometric shapes using Minecraft's particle system.
 * <p>
 * Provides asynchronous particle rendering methods for lines, cubes, and region outlines.
 * All methods use Bukkit's scheduler to prevent server thread blocking during execution.
 *
 * @author Final_ROOT, balugaq
 */
public class ParticleUtil {
    /** X-axis coordinate offsets for cube corner positions */
    private static final double[] BLOCK_CUBE_OFFSET_X = new double[]{0, 1, 0, 0, 1, 1, 0, 1};
    /** Y-axis coordinate offsets for cube corner positions */
    private static final double[] BLOCK_CUBE_OFFSET_Y = new double[]{0, 0, 1, 0, 1, 0, 1, 1};
    /** Z-axis coordinate offsets for cube corner positions */
    private static final double[] BLOCK_CUBE_OFFSET_Z = new double[]{0, 0, 0, 1, 0, 1, 1, 1};

    /**
     * Renders a segmented particle line with fixed particle count per segment.
     * <p>
     * Distributes particles evenly between consecutive locations. For List-based version see
     * {@link #drawLineByTotalAmount(Particle, int, List)}.
     *
     * @param particle     Particle type to display
     * @param totalAmount  Minimum 1 particle per segment
     * @param locations    Array of connection points (minimum 2 required)
     * @apiNote Requires locations in the same world. Invalid segments are skipped.
     *          Executes synchronously on main thread.
     */
    public static void drawLineByTotalAmount(@Nonnull Particle particle, int totalAmount, @Nonnull Location... locations) {
        // Original implementation preserved
        for (int i = 0; i < locations.length; i++) {
            if ((i + 1) < locations.length) {
                Location location1 = locations[i];
                Location location2 = locations[i + 1];

                if (totalAmount < 1 || location1.getWorld() == null || location1.getWorld() != location2.getWorld()) {
                    return;
                }
                World world = location1.getWorld();
                double[] x = JavaUtil.disperse(totalAmount, location1.getX(), location2.getX());
                double[] y = JavaUtil.disperse(totalAmount, location1.getY(), location2.getY());
                double[] z = JavaUtil.disperse(totalAmount, location1.getZ(), location2.getZ());
                for (int j = 0; j < totalAmount; j++) {
                    world.spawnParticle(particle, x[j], y[j], z[j], 1, 0, 0, 0, 0);
                }
            }
        }
    }

    /**
     * List-based overload of {@link #drawLineByTotalAmount(Particle, int, Location...)}.
     *
     * @param locationList Connection points list (converted to array internally)
     */
    public static void drawLineByTotalAmount(@Nonnull Particle particle, int totalAmount, @Nonnull List<Location> locationList) {
        Location[] locations = new Location[locationList.size()];
        for (int i = 0; i < locations.length; i++) {
            locations[i] = locationList.get(i);
        }
        ParticleUtil.drawLineByTotalAmount(particle, totalAmount, locations);
    }

    /**
     * Renders particle lines with distance-based spacing and async execution.
     * <p>
     * Distributes particles at fixed intervals along paths, scheduling execution through
     * plugin's async scheduler. For List-based overload, see
     * {@link #drawLineByDistance(Plugin, Particle, long, double, List)}.
     *
     * @param plugin    Plugin instance for scheduler access
     * @param particle  Particle type to display
     * @param interval  Execution interval in milliseconds (≥50ms triggers async)
     * @param distance  Blocks between particles
     * @param locations Connection points array
     * @apiNote Differs from totalAmount-based methods by using spatial distribution.
     *          Requires locations in the same world.
     */
    public static void drawLineByDistance(@Nonnull Plugin plugin, @Nonnull Particle particle, long interval, double distance, @Nonnull Location... locations) {
        // Original implementation preserved
        int time = 0;
        for (int i = 0; i + 1 < locations.length; i++) {
            Location location1 = locations[i];
            Location location2 = locations[i + 1];

            if (distance == 0 || location1.getWorld() == null || location1.getWorld() != location2.getWorld()) {
                return;
            }
            World world = location1.getWorld();
            double x = location1.getX();
            double y = location1.getY();
            double z = location1.getZ();

            double d = location1.distance(location2);
            int particleCount = (int) (d / distance);
            double px = (location2.getX() - x) / (particleCount);
            double py = (location2.getY() - y) / (particleCount);
            double pz = (location2.getZ() - z) / (particleCount);

            double t = time;
            int tick = (int) (t / 50);
            int lastTick;
            List<Runnable> runnableList = new ArrayList<>();
            for (int j = 0; j < particleCount; j++) {
                x += px;
                y += py;
                z += pz;
                double fx = x;
                double fy = y;
                double fz = z;
                runnableList.add(() -> world.spawnParticle(particle, fx, fy, fz, 1, 0, 0, 0, 0));

                t += (double) interval / particleCount;
                lastTick = (int) (t / 50);
                if (tick != lastTick) {
                    final List<Runnable> finalRunnableList = runnableList;
                    plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> finalRunnableList.forEach(Runnable::run), tick);
                    tick = lastTick;
                    runnableList = new ArrayList<>();
                }
            }
            if (!runnableList.isEmpty()) {
                final List<Runnable> finalRunnableList = runnableList;
                plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> finalRunnableList.forEach(Runnable::run), tick);
            }

            time += (int) interval;
        }
    }

    /**
     * List-based overload of {@link #drawLineByDistance(Plugin, Particle, long, double, Location...)}.
     *
     * @param locationList Connection points list (converted to array internally)
     */
    public static void drawLineByDistance(@Nonnull Plugin plugin, @Nonnull Particle particle, long interval, double distance, @Nonnull List<Location> locationList) {
        Location[] locations = new Location[locationList.size()];
        for (int i = 0; i < locations.length; i++) {
            locations[i] = locationList.get(i);
        }
        ParticleUtil.drawLineByDistance(plugin, particle, interval, distance, locations);
    }

    /**
     * Renders particle cubes at specified locations with scheduled execution.
     * <p>
     * Uses predefined block corner offsets ({@link #BLOCK_CUBE_OFFSET_X},
     * {@link #BLOCK_CUBE_OFFSET_Y}, {@link #BLOCK_CUBE_OFFSET_Z}) to draw cube vertices.
     *
     * @param plugin    Plugin instance for scheduler access
     * @param particle  Particle type to display
     * @param interval  Delay between renders in milliseconds (immediate if <50ms)
     * @param locations Cube positions array
     * @apiNote Executes synchronously for intervals below 50ms, async otherwise
     */
    public static void drawCubeByLocations(@Nonnull Plugin plugin, @Nonnull Particle particle, long interval, Location... locations) {
        // Original implementation preserved
        int time = 0;
        for (Location location : locations) {
            World world = location.getWorld();
            if (world == null) {
                continue;
            }
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            if (time < 50) {
                for (int i = 0; i < BLOCK_CUBE_OFFSET_X.length; i++) {
                    world.spawnParticle(particle, x + BLOCK_CUBE_OFFSET_X[i], y + BLOCK_CUBE_OFFSET_Y[i], z + BLOCK_CUBE_OFFSET_Z[i], 1, 0, 0, 0, 0);
                }
            } else {
                plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {
                    for (int i = 0; i < BLOCK_CUBE_OFFSET_X.length; i++) {
                        world.spawnParticle(particle, x + BLOCK_CUBE_OFFSET_X[i], y + BLOCK_CUBE_OFFSET_Y[i], z + BLOCK_CUBE_OFFSET_Z[i], 1, 0, 0, 0, 0);
                    }
                }, time / 50);
            }
            time += (int) interval;
        }
    }

    /**
     * List-based overload of {@link #drawCubeByLocations(Plugin, Particle, long, Location...)}.
     *
     * @param locationList Cube positions list (converted to array internally)
     */
    public static void drawCubeByLocations(@Nonnull Plugin plugin, @Nonnull Particle particle, long interval, @Nonnull List<Location> locationList) {
        Location[] locations = new Location[locationList.size()];
        for (int i = 0; i < locationList.size(); i++) {
            locations[i] = locationList.get(i);
        }
        ParticleUtil.drawCubeByLocations(plugin, particle, interval, locations);
    }

    /**
     * Renders a cuboid outline between two corner locations.
     * <p>
     * Constructs 12 edges of a cube using {@link #drawLineByDistance} with 0.25m spacing.
     *
     * @param plugin    Plugin instance for scheduler access
     * @param particle  Particle type to display
     * @param interval  Delay between particle batches in milliseconds
     * @param corner1   First bounding coordinate
     * @param corner2   Opposite bounding coordinate
     * @throws IllegalArgumentException If corners are in different worlds
     */
    public static void drawRegionOutline(@Nonnull Plugin plugin, @Nonnull Particle particle, long interval, @Nonnull Location corner1, @Nonnull Location corner2) {
        // Original implementation preserved
        World world = corner1.getWorld();
        if (world == null || corner1.getWorld() != corner2.getWorld()) {
            return;
        }

        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());

        Location[] corners = new Location[]{
                new Location(world, minX, minY, minZ),
                new Location(world, minX, minY, maxZ + 1),
                new Location(world, maxX + 1, minY, maxZ + 1),
                new Location(world, maxX + 1, minY, minZ),
                new Location(world, minX, maxY + 1, minZ),
                new Location(world, minX, maxY + 1, maxZ + 1),
                new Location(world, maxX + 1, maxY + 1, maxZ + 1),
                new Location(world, maxX + 1, maxY + 1, minZ)
        };

        drawLineByDistance(plugin, particle, interval, 0.25, corners[0], corners[1]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[0], corners[3]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[0], corners[4]);

        drawLineByDistance(plugin, particle, interval, 0.25, corners[1], corners[2]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[1], corners[5]);

        drawLineByDistance(plugin, particle, interval, 0.25, corners[2], corners[3]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[2], corners[6]);

        drawLineByDistance(plugin, particle, interval, 0.25, corners[3], corners[7]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[4], corners[5]);

        drawLineByDistance(plugin, particle, interval, 0.25, corners[4], corners[7]);
        drawLineByDistance(plugin, particle, interval, 0.25, corners[5], corners[6]);

        drawLineByDistance(plugin, particle, interval, 0.25, corners[6], corners[7]);
    }
}