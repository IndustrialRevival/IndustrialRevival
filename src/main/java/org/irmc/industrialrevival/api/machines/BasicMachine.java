package org.irmc.industrialrevival.api.machines;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.process.MachineProcessor;
import org.irmc.industrialrevival.api.machines.process.ProcessorHolder;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 */
public abstract class BasicMachine extends AbstractMachine implements ProcessorHolder<MachineOperation> {
    private final Map<Location, MachineRecipe> lastMatches = new HashMap<>();
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers((BlockTicker) (block, menu, data) -> tick(block, menu));
        super.preRegister();
    }

    protected void tick(@Nonnull Block block, @Nullable MachineMenu menu) {
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
                lastMatches.put(location, this.machineRecipes.findNextRecipe(inputs));
                return;
            } else {
                if (!lastMatch.isMatch(inputs)) {
                    lastMatches.remove(location);
                    return;
                }
            }
            processor.startProcess(location, new MachineOperation(lastMatch));
            for (ItemStackReference item : lastMatch.getInputs().keySet()) {
                menu.consumeItem(item, lastMatch.getInputs().get(item));
            }
        } else {
            if (operation.isDone()) {
                if (menu.fits(operation.getOutputStacks())) {
                    onDone(block, menu, operation);
                    processor.stopProcess(location);
                }
            } else {
                operation.tick();
            }
        }
    }

    @Nonnull
    @Override
    public MachineProcessor<MachineOperation> getProcessor() {
        return this.processor;
    }

    public void onDone(Block block, MachineMenu menu, MachineOperation operation) {
        menu.pushItem(operation.getOutputStacks(), menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW));
    }
}
