package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.function.BiConsumer;

public class LimitedItem extends IndustrialRevivalItem implements Limited {
    private NamespacedKey LIMITED_COUNT_LEFT;
    @Getter
    private BiConsumer<Player, ItemStack> onUseHandler;
    @Getter
    private int limit = 0;

    public LimitedItem() {
    }

    public LimitedItem setLimit(@Range(from = 0, to = Integer.MAX_VALUE) int limit) {
        this.limit = limit;
        return this;
    }

    public LimitedItem setOnUseHandler(@NotNull BiConsumer<Player, ItemStack> onUseHandler) {
        this.onUseHandler = onUseHandler;
        return this;
    }

    @Override
    public final int getCountLeft(@NotNull ItemStack item) {
        return PersistentDataAPI.getInt(item.getItemMeta(), LIMITED_COUNT_LEFT, 0);
    }

    @Override
    public void setCountLeft(@NotNull ItemStack item, int countLeft) {
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
