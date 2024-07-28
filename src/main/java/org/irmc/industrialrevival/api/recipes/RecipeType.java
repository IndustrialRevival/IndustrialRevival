package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

@Getter
public class RecipeType {
    public static final RecipeType GRINDSTONE;
    public static final RecipeType SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType KILL_MOB;

    private final NamespacedKey key;
    private final ItemStack icon;

    public RecipeType(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
    }

    public RecipeType(NamespacedKey key, String itemId) {
        this(key, IndustrialRevivalItem.getById(itemId).getItem());
    }

    static {
        GRINDSTONE = new RecipeType(
                new NamespacedKey(IndustrialRevival.getInstance(), "grindstone"),
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName("grindstone"),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore("grindstone")));

        SMELTING = new RecipeType(
                new NamespacedKey(IndustrialRevival.getInstance(), "smelting"),
                new CustomItemStack(
                        Material.BLAST_FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName("smelting"),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore("smelting")));

        MINE = new RecipeType(
                new NamespacedKey(IndustrialRevival.getInstance(), "mine"),
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName("mine"),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore("mine")));

        KILL_MOB = new RecipeType(
                new NamespacedKey(IndustrialRevival.getInstance(), "kill_mob"),
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName("kill_mob"),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore("kill_mob")));
    }
}
