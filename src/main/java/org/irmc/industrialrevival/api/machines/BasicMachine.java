package org.irmc.industrialrevival.api.machines;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 */
public class BasicMachine extends AbstractMachine {

    private Map<Location, MachineRecipe> lastMatches;
    public BasicMachine(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
                new BlockTicker() {
                    @Override
                    public void onTick(Block block, MachineMenuPreset menuPreset, IRBlockData data) {
                        tick(block, menuPreset, data.getMachineMenu());
                    }
                }
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenuPreset menuPreset, MachineMenu menu) {
        Map<ItemStack, Integer> inputs = new HashMap<>();
        for (int slot : menuPreset.getSlotsByItemFlow(ItemFlow.INSERT)) {
            ItemStack stack = menu.getItem(slot);
            if (stack != null && !stack.getType().isAir()) {
                inputs.merge(stack, stack.getAmount(), Integer::sum);
            }
        }

        Location location = block.getLocation();
        MachineRecipe lastMatch = lastMatches.get(location);
        if (lastMatch == null) {
            lastMatches.put(location, machineRecipes.findNextRecipe(inputs));
            return;
        }

        // TODO: implement tick logic
    }
}
