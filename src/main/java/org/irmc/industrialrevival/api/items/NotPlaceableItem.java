package org.irmc.industrialrevival.api.items;

import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

public class NotPlaceableItem extends IndustrialRevivalItem implements NotPlaceable {
    @Override
    public NotPlaceableItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }
    @Override
    public NotPlaceableItem addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public NotPlaceableItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public NotPlaceableItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public NotPlaceableItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public NotPlaceableItem setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        super.setDisabledInWorld(world, disabled, saveToConfig);
        return this;
    }

    @Override
    public NotPlaceableItem setDisabled(boolean disabled, boolean saveToConfig) {
        super.setDisabled(disabled, saveToConfig);
        return this;
    }

    @Override
    public NotPlaceableItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public NotPlaceableItem setEnchantable(boolean enchantable, boolean saveToConfig) {
        super.setEnchantable(enchantable, saveToConfig);
        return this;
    }

    @Override
    public NotPlaceableItem setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        super.setDisenchantable(disenchantable, saveToConfig);
        return this;
    }

    @Override
    public NotPlaceableItem setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        super.setHideInGuide(hideInGuide, saveToConfig);
        return this;
    }
}
