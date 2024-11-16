package org.irmc.industrialrevival.utils;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

public class ItemStackUtil {
    public static ItemStack hideTooltip(ItemStack itemStack) {
        return new CustomItemStack(itemStack, meta -> {
            meta.addItemFlags(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        }).toPureItemStack();
    }
}
