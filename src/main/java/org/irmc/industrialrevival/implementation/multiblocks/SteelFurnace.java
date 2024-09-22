package org.irmc.industrialrevival.implementation.multiblocks;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.multiblocks.DisplayableMultiBlock;
import org.irmc.industrialrevival.api.multiblocks.MultiBlockCore;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public class SteelFurnace extends MultiBlockCore implements DisplayableMultiBlock {
    Map<Location, DisplayGroup> displayGroups;

    public SteelFurnace(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe) {
        super(group, itemStack, recipeType, recipe, 1, 2, 1);
    }

    @Override
    public boolean environmentCheck(Block block, MachineMenu menu) {
        if (block.getRelative(BlockFace.DOWN).getType() == Material.FIRE) {
            return true;
        }
        return false;
    }

    @Override
    public Map<Location, DisplayGroup> getDisplayGroups() {
        return displayGroups;
    }
}
