package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * BasicMachine is not related to energy networks, it just turn item A to B.
 */
public class BasicMachine extends AbstractMachine {

    public BasicMachine(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
                (BlockTicker) (block, menu, data) -> tick(block, data.getMachineMenu())
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenu menu) {
        // TODO: implement machine logic here
    }
}
