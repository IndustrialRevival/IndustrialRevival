package org.irmc.industrialrevival.core.world.populators;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Debug;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ElementOreGenerator extends APopulator {
    @Override
    public boolean isReplaceable(@NotNull Material material) {
        return material == Material.STONE || material == Material.GRANITE || material == Material.DIORITE || material == Material.ANDESITE || material == Material.DEEPSLATE;
    }

    @Override
    public void generate(@NotNull World world, @NotNull Random random, @NotNull Chunk chunk, @NotNull Block block) {
        ElementType elementType = ElementType.values()[random.nextInt(ElementType.values().length)];
        if (elementType.isGas()) {
            return;
        }

        if (elementType.isMetal()) {
            block.setType(Material.DEEPSLATE_COPPER_ORE);
        } else {
            block.setType(Material.YELLOW_TERRACOTTA);
        }
        Location location = block.getLocation();
        IRDock.getPlugin().getDataManager().handleBlockPlacing(location, new NamespacedKey(IRDock.getPlugin(), elementType.name().toLowerCase()));
        Debug.debug("Generated " + elementType.name() + " ore at " + location);
    }

    @Override
    public int getOriginY() {
        return -45;
    }

    @Override
    public double getGenerateChance() {
        return 0;
    }

    @Override
    public @NotNull IndustrialRevivalAddon getAddon() {
        return IRDock.getPlugin();
    }
}
