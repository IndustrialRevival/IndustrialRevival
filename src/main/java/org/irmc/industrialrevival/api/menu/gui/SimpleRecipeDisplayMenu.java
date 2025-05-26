package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.api.recipes.RecipeContents;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SimpleRecipeDisplayMenu extends PageableMenu<RecipeContent> {
    public SimpleRecipeDisplayMenu(Player player, IndustrialRevivalItem item) {
        this(item.getItemName().toString(), player, 1, RecipeContents.getRecipeContents(item.getId()));
    }
    public SimpleRecipeDisplayMenu(String title, Player p, int currentPage, List<RecipeContent> contents) {
        super(title, p, currentPage, contents, new HashMap<>());
        drawer = new MatrixMenuDrawer(54)
                .addLine("BTBBBBBSB")
                .addLine("BBtttttBB")
                .addLine("b  iii  w")
                .addLine("   iii o ")
                .addLine("   iii   ")
                .addLine("BPBBBBBNB")
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(), GuideUtil::backHistory);
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());

        GuideUtil.addToHistory(PlayerProfile.getProfile(p).getGuideHistory(), this);
    }

    /**
     * Shut up, compiler
     */
    @Override
    public ItemStack getDisplayItem(RecipeContent item) {
        return null;
    }
}
