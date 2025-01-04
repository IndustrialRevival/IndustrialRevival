package org.irmc.industrialrevival.api.objects.itemstacks;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.irmc.pigeonlib.items.CustomItemStack;

public class LeatherItemStack extends CustomItemStack {
    public LeatherItemStack(Color color, ItemStack itemStack) {
        super(itemStack);
        this.getBukkit().editMeta(LeatherArmorMeta.class, meta -> {
            meta.setColor(color);
        });
    }
}
