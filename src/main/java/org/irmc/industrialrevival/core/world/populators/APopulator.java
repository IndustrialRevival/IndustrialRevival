package org.irmc.industrialrevival.core.world.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitRunnable;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.Range;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("deprecation")
public abstract class APopulator extends BlockPopulator {

    @Override
    public void populate(@Nonnull World world, @Nonnull Random random, @Nonnull Chunk source) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (int i = 0; i < getGenerateTimes(); i++) {
                    int x = random.nextInt(getOriginX(), getBoundX());
                    int y = random.nextInt(getOriginY(), getBoundY()) + 1;
                    int z = random.nextInt(getOriginZ(), getBoundZ());
                    int count = 0;
                    while (count < getMaxGenerateOnce() && random.nextDouble() < getGenerateChance()) {
                        Block sourceb = source.getBlock(x, y, z);
                        if (isReplaceable(sourceb)) {
                            if (DataUtil.hasBlockData(sourceb.getLocation())) return;
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    generate(world, random, source, sourceb);
                                }

                            }.runTask(getAddon().getPlugin());
                            count++;
                        }

                        switch (random.nextInt(6)) {
                            case 0 -> x = Math.min(x + 1, 15);
                            case 1 -> y = Math.min(y + 1, 15);
                            case 2 -> z = Math.min(z + 1, 15);
                            case 3 -> x = Math.max(x - 1, 0);
                            case 4 -> y = Math.max(y - 1, 1);
                            default -> z = Math.max(z - 1, 0);
                        }
                    }

                }

            }

        }.runTaskAsynchronously(getAddon().getPlugin());

    }

    public boolean isReplaceable(@Nonnull Block block) {
        return isReplaceable(block.getType());
    }

    public abstract boolean isReplaceable(@Nonnull Material material);

    public abstract void generate(@Nonnull World world, @Nonnull Random random, @Nonnull Chunk chunk, @Nonnull Block block);

    @Range(from = 0, to = 127)
    public int getGenerateTimes() {
        return 3;
    }

    @Range(from = 0, to = 16)
    public int getOriginX() {
        return 0;
    }

    @Range(from = 0, to = 16)
    public int getBoundX() {
        return 16;
    }

    @Range(from = -64, to = 319)
    public abstract int getOriginY();

    @Range(from = -64, to = 319)
    public int getBoundY() {
        return 63;
    }

    @Range(from = 0, to = 16)
    public int getOriginZ() {
        return 0;
    }

    @Range(from = 0, to = 16)
    public int getBoundZ() {
        return 16;
    }

    @Range(from = 0, to = 1)
    public abstract double getGenerateChance();

    @Range(from = 0, to = 100)
    public int getMaxGenerateOnce() {
        return 2;
    }

    @Nonnull
    public abstract IndustrialRevivalAddon getAddon();
}
