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
import org.irmc.industrialrevival.api.recipes.methods.CraftMethod;
import org.irmc.industrialrevival.dock.IRDock;
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
    public static final NamespacedKey RECIPE_TYPE_BLOCK_DROP = KeyUtil.customKey("block_drop");

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
    public static final RecipeType BLOCK_DROP;

    static final RecipeDisplay DEFAULT_RECIPE_DISPLAY = new DefaultRecipeDisplay();

    static {
        GRINDSTONE = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_GRINDSTONE,
                new CustomItemStack(
                        Material.DISPENSER,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_GRINDSTONE),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_GRINDSTONE)));

        VANILLA_SMELTING = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_VANILLA_SMELTING,
                new CustomItemStack(
                        Material.FURNACE,
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_VANILLA_SMELTING),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_VANILLA_SMELTING)));

        MINE = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_MINE,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_MINE),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_MINE)));

        KILL_MOB = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_KILL_MOB,
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_KILL_MOB),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_KILL_MOB)));

        INTERACT = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_INTERACT,
                new CustomItemStack(
                        Material.STICK,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_INTERACT),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_INTERACT)));

        WAIT = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_WAIT,
                new CustomItemStack(
                        Material.CLOCK,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_WAIT),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_WAIT)));

        NULL = new RecipeType(IRDock.getPlugin(), RECIPE_TYPE_NULL, new CustomItemStack(Material.AIR));

        VANILLA_CRAFTING = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_VANILLA_CRAFTING,
                new CustomItemStack(
                        Material.CRAFTING_TABLE,
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_VANILLA_CRAFTING),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_VANILLA_CRAFTING)));

        SMELTING = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_SMELTING,
                new CustomItemStack(
                        Material.FURNACE,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_SMELTING),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_SMELTING)));

        CRAFTING = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_CRAFTING,
                new CustomItemStack(
                        Material.CRAFTING_TABLE,
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeName(RECIPE_TYPE_CRAFTING),
                        IRDock.getPlugin().getLanguageManager().getRecipeTypeLore(RECIPE_TYPE_CRAFTING)));
        ELECTROLYSIS = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_ELECTROLYSIS,
                new CustomItemStack(
                        Material.CAULDRON,
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_ELECTROLYSIS),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_ELECTROLYSIS)));
        MULTIBLOCK = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_MULTIBLOCK,
                new CustomItemStack(
                        Material.IRON_BLOCK,
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_MULTIBLOCK),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_MULTIBLOCK)));

        BLOCK_DROP = new RecipeType(
                IRDock.getPlugin(),
                RECIPE_TYPE_BLOCK_DROP,
                new CustomItemStack(
                        Material.IRON_PICKAXE,
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeName(RECIPE_TYPE_BLOCK_DROP),
                        IRDock.getPlugin()
                                .getLanguageManager()
                                .getRecipeTypeLore(RECIPE_TYPE_BLOCK_DROP)));
    }

    private final IndustrialRevivalAddon addon;
    private final NamespacedKey key;
    private final ItemStack icon;
    private final RecipeDisplay recipeDisplay;
    private NamespacedKey makerId;
    /**
     * When a recipe is created using {@link CraftMethod#of(RecipeType, ItemStack[], ItemStack)} or {@link CraftMethod#of(RecipeType, ItemStack[], IndustrialRevivalItem)},
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
            return item.getIcon();
        } else {
            if (icon == null || icon.getType() == Material.AIR) {
                return null;
            }

            NamespacedKey id = PersistentDataAPI.getNamespacedKey(icon.getItemMeta(), Constants.ItemStackKeys.CLEANED_IR_ITEM_ID);
            if (id != null) {
                makerId = id;
                return IndustrialRevivalItem.getById(id).getItemStack().clone();
            }

            return icon;
        }
    }

    public RecipeType setMaker(NamespacedKey makerId) {
        this.makerId = makerId;
        return this;
    }

    public IndustrialRevivalItem getMakerItem() {
        getMaker();
        return IndustrialRevivalItem.getById(makerId);
    }

    public void registerRecipe(ItemStack[] input, ItemStack output) {
        IRDock.getPlugin().getRegistry().registerCraftable(this, output);
        if (registerRecipeConsumer != null) {
            registerRecipeConsumer.accept(input, output);
        }
    }

    public void unregisterRecipe(ItemStack[] input, ItemStack output) {
        IRDock.getPlugin().getRegistry().unregisterProduceable(this, output);
        if (unregisterRecipeConsumer != null) {
            unregisterRecipeConsumer.accept(input, output);
        }
    }

    public RecipeType getRecipeType() {
        return this;
    }

    public ItemStack getRecipeTypeIcon() {
        return getIcon();
    }

    @FunctionalInterface
    public interface RecipeDisplay {
        void display(Player p, SimpleMenu sm, IndustrialRevivalItem item);
    }
}
