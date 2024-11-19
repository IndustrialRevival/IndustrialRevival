package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.MobDropMethod;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class DropFromMobItem extends IndustrialRevivalItem implements MobDropItem {
    @Getter
    private final Set<MobDropMethod> dropMethods = new HashSet<>();

    @Override
    public MobDropMethod[] getDropMethods() {
        return dropMethods.toArray(new MobDropMethod[0]);
    }

    public DropFromMobItem addDropMethod(@NotNull MobDropMethod dropMethod) {
        checkRegistered();
        dropMethods.add(dropMethod);
        return this;
    }
}
