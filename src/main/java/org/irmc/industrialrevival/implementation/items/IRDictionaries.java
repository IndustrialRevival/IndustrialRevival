package org.irmc.industrialrevival.implementation.items;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.collection.UnchangeableItemDictionary;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;

import java.util.List;

public class IRDictionaries {
    public static final ItemDictionary IR_ORE;
    public static final ItemDictionary IR_ORE_PRODUCTION;

    static {
        IR_ORE = new UnchangeableItemDictionary(
                KeyUtil.customKey("dict_ore"),
                List.of());

        IR_ORE_PRODUCTION = new UnchangeableItemDictionary(KeyUtil.customKey("dict_ore_production"), List.of());
    }

    public static ItemDictionary getDictionary(NamespacedKey key) {
        return IndustrialRevival.getInstance().getRegistry().getDictionaries().get(key);
    }
}
