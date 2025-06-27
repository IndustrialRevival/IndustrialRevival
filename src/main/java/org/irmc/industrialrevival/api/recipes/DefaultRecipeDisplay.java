package org.irmc.industrialrevival.api.recipes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.RecipeDisplayItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;
import org.irmc.industrialrevival.utils.CleanedItemGetter;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.ItemUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class DefaultRecipeDisplay implements RecipeType.RecipeDisplay {
    protected static final Map<UUID, Integer> pageRecord = new HashMap<>();

    private static final int[] recipeSlots = Constants.Guide.GUIDE_RECIPE_SLOTS;

    @Override
    public void display(Player p, SimpleMenu sm, IndustrialRevivalItem item) {
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;
        sm.setItem(0, Constants.Buttons.BACK_BUTTON.apply(p), ((player, _, _, _, _) -> {
            guide.goBack(player);
            return false;
        }));

        int[] recipeSlots = Constants.Guide.GUIDE_RECIPE_SLOTS;

        List<RecipeContent> recipeContents = RecipeContents.getRecipeContents(item.getId());
        if (recipeContents.isEmpty()) {
            sm.setItem(
                    recipeSlots[4],
                    new CustomItemStack(
                            Material.BARRIER,
                            IRDock.getPlugin()
                                    .getLanguageManager()
                                    .getMsgComponent(p, "misc.recipe_not_found")));

            sm.setItem(Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT, 1, 2, 3, 4, 5, 6, 7, 8);
            sm.setItem(7, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);
            sm.setItem(25, CleanedItemGetter.clean(item.getIcon()));
        } else {
            recipeContents = getRecipeContentsByPage(recipeContents, pageRecord.getOrDefault(p.getUniqueId(), 1));
            if (!recipeContents.isEmpty()) {
                showRecipeContent(p, sm, recipeContents.getFirst(), recipeContents);
            }
        }

        sm.setItem(28, Constants.Buttons.ADD_TO_BOOKMARK_BUTTON.apply(p), (player, _, _, _, _) -> {
            SurvivalGuideImplementation.INSTANCE.addBookmark(player, item);
            return false;
        });

        sm.setSize(45);
    }

    private void showRecipeContent(Player p, SimpleMenu sm, RecipeContent rc, List<RecipeContent> recipeContents) {
        setBorder(sm, p, rc);

        IndustrialRevivalItem item = rc.result();
        ItemStack[] recipe;
        if (item instanceof RecipeDisplayItem rdi) {
            recipe = rdi.getRecipe(rc.recipeType().getMakerItem());
        } else {
            recipe = item.getRecipeIngredients(rc.recipeType());
        }

        if (recipe.length < 1) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            ItemStack recipeItem = recipe[i];
            if (recipeItem != null) {
                sm.setItem(recipeSlots[i], recipe[i], ClickHandler.DEFAULT);
            }
        }

        sm.setItem(19, rc.recipeType().getIcon());

        int index = 0;

        for (int i = 2; i < 7; i++) {
            RecipeContent recipeContent = recipeContents.get(index);
            if (recipeContent != null) {
                sm.setItem(i, recipeContent.getMakerItem(), (player, _, _, menu, _) -> {
                    pageRecord.put(player.getUniqueId(), 1);
                    showRecipeContent(player, menu, recipeContent, recipeContents);
                    return false;
                });
            } else {
                sm.setItem(i, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);
            }
            index++;
        }

        int currentPage = pageRecord.getOrDefault(p.getUniqueId(), 1);

        ClickHandler previousHandler = (player, _, _, _, _) -> {
            if (currentPage > 1) {
                pageRecord.put(player.getUniqueId(), currentPage - 1);
                List<RecipeContent> recipeContentsByPage = getRecipeContentsByPage(recipeContents, currentPage - 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getIcon());
                }
            }
            // do nothing
            return false;
        };
        ItemStack previousOne = Constants.Buttons.PREVIOUS_ONE_BUTTON.apply(p);
        if (currentPage == 1) {
            previousOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(1, previousOne, previousHandler);

        ClickHandler nextHandler = (player, _, _, _, _) -> {
            if (recipeContents.size() < 6) {
                return false;
            }

            int totalPage = recipeContents.size() / 5 + 1;

            if (currentPage < totalPage) {
                pageRecord.put(player.getUniqueId(), currentPage + 1);
                List<RecipeContent> recipeContentsByPage = getRecipeContentsByPage(recipeContents, currentPage + 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getIcon());
                }
            }
            // do nothing
            return false;
        };

        int totalPage = recipeContents.size() % 5 == 0 ? recipeContents.size() / 5 : recipeContents.size() / 5 + 1;

        ItemStack nextOne = Constants.Buttons.NEXT_ONE_BUTTON.apply(p);
        if (currentPage == totalPage || recipeContents.size() < 6) {
            nextOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(7, nextOne, nextHandler);
    }

    private List<RecipeContent> getRecipeContentsByPage(List<RecipeContent> allRecipeContents, int page) {
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

    private void showUsage(Player p, NamespacedKey itemId) {
        SimpleMenu sm = new SimpleMenu(
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Constants.Keys.GUIDE_TITLE_KEY));
        Collection<List<RecipeContent>> recipeContents =
                RecipeContents.getRecipeContents().values();
        List<RecipeContent> allAvailableRecipeContents = new ArrayList<>();
        for (List<RecipeContent> recipeContentList : recipeContents) {
            for (RecipeContent recipeContent : recipeContentList) {
                if (recipeContent.maker() == null) {
                    continue;
                }

                if (recipeContent.maker().getId().equals(itemId)) {
                    allAvailableRecipeContents.add(recipeContent);
                }
            }
        }

        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        if (allAvailableRecipeContents.isEmpty()) {
            sm.setItem(0, Constants.Buttons.BACK_BUTTON.apply(p), ((player, _, _, _, _) -> {
                guide.goBack(player);
                return false;
            }));
            sm.setItem(Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT, 1, 2, 3, 4, 5, 6, 7, 8);

            sm.setItem(
                    recipeSlots[4],
                    new CustomItemStack(
                            Material.BARRIER,
                            IRDock.getPlugin()
                                    .getLanguageManager()
                                    .getMsgComponent(p, "misc.recipe_no_usage")));

            return;
        }

        int currentPage = pageRecord.getOrDefault(p.getUniqueId(), 1);

        ClickHandler previousHandler = (player, _, _, _, _) -> {
            if (currentPage > 1) {
                pageRecord.put(player.getUniqueId(), currentPage - 1);
                List<RecipeContent> recipeContentsByPage =
                        getRecipeContentsByPage(allAvailableRecipeContents, currentPage - 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getIcon());
                }
            }
            // do nothing
            return false;
        };

        ItemStack previousOne = Constants.Buttons.PREVIOUS_ONE_BUTTON.apply(p);
        if (currentPage == 1) {
            previousOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(1, previousOne, previousHandler);

        ClickHandler nextHandler = (player, _, _, _, _) -> {
            if (recipeContents.size() < 6) {
                return false;
            }

            int totalPage = recipeContents.size() / 5 + 1;

            if (currentPage < totalPage) {
                pageRecord.put(player.getUniqueId(), currentPage + 1);
                List<RecipeContent> recipeContentsByPage =
                        getRecipeContentsByPage(allAvailableRecipeContents, currentPage + 1);
                for (int i = 2; i < 7; i++) {
                    sm.setItem(i, recipeContentsByPage.get(i - 2).maker().getIcon());
                }
            }
            // do nothing
            return false;
        };

        int totalPage = recipeContents.size() % 5 == 0 ? recipeContents.size() / 5 : recipeContents.size() / 5 + 1;

        ItemStack nextOne = Constants.Buttons.NEXT_ONE_BUTTON.apply(p);
        if (currentPage == totalPage || recipeContents.size() < 6) {
            nextOne.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextOne.editMeta(m -> m.displayName(Component.empty()));
        }

        sm.setItem(7, nextOne, nextHandler);

        sm.setSize(45);
        sm.open(p);
    }

    private void setBorder(SimpleMenu sm, Player p, RecipeContent rc) {
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;
        sm.setItem(0, Constants.Buttons.BACK_BUTTON.apply(p), ((player, _, _, _, _) -> {
            guide.goBack(player);
            pageRecord.remove(player.getUniqueId());
            return false;
        }));

        if (rc.recipeType().getRecipeDisplay() != RecipeType.DEFAULT_RECIPE_DISPLAY) {
            rc.recipeType().getRecipeDisplay().display(p, sm, rc.result());
            return;
        }

        sm.setCloseHandler((pl, menu) -> pageRecord.remove(pl.getUniqueId()));

        IndustrialRevivalItem item = rc.result();

        ItemStack wikiPageItem = Constants.Buttons.WIKI_PAGE_BUTTON.apply(p);
        ClickHandler wikiHandler;
        if (item.getWikiText() == null) {
            wikiPageItem.setType(Material.BLACK_STAINED_GLASS_PANE);
            wikiHandler = ClickHandler.DEFAULT;
            wikiPageItem.editMeta(m -> m.displayName(Component.empty()));
        } else {
            String url = Constants.Misc.WIKI_URL + item.getWikiText();
            ClickEvent clickEvent = ClickEvent.openUrl(url);
            Component text =
                    IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "misc.wiki_page");
            text = text.clickEvent(clickEvent);

            Component finalText = text;
            wikiHandler = (_, _, _, _, _) -> {
                p.sendMessage(finalText);
                return false;
            };
        }

        sm.setItem(8, wikiPageItem, wikiHandler);

        sm.setItem(19, rc.recipeType().getIcon());

        ItemStack resultItem = rc.result().getItemStack().clone();
        ItemUtils.addLore(
                resultItem,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "misc.recipe_show_usage"),
                true);

        sm.setItem(25, resultItem, (player, _, _, _, clickType) -> {
            if (clickType == ClickType.RIGHT || clickType == ClickType.SHIFT_RIGHT) {
                showUsage(player, item.getId());
            }
            return false;
        });
    }
}
