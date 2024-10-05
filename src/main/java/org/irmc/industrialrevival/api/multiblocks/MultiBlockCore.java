package org.irmc.industrialrevival.api.multiblocks;

import lombok.Getter;
import org.bukkit.Axis;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.annotation.Nonnull;

@Getter
public abstract class MultiBlockCore extends IndustrialRevivalItem implements IMultiBlock {
    private int maxX;
    private int maxY;
    private int maxZ;

    @Override
    public MultiBlockCore setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
        return this;
    }

    @Override
    public MultiBlockCore setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public MultiBlockCore addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public MultiBlockCore setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public MultiBlockCore setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public MultiBlockCore setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public MultiBlockCore addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public boolean environmentCheck(@Nonnull Block block, @Nullable MachineMenu menu) {
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

    public void setLimitX(@Range(from = 1, to = 9) int maxX) {
        checkRegistered();
        if (maxX < 1 || maxX > 9) {
            throw new IllegalArgumentException("maxX must be between 1 and 9");
        }
        this.maxX = maxX;
    }

    public void setLimitY(@Range(from = 1, to = 9) int maxY) {
        checkRegistered();
        if (maxY < 1 || maxY > 9) {
            throw new IllegalArgumentException("maxY must be between 1 and 9");
        }
        this.maxY = maxY;
    }

    public void setLimitZ(@Range(from = 1, to = 9) int maxZ) {
        checkRegistered();
        if (maxZ < 1 || maxZ > 9) {
            throw new IllegalArgumentException("maxZ must be between 1 and 9");
        }
    }
}
