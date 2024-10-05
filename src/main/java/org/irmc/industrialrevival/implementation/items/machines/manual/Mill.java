package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockUseHandler;
import org.irmc.industrialrevival.api.machines.AbstractMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.NotNull;

public class Mill extends AbstractMachine {
    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        addItemHandlers(new BlockUseHandler() {
            @Override
            public void onRightClick(PlayerInteractEvent event) {
                // TODO
            }
        });
    }
}
