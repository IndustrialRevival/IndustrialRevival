package org.irmc.industrialrevival.api.machines;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.InventoryBlock;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.process.MachineProcessor;
import org.irmc.industrialrevival.api.machines.process.ProcessorHolder;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.menu.MenuDrawer;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 */
public abstract class BasicMachine extends AbstractMachine implements ProcessorHolder<MachineOperation>, InventoryBlock {
    private final Map<Location, MachineRecipe> lastMatches = new HashMap<>();
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);

    private int[] INPUT_SLOTS = null;
    private int[] OUTPUT_SLOTS = null;

    @Override
    @OverridingMethodsMustInvokeSuper
    protected void preRegister() throws Exception {
        new MachineMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                setSize(getMenuDrawer().getSize());
                addMenuDrawer(getMenuDrawer());
            }

            @Override
            public void newInstance(@NotNull Block block, @Nullable MachineMenu menu) {
                onNewInstance(block, menu);
            }

            @Override
            public int[] getSlotsByItemFlow(@Nonnull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
                if (itemFlow == ItemFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }
        };
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

    public MenuDrawer getMenuDrawer() {
        return new MenuDrawer(45)
                .addLine("IIIBBBOOO")
                .addLine("IiIBBBOoO")
                .addLine("IiIBSBOoO")
                .addLine("IiIBBBOoO")
                .addLine("IIIBBBOOO")
                .addExplain("I", MenuUtil.INPUT_BORDER)
                .addExplain("B", MenuUtil.BACKGROUND)
                .addExplain("O", MenuUtil.OUTPUT_BORDER);
    }

    public int[] getInputSlots() {
        if (INPUT_SLOTS != null) {
            return INPUT_SLOTS;
        } else {
            INPUT_SLOTS = getMenuDrawer().getCharPositions("i");
            return INPUT_SLOTS;
        }
    }

    public int[] getOutputSlots() {
        if (OUTPUT_SLOTS != null) {
            return OUTPUT_SLOTS;
        } else {
            OUTPUT_SLOTS = getMenuDrawer().getCharPositions("o");
            return OUTPUT_SLOTS;
        }
    }
    public void onNewInstance(Block block, MachineMenu menu) {

    }
}
