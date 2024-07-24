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
        NamespacedKey grindstoneKey = new NamespacedKey(IndustrialRevival.getInstance(), "grindstone");
        NamespacedKey smeltingKey = new NamespacedKey(IndustrialRevival.getInstance(), "smelting");
        NamespacedKey mineKey = new NamespacedKey(IndustrialRevival.getInstance(), "mine");
        NamespacedKey killMobKey = new NamespacedKey(IndustrialRevival.getInstance(), "kill_mob");

        GRINDSTONE = new RecipeType(
                grindstoneKey,
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(grindstoneKey),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(grindstoneKey)));

        SMELTING = new RecipeType(
                smeltingKey,
                new CustomItemStack(
                        Material.BLAST_FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(smeltingKey),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(smeltingKey)));

        MINE = new RecipeType(
                mineKey,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(mineKey),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(mineKey)));

        KILL_MOB = new RecipeType(
                killMobKey,
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(killMobKey),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(killMobKey)));

    }
}
