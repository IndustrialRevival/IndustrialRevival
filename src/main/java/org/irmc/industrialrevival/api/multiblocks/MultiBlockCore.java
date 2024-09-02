package org.irmc.industrialrevival.api.multiblocks;

import lombok.Getter;
import org.bukkit.Axis;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public abstract class MultiBlockCore extends IndustrialRevivalItem implements IMultiBlock {
    private @Getter int maxX;
    private @Getter int maxY;
    private @Getter int maxZ;
    public MultiBlockCore(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, int maxX, int maxY, int maxZ) {
        super(group, itemStack, recipeType, recipe);
        setLimit(maxX, maxY, maxZ);
    }

    @Override
    public boolean environmentCheck(Block block, MachineMenu menu) {
        return false;
    }

    @Override
    public void setLimit(int maxX, int maxY, int maxZ) {
        if (maxX < 1 || maxX > 9) {
            throw new IllegalArgumentException("maxX must be between 1 and 9");
        }
        if (maxY < 1 || maxY > 9) {
            throw new IllegalArgumentException("maxY must be between 1 and 9");
        }
        if (maxZ < 1 || maxZ > 9) {
            throw new IllegalArgumentException("maxZ must be between 1 and 9");
        }

        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    @Override
    public int getLimit(Axis axis) {
        if (axis == Axis.X) {
            return maxX;
        } else if (axis == Axis.Y) {
            return maxY;
        } else if (axis == Axis.Z) {
            return maxZ;
        }
        return 0;
    }
}
