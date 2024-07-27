package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.utils.Keys;

@Getter
public class RecipeType {
    public static final RecipeType GRINDSTONE;
    public static final RecipeType SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType NULL;

    private final NamespacedKey key;
    private final ItemStack icon;

    public RecipeType(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
    }

    static {
        GRINDSTONE = new RecipeType(
                Keys.RECIPE_TYPE_GRIDSTONE,
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(Keys.RECIPE_TYPE_GRIDSTONE),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(Keys.RECIPE_TYPE_GRIDSTONE)));

        SMELTING = new RecipeType(
                Keys.RECIPE_TYPE_SMELTING,
                new CustomItemStack(
                        Material.BLAST_FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(Keys.RECIPE_TYPE_SMELTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(Keys.RECIPE_TYPE_SMELTING)));

        MINE = new RecipeType(
                Keys.RECIPE_TYPE_MINE,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(Keys.RECIPE_TYPE_MINE),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(Keys.RECIPE_TYPE_MINE)));

        NULL = new RecipeType(
                Keys.RECIPE_TYPE_NULL,
                new CustomItemStack(Material.AIR));
    }
}
