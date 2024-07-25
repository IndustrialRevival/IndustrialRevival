package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.entity.EntityType;

public interface MobDropItem extends ChancedItem {
    EntityType getMobType();

    int dropAmount();
}
