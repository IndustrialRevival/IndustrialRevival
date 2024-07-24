package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

public interface MobDropItem extends ChancedItem {
    EntityType getMobType();

    ItemStack dropItems();

    default void registerMobDrop() {
        IndustrialRevival.getInstance().getRegistry().registerMobDrop(this);
    }
}
