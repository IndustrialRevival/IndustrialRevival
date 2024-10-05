package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.MobDropMethod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class DropFromMobItem extends IndustrialRevivalItem implements MobDropItem {
    @Getter
    private final Set<MobDropMethod> dropMethods = new HashSet<>();

    @Override
    public MobDropMethod[] getDropMethods() {
        return dropMethods.toArray(new MobDropMethod[0]);
    }

    public DropFromMobItem addDropMethod(MobDropMethod dropMethod) {
        checkRegistered();
        dropMethods.add(dropMethod);
        return this;
    }

    @Override
    public DropFromMobItem setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
        return this;
    }

    @Override
    public DropFromMobItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public DropFromMobItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public DropFromMobItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public DropFromMobItem setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public DropFromMobItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public DropFromMobItem addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }
}
