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
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.api.recipes.RecipeType;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 */
public abstract class BasicMachine extends AbstractMachine implements ProcessorHolder<MachineOperation> {

    private final Map<Location, MachineRecipe> lastMatches = new HashMap<>();
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);

    public BasicMachine(
            @Nonnull ItemGroup group,
            @Nonnull IndustrialRevivalItemStack itemStack,
            @Nonnull RecipeType recipeType,
            @Nonnull ItemStack[] recipe,
            @Nonnull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers((BlockTicker) (block, menu, data) -> tick(block, menu));
        super.preRegister();
    }

    protected void tick(Block block, MachineMenu menu) {
        // TODO: progressing itemstack
        if (block == null) {
            processor.stopProcess(menu.getLocation());
            return;
        }
        if (menu == null) {
            processor.stopProcess(block.getLocation());
            return;
        }
        Location location = block.getLocation();
        MachineOperation operation = processor.getProcess(location);
        if (operation == null) {
            MachineRecipe lastMatch = lastMatches.get(location);
            Map<ItemStack, Integer> inputs = new HashMap<>();
            for (int slot : menu.getPreset().getSlotsByItemFlow(ItemFlow.INSERT)) {
                ItemStack stack = menu.getItem(slot);
                if (stack != null && !stack.getType().isAir()) {
                    inputs.merge(stack, stack.getAmount(), Integer::sum);
                }
            }
            if (lastMatch == null) {
                lastMatches.put(location, machineRecipes.findNextRecipe(inputs));
                return;
            } else {
                if (!lastMatch.isMatch(inputs)) {
                    lastMatches.remove(location);
                    return;
                }
            }
            processor.startProcess(location, new MachineOperation(lastMatch));
            for (ItemStack item : lastMatch.getInputs().keySet()) {
                menu.consumeItem(item, lastMatch.getInputs().get(item));
            }
        } else {
            if (operation.isDone()) {
                if (menu.fits(operation.getOutputStacks())) {
                    menu.pushItem(operation.getOutputStacks(), menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW));
                    processor.stopProcess(location);
                }
            } else {
                operation.tick();
            }
        }

        // TODO: 仍然没写完processor的部分
    }

    @Nonnull
    @Override
    public MachineProcessor<MachineOperation> getProcessor() {
        return this.processor;
    }
}
