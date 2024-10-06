package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.BiConsumer;

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
    public LimitedItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        this.LIMITED_COUNT_LEFT = new NamespacedKey(IndustrialRevival.getInstance(), "lcl_" + getId().toLowerCase());
        return this;
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

    @Override
    public LimitedItem setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
        return this;
    }

    @Override
    public LimitedItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public LimitedItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public LimitedItem setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public LimitedItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public LimitedItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public LimitedItem setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Override
    public LimitedItem setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }

    @Override
    public LimitedItem setHideInGuide(boolean hideInGuide) {
        super.setHideInGuide(hideInGuide);
        return this;
    }
}
