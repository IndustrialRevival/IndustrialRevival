package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.api.recipes.RecipeContents;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.JavaUtil;
import org.irmc.industrialrevival.utils.MenuUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SimpleRecipeDisplayMenu extends PageableMenu<RecipeContent> {
    public SimpleRecipeDisplayMenu(Player player, IndustrialRevivalItem item) {
        this(item.getItemName(), player, 1, RecipeContents.getRecipeContents(item.getId()));
    }
    public SimpleRecipeDisplayMenu(Component title, Player p, int currentPage, List<RecipeContent> contents) {
        super(title, p, currentPage, contents, new HashMap<>());

        displayRecipeTypes();
        displayIngredients();
        displayOutput();

        GuideUtil.addToHistory(PlayerProfile.getProfile(p).getGuideHistory(), this);
    }

    public void displayRecipeTypes() {
        var slots = getDrawer().getCharPositions('t');
        for (int index = 0; index < slots.length; index++) {
            // 这个icon要apply page信息
            if (!insertFirstEmpty(getRecipeTypeAt(index).getIcon(), slots)) {
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
        insertFirstEmpty(getDisplayItem(getOutput()), slots);
    }

    public RecipeContent getRecipeContentAt(int index) {
        return getItems().get(index + getCurrentPage() - 1);
    }

    public RecipeContent getRecipeContent() {
        return getItems().get(getCurrentPage() - 1);
    }

    public RecipeType getRecipeTypeAt(int index) {
        return getRecipeContentAt(index).recipeType();
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
    public MatrixMenuDrawer newDrawer() {
        return new MatrixMenuDrawer(54)
                .addLine("BTBBBBBSB")
                .addLine("BBtttttBB")
                .addLine("b  iii  w")
                .addLine("   iii o ")
                .addLine("   iii   ")
                .addLine("BPBBBBBNB")
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler())
                .addExplain("w", "Wiki", getWikiButton(), GuideUtil::openWiki)
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
        // 从 itemstack 获取 page
        getByPage();
        return false;
    }

    @Override
    public PageableMenu<RecipeContent> newMenu(PageableMenu<RecipeContent> menu, int newPage) {
        return new SimpleRecipeDisplayMenu(getTitle(), getPlayer(), newPage, getItems());
    }
}
