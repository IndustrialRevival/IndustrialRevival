package org.irmc.industrialrevival.api.items;

import java.util.function.BiConsumer;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class LimitedItem extends IndustrialRevivalItem implements Limited {
    private final BiConsumer<Player, ItemStack> onUseHandler;
    private final NamespacedKey LIMITED_COUNT_LEFT;

    private final int limit;

    public LimitedItem(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            int limit) {
        super(group, itemStack, recipeType, recipe);
        this.limit = limit;
        this.onUseHandler = this::onItemUse;
        this.LIMITED_COUNT_LEFT = new NamespacedKey(IndustrialRevival.getInstance(), "lcl_" + getId().toLowerCase());
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
        this.onUseHandler = onUseHandler;
        this.LIMITED_COUNT_LEFT = new NamespacedKey(IndustrialRevival.getInstance(), "lcl_" + getId().toLowerCase());
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public final int getCountLeft(ItemStack item) {
        return PersistentDataAPI.getInt(item.getItemMeta(), LIMITED_COUNT_LEFT, 0);
    }

    @Override
    public void setCountLeft(ItemStack item, int countLeft) {
        PersistentDataAPI.setInt(item.getItemMeta(), LIMITED_COUNT_LEFT, countLeft);
    }

    public void doUse(Player player, ItemStack item) {
        onUseHandler.accept(player, item);
    }

    private void onItemUse(Player player, ItemStack item) {
        if (getCountLeft(item) <= 0) {
            item.setAmount(0);
        }
        setCountLeft(item, getCountLeft(item) - 1);
        if (getCountLeft(item) == 0) {
            item.setAmount(0);
        }
    }
}
