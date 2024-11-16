package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.BlockDropMethod;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class DropFromBlockItem extends IndustrialRevivalItem implements BlockDropItem {
    @Getter
    private final Set<BlockDropMethod> blockDropMethods = new HashSet<>();

    @Override
    public BlockDropMethod[] getDropMethods() {
        return blockDropMethods.toArray(new BlockDropMethod[0]);
    }

    @Override
    public DropFromBlockItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }
    @Override
    public DropFromBlockItem addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public DropFromBlockItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public DropFromBlockItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public DropFromBlockItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public DropFromBlockItem setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public DropFromBlockItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public DropFromBlockItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public DropFromBlockItem setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Override
    public DropFromBlockItem setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }

    @Override
    public DropFromBlockItem setHideInGuide(boolean hideInGuide) {
        super.setHideInGuide(hideInGuide);
        return this;
    }

    public DropFromBlockItem addDropMethod(@NotNull BlockDropMethod blockDropMethod) {
        checkRegistered();
        blockDropMethods.add(blockDropMethod);
        return this;
    }
}
