package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.BlockDropMethod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
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
    public DropFromBlockItem setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
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
    public DropFromBlockItem setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public DropFromBlockItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public DropFromBlockItem addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }
    public DropFromBlockItem addDropMethod(BlockDropMethod blockDropMethod) {
        checkRegistered();
        blockDropMethods.add(blockDropMethod);
        return this;
    }
}
