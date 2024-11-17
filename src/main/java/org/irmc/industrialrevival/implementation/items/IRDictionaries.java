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
                List.of(
                        IndustrialRevivalItems.ALUMINIUM_ORE,
                        IndustrialRevivalItems.CHROMIUM_ORE,
                        IndustrialRevivalItems.LEAD_ORE,
                        IndustrialRevivalItems.COBALT_ORE,
                        IndustrialRevivalItems.MAGNESIUM_ORE,
                        IndustrialRevivalItems.MAGNET_ORE,
                        IndustrialRevivalItems.URANIUM_ORE,
                        IndustrialRevivalItems.TIN_ORE,
                        IndustrialRevivalItems.MERCURY_ORE,
                        IndustrialRevivalItems.SILVER_ORE,
                        IndustrialRevivalItems.NICKEL_ORE,
                        IndustrialRevivalItems.TUNGSTEN_ORE,
                        IndustrialRevivalItems.ZINC_ORE));

        IR_ORE_PRODUCTION = new UnchangeableItemDictionary(KeyUtil.customKey("dict_ore_production"), List.of());
    }

    public static ItemDictionary getDictionary(NamespacedKey key) {
        return IndustrialRevival.getInstance().getRegistry().getDictionaries().get(key);
    }
}
