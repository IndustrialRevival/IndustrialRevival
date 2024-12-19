package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

@Getter
public class RecipeType {
    public static final NamespacedKey RECIPE_TYPE_GRINDSTONE = KeyUtil.customKey("grindstone");
    public static final NamespacedKey RECIPE_TYPE_VANILLA_SMELTING = KeyUtil.customKey("vanilla_smelting");
    public static final NamespacedKey RECIPE_TYPE_MINE = KeyUtil.customKey("mine");
    public static final NamespacedKey RECIPE_TYPE_KILL_MOB = KeyUtil.customKey("kill_mob");
    public static final NamespacedKey RECIPE_TYPE_INTERACT = KeyUtil.customKey("interact");
    public static final NamespacedKey RECIPE_TYPE_WAIT = KeyUtil.customKey("wait");
    public static final NamespacedKey RECIPE_TYPE_NULL = KeyUtil.customKey("null");
    public static final NamespacedKey RECIPE_TYPE_VANILLA_CRAFTING = KeyUtil.customKey("vanilla_crafting");
    public static final NamespacedKey RECIPE_TYPE_SMELTING = KeyUtil.customKey("smelting");
    public static final NamespacedKey RECIPE_TYPE_CRAFTING = KeyUtil.customKey("crafting");
    public static final NamespacedKey RECIPE_TYPE_ELECTROLYSIS = KeyUtil.customKey("electrolysis");
    public static final NamespacedKey RECIPE_TYPE_MULTIBLOCK = KeyUtil.customKey("multiblock");

    public static final RecipeType GRINDSTONE;
    public static final RecipeType VANILLA_SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType KILL_MOB;
    public static final RecipeType INTERACT;
    public static final RecipeType WAIT;
    public static final RecipeType NULL;
    public static final RecipeType VANILLA_CRAFTING;
    public static final RecipeType SMELTING;
    public static final RecipeType CRAFTING;
    public static final RecipeType ELECTROLYSIS;
    public static final RecipeType MULTIBLOCK;
    static final RecipeDisplay DEFAULT_RECIPE_DISPLAY = new DefaultRecipeDisplay();

