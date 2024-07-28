package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.utils.Keys;

import java.util.Arrays;
import java.util.function.Function;

@Getter
public class RecipeType {

    public static final NamespacedKey RECIPE_TYPE_GRIDSTONE = Keys.customKey("gridstone");
    public static final NamespacedKey RECIPE_TYPE_SMELTING = Keys.customKey("smelting");
    public static final NamespacedKey RECIPE_TYPE_MINE = Keys.customKey("mine");
    public static final NamespacedKey RECIPE_TYPE_KILL_MOB = Keys.customKey("kill_mob");
    public static final NamespacedKey RECIPE_TYPE_INTERACT = Keys.customKey("interact");
    public static final NamespacedKey RECIPE_TYPE_WAIT = Keys.customKey("wait");
    public static final NamespacedKey RECIPE_TYPE_NULL = Keys.customKey("null");
    public static final NamespacedKey RECIPE_TYPE_VANILLA_CRAFTING = Keys.customKey("vanilla_crafting");
    
    public static final RecipeType GRINDSTONE;
    public static final RecipeType SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType KILL_MOB;
    public static final RecipeType INTERACT;
    public static final RecipeType WAIT;
    public static final RecipeType NULL;
    public static final RecipeType VANILLA_CRAFTING;

    private final NamespacedKey key;
    private final ItemStack icon;
    private final Function<ItemStack[], ItemStack[]> recipeConverter;

    public RecipeType(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.recipeConverter = o -> {
            if (o.length == 0) {
                return new ItemStack[9];
            } else {
                if (o.length < 9) {
                    ItemStack[] newRecipe = new ItemStack[9];
                    System.arraycopy(o, 0, newRecipe, 0, o.length);
                    return newRecipe;
                } else {
                    return Arrays.copyOf(o, 9);
                }
            }
        };
    }

    public RecipeType(NamespacedKey key, ItemStack icon, Function<ItemStack[], ItemStack[]> recipeConverter) {
        this.key = key;
        this.icon = icon;
        this.recipeConverter = recipeConverter;
    }

    static {
        GRINDSTONE = new RecipeType(
                RECIPE_TYPE_GRIDSTONE,
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_GRIDSTONE),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_GRIDSTONE)));

        SMELTING = new RecipeType(
                RECIPE_TYPE_SMELTING,
                new CustomItemStack(
                        Material.BLAST_FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_SMELTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_SMELTING)));

        MINE = new RecipeType(
                RECIPE_TYPE_MINE,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_MINE),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_MINE)));

        KILL_MOB = new RecipeType(
                RECIPE_TYPE_KILL_MOB,
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_KILL_MOB),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_KILL_MOB)));

        INTERACT = new RecipeType(
                RECIPE_TYPE_INTERACT,
                new CustomItemStack(
                        Material.STICK,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_INTERACT),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_INTERACT)));

        WAIT = new RecipeType(
                RECIPE_TYPE_WAIT,
                new CustomItemStack(
                        Material.CLOCK,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_WAIT),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_WAIT)));

        NULL = new RecipeType(
                RECIPE_TYPE_NULL,
                new CustomItemStack(Material.AIR));


        VANILLA_CRAFTING = new RecipeType(
                RECIPE_TYPE_VANILLA_CRAFTING,
                new CustomItemStack(
                        Material.CRAFTING_TABLE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_VANILLA_CRAFTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_VANILLA_CRAFTING)));
    }
}
