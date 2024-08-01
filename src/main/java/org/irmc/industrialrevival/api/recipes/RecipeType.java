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

    public static final RecipeType GRINDSTONE;
    public static final RecipeType VANILLA_SMELTING;
    public static final RecipeType MINE;
    public static final RecipeType KILL_MOB;
    public static final RecipeType INTERACT;
    public static final RecipeType WAIT;
    public static final RecipeType NULL;
    public static final RecipeType VANILLA_CRAFTING;

    private static final Map<UUID, Integer> pageRecord = new HashMap<>();
    private static final RecipeDisplay DEFAULT_RECIPE_DISPLAY = RecipeType::defaultRecipeDisplay;

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

    private static void defaultRecipeDisplay(Player p, SimpleMenu sm, IndustrialRevivalItem item) {
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;
        sm.setItem(0, Constants.BACK_BUTTON.apply(p), ((slot, player, item1, menu, clickType) -> {
            guide.goBack(player);
            return false;
        }));

        int[] recipeSlots = SurvivalGuideImplementation.RECIPE_SLOT;

        List<RecipeContent> recipeContents = RecipeContents.getRecipeContents(item.getId());
        if (recipeContents.isEmpty()) {
            sm.setItem(
                    recipeSlots[4],
                    new CustomItemStack(
                            Material.BARRIER,
                            IndustrialRevival.getInstance()
                                    .getLanguageManager()
                                    .getMsgComponent(p, "misc.recipe_not_found")));

            sm.setItem(Constants.BACKGROUND_ITEM, SimpleMenu.ClickHandler.DEFAULT, 1, 2, 3, 4, 5, 6, 7, 8);
            sm.setItem(7, Constants.BACKGROUND_ITEM, SimpleMenu.ClickHandler.DEFAULT);
            sm.setItem(25, ItemUtils.getCleanedItem(item.getItem()));
        } else {
            recipeContents = getRecipeContentsByPage(recipeContents, pageRecord.getOrDefault(p.getUniqueId(), 1));
            showRecipeContent(p, sm, recipeContents.get(0), recipeContents);
        }

        sm.setSize(45);
    }

    private static void showRecipeContent(
            Player p, SimpleMenu sm, RecipeContent rc, List<RecipeContent> recipeContents) {
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;
        sm.setItem(0, Constants.BACK_BUTTON.apply(p), ((slot, player, item1, menu, clickType) -> {
            guide.goBack(player);
            pageRecord.remove(player.getUniqueId());
            return false;
        }));

        if (rc.recipeType().recipeDisplay != DEFAULT_RECIPE_DISPLAY) {
            rc.recipeType().recipeDisplay.display(p, sm, rc.result());
            return;
        }

        sm.setCloseHandler(pl -> pageRecord.remove(pl.getUniqueId()));

        IndustrialRevivalItem item = rc.result();
        ItemStack[] recipe;
        if (item instanceof RecipeDisplayItem rdi) {
            recipe = rdi.getRecipe(rc.recipeType().getMakerItem());
        } else {
            recipe = item.getRecipe();
        }

        int[] recipeSlots = SurvivalGuideImplementation.RECIPE_SLOT;
        for (int i = 0; i < 9; i++) {
            ItemStack recipeItem = recipe[i];
            if (recipeItem != null) {
                sm.setItem(recipeSlots[i], recipe[i], SimpleMenu.ClickHandler.DEFAULT);
            }
        }

        sm.setItem(19, rc.recipeType().getIcon());

        int index = 0;

        for (int i = 2; i < 7; i++) {
            RecipeContent recipeContent = recipeContents.get(index);
            if (recipeContent != null) {
                sm.setItem(i, recipeContent.getMakerItem(), (slot, player, item1, menu, clickType) -> {
                    pageRecord.put(player.getUniqueId(), 1);
                    showRecipeContent(player, menu, recipeContent, recipeContents);
                    return false;
                });
            } else {
                sm.setItem(i, Constants.BACKGROUND_ITEM, SimpleMenu.ClickHandler.DEFAULT);
            }
            index++;
        }

        ItemStack wikiPageItem = Constants.WIKI_PAGE_BUTTON.apply(p);
        SimpleMenu.ClickHandler wikiHandler;
        if (item.getWikiText() == null) {
            wikiPageItem.setType(Material.BLACK_STAINED_GLASS_PANE);
            wikiHandler = SimpleMenu.ClickHandler.DEFAULT;
            wikiPageItem.editMeta(m -> m.displayName(Component.empty()));
        } else {
            String url = Constants.WIKI_URL + item.getWikiText();
            ClickEvent clickEvent = ClickEvent.openUrl(url);
            Component text =
                    IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, "misc.wiki_page");
            text = text.clickEvent(clickEvent);

            Component finalText = text;
            wikiHandler = (slot, player, item1, menu, clickType) -> {
                p.sendMessage(finalText);
                return false;
            };
        }

        sm.setItem(8, wikiPageItem, wikiHandler);

        int currentPage = pageRecord.getOrDefault(p.getUniqueId(), 1);

        SimpleMenu.ClickHandler previousHandler = (slot, player, item1, menu, clickType) -> {
            if (currentPage > 1) {
                pageRecord.put(player.getUniqueId(), currentPage - 1);
                List<RecipeContent> recipeContentsByPage = getRecipeContentsByPage(recipeContents, currentPage - 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getItem());
                }
            }
            // do nothing
            return false;
        };
        ItemStack previousOne = Constants.PREVIOUS_ONE_BUTTON.apply(p);
        if (currentPage == 1) {
            previousOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(1, previousOne, previousHandler);

        SimpleMenu.ClickHandler nextHandler = (slot, player, item1, menu, clickType) -> {
            if (recipeContents.size() < 6) {
                return false;
            }

            int totalPage = recipeContents.size() / 5 + 1;

            if (currentPage < totalPage) {
                pageRecord.put(player.getUniqueId(), currentPage + 1);
                List<RecipeContent> recipeContentsByPage = getRecipeContentsByPage(recipeContents, currentPage + 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getItem());
                }
            }
            // do nothing
            return false;
        };

        int totalPage = recipeContents.size() % 5 == 0 ? recipeContents.size() / 5 : recipeContents.size() / 5 + 1;

        ItemStack nextOne = Constants.NEXT_ONE_BUTTON.apply(p);
        if (currentPage == totalPage || recipeContents.size() < 6) {
            nextOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(7, nextOne, nextHandler);

        sm.setItem(25, rc.result().getItem());
    }

    private static List<RecipeContent> getRecipeContentsByPage(List<RecipeContent> allRecipeContents, int page) {
        if (allRecipeContents == null) {
            return new ArrayList<>();
        }

        int pageSize = 5;
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize - 1, allRecipeContents.size() - 1);
        int actualSize = endIndex - startIndex + 1;

        List<RecipeContent> result = new ArrayList<>(pageSize);

        for (int i = 0; i < actualSize; i++) {
            result.add(allRecipeContents.get(startIndex + i));
        }

        for (int i = actualSize; i < pageSize; i++) {
            result.add(null);
        }

        return result;
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
    }
}
