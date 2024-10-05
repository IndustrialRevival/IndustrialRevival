package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MenuDrawer;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.utils.MenuUtil;

import org.jetbrains.annotations.Nullable;
import java.util.Map;

public class ManualGrind extends BasicMachine {
    private static final ItemStack CLICKER_ICON = new CustomItemStack(
            Material.ORANGE_STAINED_GLASS_PANE,
            "§6Clicker",
            "§7Click to start grinding");
    private static final int MENU_SIZE = 27;
    private static final MenuDrawer DRAWER = new MenuDrawer(MENU_SIZE)
            .addLine("IIIDDDOOO")
            .addLine("IiIDCDOoO")
            .addLine("IIIDDDOOO")
            .addExplain("I", MenuUtil.INPUT_BORDER)
            .addExplain("D", MenuUtil.CLICKER_BORDER)
            .addExplain("O", MenuUtil.OUTPUT_BORDER)
            .addExplain("C", CLICKER_ICON);

    public ManualGrind() {
        super();
        addRecipe(0, 0, new ItemStack(Material.COBBLESTONE), new ItemStack(Material.GRAVEL));
    }

    public void onNewInstance(@NotNull Block block, @Nullable MachineMenu menu) {
        if (menu == null) {
            return;
        }

        for (int slot : DRAWER.getCharPositions("C")) {
            menu.setClickHandler(slot, (player, clickedItem, clickedSlot, clickedMenu, clickType) -> {
                Map<ItemStack, Integer> items = MenuUtil.getMenuItemsByItemFlow(clickedMenu, ItemFlow.INSERT, null);
                MachineRecipe recipe = machineRecipes.findNextRecipe(items);
                if (recipe != null) {
                    if (menu.fits(recipe.getOutputs())) {
                        menu.consumeItem(recipe.getInputs());
                        menu.pushItem(recipe.getOutputs());
                    } else {
                        player.sendMessage("Not enough space in output slots");
                    }
                }
                return false;
            });
        }
    }
}
