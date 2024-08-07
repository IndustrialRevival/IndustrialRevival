package org.irmc.industrialrevival.api.machines;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.process.MachineProcessor;
import org.irmc.industrialrevival.api.machines.process.ProcessorHolder;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.utils.DataUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 */
public class BasicMachine extends AbstractMachine implements ProcessorHolder<MachineOperation> {

    private Map<Location, MachineRecipe> lastMatches;
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);
    public BasicMachine(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
                (BlockTicker) (block, menu, data) -> tick(block, menu)
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenu menu) {

        Map<ItemStack, Integer> inputs = new HashMap<>();
        for (int slot : menu.getPreset().getSlotsByItemFlow(ItemFlow.INSERT)) {
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
        // TODO: 仍然没写完processor的部分
    }

    @NotNull
    @Override
    public MachineProcessor<MachineOperation> getProcessor() {
        return processor;
    }
}
