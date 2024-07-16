package org.irmc.industrialrevival.api.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class RecipeType {
    public static final RecipeType GRINDSTONE;

    private final NamespacedKey key;
    private final ItemStack icon;

    public RecipeType(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public ItemStack getIcon() {
        return icon;
    }

    static {
        GRINDSTONE = new RecipeType(
                new NamespacedKey(IndustrialRevival.getInstance(), "grindstone"),
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getItemName("grindstone"),
                        IndustrialRevival.getInstance().getLanguageManager().getItemLore("grindstone")
                )
        );
    }
}
