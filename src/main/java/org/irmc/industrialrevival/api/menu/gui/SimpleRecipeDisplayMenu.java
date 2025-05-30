package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.api.recipes.RecipeContents;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

@Getter
public class SimpleRecipeDisplayMenu extends PageableMenu<RecipeContent> {
    public static final NamespacedKey PAGE_KEY = KeyUtil.customKey("current_page");
    public SimpleRecipeDisplayMenu(Player player, IndustrialRevivalItem item) {
        this(item.getItemName(), player, PlayerProfile.getProfile(player), 1, RecipeContents.getRecipeContents(item.getId()));
    }

    public SimpleRecipeDisplayMenu(Component title, Player p, PlayerProfile playerProfile, int currentPage, List<RecipeContent> contents) {
        super(title, p, playerProfile, currentPage, contents, new HashMap<>());

        if (contents.isEmpty()) {
            return;
        }

        displayRecipeTypes();
        displayIngredients();
        displayOutput();
        displayWiki();

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    @Override
    public void open(Player... players) {
        if (!getItems().isEmpty()) {
            super.open(players);
        }
    }

    public void displayRecipeTypes() {
        var slots = getDrawer().getCharPositions('t');
        for (int index = 0; index < slots.length; index++) {
            var recipeType = getRecipeTypeAt(index);
            if (recipeType == null) {
                if (!insertFirstEmpty(MenuUtil.BACKGROUND.clone(), slots)) {
                    break;
                }
                continue;
            }

            var item = new CustomItemStack(recipeType.getIcon());
            item.setPDCData(PAGE_KEY, PersistentDataType.INTEGER, index);
            if (!insertFirstEmpty(item.getBukkit(), slots)) {
                break;
            }
        }
    }

    public void displayIngredients() {
        var slots = getDrawer().getCharPositions('i');
        for (var item : getIngredients()) {
            if (!insertFirstEmpty(item, slots)) {
                break;
            }
        }
    }

    public void displayOutput() {
        var slots = getDrawer().getCharPositions('o');
        insertFirstEmpty(getDisplayItem0(getOutput()), slots);
    }

    public void displayWiki() {
        var item = getWikiButton();
        if (item != null) {
            insertFirstEmpty(item, getDrawer().getCharPositions('w'));
        }
    }

    @Nullable
    public RecipeContent getRecipeContentAt(int index) {
        var offset = getCurrentPage() * 2 >= getItems().size() ? 0 : getCurrentPage() - 1;
        var f = index + offset;
        if (f >= getItems().size()) {
            return null;
        }

        return getItems().get(f);
    }

    public RecipeContent getRecipeContent() {
        return getItems().get(getCurrentPage() - 1);
    }

    @Nullable
    public RecipeType getRecipeTypeAt(int index) {
        var e = getRecipeContentAt(index);
        if (e != null) {
            return e.recipeType();
        } else {
            return null;
        }
    }

    public ItemStack[] getIngredients() {
        return getRecipeContent().recipe();
    }

    public IndustrialRevivalItem getOutput() {
        return getRecipeContent().result();
    }

    /**
     * Shut up, compiler
     */
    @Override
    public ItemStack getDisplayItem(RecipeContent item) {
        return null;
    }

    @Override
    public @NotNull MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(45)
                .addLine("BbtttttSB")
                .addLine("   iii  w")
                .addLine("   iii o ")
                .addLine("   iii   ")
                .addLine("BPBBBBBNB");
        return drawer;
    }

    @Override
    public @NotNull MatrixMenuDrawer explainDrawer(@NotNull MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler())
                .addExplain("w", "Wiki", GuideUtil::openWiki)
                .addExplain("t", "Recipe Type", this::pageJumper)
                .addExplain("i", "Ingredients", GuideUtil::lookup)
                .addExplain("o", "Output", GuideUtil::lookup);
    }

    public ItemStack getWikiButton() {
        var s = getOutput().getWikiText();
        if (s != null) {
            return GuideUtil.getWikiButton(s);
        } else {
            return null;
        }
    }

    public boolean pageJumper(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return pageJumper(player, itemStack);
    }

    public boolean pageJumper(Player player, ItemStack itemStack) {
        int page = DataUtil.getPDC(itemStack, PAGE_KEY, PersistentDataType.INTEGER);
        var menu = getByPage(page);
        if (menu != null) {
            menu.open(player);
        }

        return false;
    }

    @Override
    public PageableMenu<RecipeContent> newMenu(PageableMenu<RecipeContent> menu, int newPage) {
        return new SimpleRecipeDisplayMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems());
    }
}
