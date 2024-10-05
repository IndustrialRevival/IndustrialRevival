package org.irmc.industrialrevival.api.multiblocks;

import lombok.Getter;
import org.bukkit.Axis;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

@Getter
public abstract class MultiBlockCore extends IndustrialRevivalItem implements IMultiBlock {
    private int maxX = 0;
    private int maxY = 0;
    private int maxZ = 0;

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
    public MultiBlockCore setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public MultiBlockCore setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public MultiBlockCore addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public boolean environmentCheck(@NotNull Block block, @Nullable MachineMenu menu) {
        return false;
    }

    public void setLimit(@NotNull Axis axis, @Range(from = 1, to = 9) int max) {
        checkRegistered();
        if (max < 1 || max > 9) {
            throw new IllegalArgumentException("maxX must be between 1 and 9");
        }
        switch (axis) {
            case X -> this.maxX = max;
            case Y -> this.maxY = max;
            case Z -> this.maxZ = max;
            default -> throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }

    public int getLimit(@NotNull Axis axis) {
        checkRegistered();
        return switch (axis) {
            case X -> maxX;
            case Y -> maxY;
            case Z -> maxZ;
            default -> throw new IllegalArgumentException("Invalid axis: " + axis);
        };
    }

    @Override
    public void preRegister() throws Exception {
        super.preRegister();

        if (maxX == 0 || maxY == 0 || maxZ == 0) {
            throw new Exception("MultiBlockCore must have a limit set for each axis");
        }

        addItemHandlers(new BlockTicker() {
            @Override
            public void onTick(@NotNull Block block, @Nullable MachineMenu menu, @Nullable IRBlockData data) {
                tick(block, menu, data);
            }
        });
    }

    public abstract void tick(@NotNull Block block, @Nullable MachineMenu menu, @Nullable IRBlockData data);
}
