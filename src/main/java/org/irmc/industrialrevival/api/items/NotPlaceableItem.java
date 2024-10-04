package org.irmc.industrialrevival.api.items;

import org.bukkit.World;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

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
    public NotPlaceableItem setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public NotPlaceableItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public NotPlaceableItem addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }
}
