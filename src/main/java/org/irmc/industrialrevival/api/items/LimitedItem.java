package org.irmc.industrialrevival.api.items;

import java.util.function.BiConsumer;

import lombok.Builder;
import lombok.Getter;
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

import javax.annotation.Nonnull;

@Builder
public class LimitedItem extends IndustrialRevivalItem implements Limited {
    private NamespacedKey LIMITED_COUNT_LEFT;
    @Getter
    private BiConsumer<Player, ItemStack> onUseHandler;
    @Getter
    private int limit = 0;

    public LimitedItem(
            @Range(from = 0, to = Integer.MAX_VALUE) int limit,
            @NotNull BiConsumer<Player, ItemStack> onUseHandler) {
        super();
        this.limit = limit;
        this.onUseHandler = onUseHandler;
    }

    @Override
    public LimitedItem setItemStack(@Nonnull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        this.LIMITED_COUNT_LEFT = new NamespacedKey(IndustrialRevival.getInstance(), "lcl_" + getId().toLowerCase());
        return this;
    }

    public LimitedItem setLimit(@Range(from = 0, to = Integer.MAX_VALUE) int limit) {
        this.limit = limit;
        return this;
    }

    public LimitedItem setOnUseHandler(@Nonnull BiConsumer<Player, ItemStack> onUseHandler) {
        this.onUseHandler = onUseHandler;
        return this;
    }

    @Override
    public final int getCountLeft(@Nonnull ItemStack item) {
        return PersistentDataAPI.getInt(item.getItemMeta(), LIMITED_COUNT_LEFT, 0);
    }

    @Override
    public void setCountLeft(@Nonnull ItemStack item, int countLeft) {
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
