package org.irmc.industrialrevival.api.recipes;

import java.util.*;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.RecipeDisplayItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContent;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContents;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.ItemUtils;
import org.irmc.industrialrevival.core.utils.Keys;
import org.irmc.industrialrevival.core.utils.PersistentDataAPI;
import org.jetbrains.annotations.Nullable;

@Getter
public class RecipeType {

    public static final NamespacedKey RECIPE_TYPE_GRINDSTONE = Keys.customKey("grindstone");
    public static final NamespacedKey RECIPE_TYPE_VANILLA_SMELTING = Keys.customKey("vanilla_smelting");
    public static final NamespacedKey RECIPE_TYPE_MINE = Keys.customKey("mine");
    public static final NamespacedKey RECIPE_TYPE_KILL_MOB = Keys.customKey("kill_mob");
    public static final NamespacedKey RECIPE_TYPE_INTERACT = Keys.customKey("interact");
    public static final NamespacedKey RECIPE_TYPE_WAIT = Keys.customKey("wait");
    public static final NamespacedKey RECIPE_TYPE_NULL = Keys.customKey("null");
    public static final NamespacedKey RECIPE_TYPE_VANILLA_CRAFTING = Keys.customKey("vanilla_crafting");
    public static final NamespacedKey RECIPE_TYPE_SMELTING = Keys.customKey("smelting");

    public static final RecipeType GRINDSTONE;
    public static final RecipeType VANILLA_SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType KILL_MOB;
    public static final RecipeType INTERACT;
    public static final RecipeType WAIT;
    public static final RecipeType NULL;
    public static final RecipeType VANILLA_CRAFTING;
    public static final RecipeType SMELTING;

    private static final Map<UUID, Integer> pageRecord = new HashMap<>();
    static final RecipeDisplay DEFAULT_RECIPE_DISPLAY = new DefaultRecipeDisplay();

    private final NamespacedKey key;
    private final ItemStack icon;
    private final RecipeDisplay recipeDisplay;
    private String makerId;

    public RecipeType(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
    }

    public RecipeType(NamespacedKey key, ItemStack icon, RecipeDisplay recipeDisplay) {
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
    }

    public RecipeType(NamespacedKey key, ItemStack icon, String makerId) {
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
        this.makerId = makerId;
    }

    public RecipeType(NamespacedKey key, ItemStack icon, String makerId, RecipeDisplay recipeDisplay) {
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
        this.makerId = makerId;
    }

    public RecipeType setMaker(String makerId) {
        this.makerId = makerId;
        return this;
    }

    @Nullable public ItemStack getMaker() {
        if (makerId != null) {
            return IndustrialRevivalItem.getById(makerId).getItem();
        }

        if (icon instanceof IndustrialRevivalItemStack iris) {
            makerId = iris.getId();
            return IndustrialRevivalItem.getById(makerId).getItem();
        } else {
            if (icon == null || icon.getType() == Material.AIR) {
                return null;
            }

            String id = PersistentDataAPI.getString(icon.getItemMeta(), ItemUtils.CLEANED_IR_ITEM_ID, "");
            if (!id.isBlank()) {
                makerId = id;
                return IndustrialRevivalItem.getById(id).getItem();
            }

            return icon;
        }
    }

    public IndustrialRevivalItem getMakerItem() {
        getMaker();
        return IndustrialRevivalItem.getById(makerId);
    }

    @FunctionalInterface
    public interface RecipeDisplay {
        void display(Player p, SimpleMenu sm, IndustrialRevivalItem item);
    }

    static {
        GRINDSTONE = new RecipeType(
                RECIPE_TYPE_GRINDSTONE,
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_GRINDSTONE),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_GRINDSTONE)));

        VANILLA_SMELTING = new RecipeType(
                RECIPE_TYPE_VANILLA_SMELTING,
                new CustomItemStack(
                        Material.BLAST_FURNACE,
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_VANILLA_SMELTING),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_VANILLA_SMELTING)));

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

        NULL = new RecipeType(RECIPE_TYPE_NULL, new CustomItemStack(Material.AIR));

        VANILLA_CRAFTING = new RecipeType(
                RECIPE_TYPE_VANILLA_CRAFTING,
                new CustomItemStack(
                        Material.CRAFTING_TABLE,
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_VANILLA_CRAFTING),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_VANILLA_CRAFTING)));

        SMELTING = new RecipeType(
                RECIPE_TYPE_SMELTING,
                new CustomItemStack(
                        Material.FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_SMELTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_SMELTING)));
    }
}
