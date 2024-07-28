package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Material;

public interface BlockDropItem extends ChancedItem {
    Material dropBlock();

    int dropAmount();
}
