package org.irmc.industrialrevival.api.items;

import org.bukkit.World;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NotPlaceableItem extends IndustrialRevivalItem implements NotPlaceable {
    @Override
    public NotPlaceableItem setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
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
    public NotPlaceableItem setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public NotPlaceableItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public NotPlaceableItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public NotPlaceableItem setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Override
    public NotPlaceableItem setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }

    @Override
    public NotPlaceableItem setHideInGuide(boolean hideInGuide) {
        super.setHideInGuide(hideInGuide);
        return this;
    }
}
