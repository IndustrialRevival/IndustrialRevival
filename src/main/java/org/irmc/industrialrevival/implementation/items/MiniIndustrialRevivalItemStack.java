package org.irmc.industrialrevival.implementation.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

class MiniIndustrialRevivalItemStack extends IndustrialRevivalItemStack {
    public MiniIndustrialRevivalItemStack(@NotNull String id, @NotNull ItemStack itemStack) {
        super(new NamespacedKey(IndustrialRevival.getInstance(), id.toLowerCase()), itemStack);
    }

    public MiniIndustrialRevivalItemStack(@NotNull String id, @NotNull Material material) {
        this(id, ItemStack.of(material));
    }
}