    static {
        GRINDSTONE = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_GRINDSTONE,
                new CustomItemStack(
                        Material.DISPENSER,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_GRINDSTONE),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_GRINDSTONE)));

        VANILLA_SMELTING = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_VANILLA_SMELTING,
                new CustomItemStack(
                        Material.FURNACE,
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_VANILLA_SMELTING),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_VANILLA_SMELTING)));

        MINE = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_MINE,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_MINE),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_MINE)));

        KILL_MOB = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_KILL_MOB,
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_KILL_MOB),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_KILL_MOB)));

        INTERACT = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_INTERACT,
                new CustomItemStack(
                        Material.STICK,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_INTERACT),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_INTERACT)));

        WAIT = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_WAIT,
                new CustomItemStack(
                        Material.CLOCK,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_WAIT),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_WAIT)));

        NULL = new RecipeType(IndustrialRevival.getInstance(), RECIPE_TYPE_NULL, new CustomItemStack(Material.AIR));

        VANILLA_CRAFTING = new RecipeType(
                IndustrialRevival.getInstance(),
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
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_SMELTING,
                new CustomItemStack(
                        Material.FURNACE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_SMELTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_SMELTING)));

        CRAFTING = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_CRAFTING,
                new CustomItemStack(
                        Material.CRAFTING_TABLE,
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_CRAFTING),
                        IndustrialRevival.getInstance().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_CRAFTING)));
        ELECTROLYSIS = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_ELECTROLYSIS,
                new CustomItemStack(
                        Material.CAULDRON,
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_ELECTROLYSIS),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_ELECTROLYSIS)));
        MULTIBLOCK = new RecipeType(
                IndustrialRevival.getInstance(),
                RECIPE_TYPE_MULTIBLOCK,
                new CustomItemStack(
                        Material.IRON_BLOCK,
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_MULTIBLOCK),
                        IndustrialRevival.getInstance()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_MULTIBLOCK)));
    }

    private final IndustrialRevivalAddon addon;
    private final NamespacedKey key;
    private final ItemStack icon;
    private final RecipeDisplay recipeDisplay;
    private NamespacedKey makerId;
    /**
     * When a recipe is created using {@link CraftMethod#CraftMethod(RecipeType, ItemStack[], ItemStack)} or {@link CraftMethod#CraftMethod(RecipeType, ItemStack[], IndustrialRevivalItem)},
     * the registerRecipeConsumer and unregisterRecipeConsumer
     * will be called to register or unregister the recipe automatically.
     */
    private BiConsumer<ItemStack[], ItemStack> registerRecipeConsumer;
    private BiConsumer<ItemStack[], ItemStack> unregisterRecipeConsumer;

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, RecipeDisplay recipeDisplay) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, NamespacedKey makerId) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
        this.makerId = makerId;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, NamespacedKey makerId, RecipeDisplay recipeDisplay) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
        this.makerId = makerId;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, BiConsumer<ItemStack[], ItemStack> registerRecipeConsumer, BiConsumer<ItemStack[], ItemStack> unregisterRecipeConsumer) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
        this.registerRecipeConsumer = registerRecipeConsumer;
        this.unregisterRecipeConsumer = unregisterRecipeConsumer;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, RecipeDisplay recipeDisplay, BiConsumer<ItemStack[], ItemStack> registerRecipeConsumer, BiConsumer<ItemStack[], ItemStack> unregisterRecipeConsumer) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
        this.registerRecipeConsumer = registerRecipeConsumer;
        this.unregisterRecipeConsumer = unregisterRecipeConsumer;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, NamespacedKey makerId, BiConsumer<ItemStack[], ItemStack> registerRecipeConsumer, BiConsumer<ItemStack[], ItemStack> unregisterRecipeConsumer) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = DEFAULT_RECIPE_DISPLAY;
        this.makerId = makerId;
        this.registerRecipeConsumer = registerRecipeConsumer;
        this.unregisterRecipeConsumer = unregisterRecipeConsumer;
    }

    public RecipeType(IndustrialRevivalAddon addon, NamespacedKey key, ItemStack icon, NamespacedKey makerId, RecipeDisplay recipeDisplay, BiConsumer<ItemStack[], ItemStack> registerRecipeConsumer, BiConsumer<ItemStack[], ItemStack> unregisterRecipeConsumer) {
        this.addon = addon;
        this.key = key;
        this.icon = icon;
        this.recipeDisplay = recipeDisplay;
        this.makerId = makerId;
        this.registerRecipeConsumer = registerRecipeConsumer;
        this.unregisterRecipeConsumer = unregisterRecipeConsumer;
    }

    @Nullable
    public ItemStack getMaker() {
        IndustrialRevivalItem item = IndustrialRevivalItem.getById(makerId);
        if (item != null) {
            return item.getItem().getItemStack();
        } else {
            if (icon == null || icon.getType() == Material.AIR) {
                return null;
            }

            String id = PersistentDataAPI.getString(icon.getItemMeta(), Constants.ItemStackKeys.CLEANED_IR_ITEM_ID, "");
            if (!id.isBlank()) {
                makerId = id;
                return IndustrialRevivalItem.getById(id).getItem().getItemStack();
            }

            return icon;
        }
    }

    public RecipeType setMaker(String makerId) {
        this.makerId = makerId;
        return this;
    }

    public IndustrialRevivalItem getMakerItem() {
        getMaker();
        return IndustrialRevivalItem.getById(makerId);
    }

    public void registerRecipe(ItemStack[] input, ItemStack output) {
        IndustrialRevival.getInstance().getRegistry().registerCraftable(this, output);
        if (registerRecipeConsumer != null) {
            registerRecipeConsumer.accept(input, output);
        }
    }

    public void unregisterRecipe(ItemStack[] input, ItemStack output) {
        IndustrialRevival.getInstance().getRegistry().unregisterCraftable(this, output);
        if (unregisterRecipeConsumer != null) {
            unregisterRecipeConsumer.accept(input, output);
        }
    }

    @FunctionalInterface
    public interface RecipeDisplay {
        void display(Player p, SimpleMenu sm, IndustrialRevivalItem item);
    }
}
