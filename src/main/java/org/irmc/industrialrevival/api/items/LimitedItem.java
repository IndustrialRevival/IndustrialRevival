package org.irmc.industrialrevival.api.items;

import java.util.function.BiConsumer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class LimitedItem extends IndustrialRevivalItem implements Limited {
    private final BiConsumer<Player, ItemStack> onUseHandler;

    private int limit;
    private int countLeft;

    public LimitedItem(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            int limit) {
        super(group, itemStack, recipeType, recipe);
        this.limit = limit;
        this.countLeft = limit;
        this.onUseHandler = this::onItemUse;
    }

    public LimitedItem(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            @Range(from = 0, to = Integer.MAX_VALUE) int limit,
            @NotNull BiConsumer<Player, ItemStack> onUseHandler) {
        super(group, itemStack, recipeType, recipe);
        this.limit = limit;
        this.countLeft = limit;
        this.onUseHandler = onUseHandler;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int getCountLeft() {
        return countLeft;
    }

    @Override
    public void setCountLeft(int countLeft) {
        this.countLeft = countLeft;
    }

    public void doUse(Player player, ItemStack item) {
        onUseHandler.accept(player, item);
    }

    private void onItemUse(Player player, ItemStack item) {
        if (countLeft <= 0) {
            item.setAmount(0);
        }
        countLeft -= 1;
        if (countLeft == 0) {
            item.setAmount(0);
        }
    }
}
