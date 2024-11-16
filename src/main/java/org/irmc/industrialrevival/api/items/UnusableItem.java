package org.irmc.industrialrevival.api.items;

import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.Unusable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;


public class UnusableItem extends IndustrialRevivalItem implements Unusable {
    @Override
    public UnusableItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }
    @Override
    public UnusableItem addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public UnusableItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public UnusableItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public UnusableItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public UnusableItem setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public UnusableItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public UnusableItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public UnusableItem setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Override
    public UnusableItem setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }

    @Override
    public UnusableItem setHideInGuide(boolean hideInGuide) {
        super.setHideInGuide(hideInGuide);
        return this;
    }
}
