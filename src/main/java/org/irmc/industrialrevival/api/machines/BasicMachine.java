package org.irmc.industrialrevival.api.machines;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
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
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicMachine is not related to energy networks, it just turns item A to B.
 * @author balugaq
 */
public abstract class BasicMachine extends AbstractMachine implements ProcessorHolder<MachineOperation>, InventoryBlock {
    private final Map<Location, MachineRecipe> lastMatches = new HashMap<>();
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);

    private int[] INPUT_SLOTS = null;
    private int[] OUTPUT_SLOTS = null;

    public void buildMenu(NamespacedKey id, Component itemName) {
        new MachineMenuPreset(id, itemName) {
            @Override
            public void init() {
                setSize(getMatrixMenuDrawer().getSize());
                withMenuDrawer(getMatrixMenuDrawer());
            }

            @Override
            public void newInstance(@NotNull Block block, @Nullable MachineMenu menu) {
                onNewInstance(block, menu);
            }

            @Override
            public int[] getSlotsByItemFlow(@NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
                if (itemFlow == ItemFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }
        }.register();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void preRegister() throws Exception {
        buildMenu(getId(), getItemName());
        addItemHandlers((BlockTicker) this::tick);
        super.preRegister();
    }

    protected void tick(@NotNull BlockTickEvent event) {
        Block block = event.getBlock();
        MachineMenu menu = event.getMenu();
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

    @NotNull
    @Override
    public MachineProcessor<MachineOperation> getProcessor() {
        return this.processor;
    }

    public void onDone(@NotNull Block block, @Nullable MachineMenu menu, @NotNull MachineOperation operation) {
        if (menu == null) {
            return;
        }
        menu.pushItem(operation.getOutputStacks(), menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW));
    }

    public MatrixMenuDrawer getMatrixMenuDrawer() {
        return new MatrixMenuDrawer(45)
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
            INPUT_SLOTS = getMatrixMenuDrawer().getCharPositions("i");
            return INPUT_SLOTS;
        }
    }

    public int[] getOutputSlots() {
        if (OUTPUT_SLOTS != null) {
            return OUTPUT_SLOTS;
        } else {
            OUTPUT_SLOTS = getMatrixMenuDrawer().getCharPositions("o");
            return OUTPUT_SLOTS;
        }
    }

    public void onNewInstance(@NotNull Block block, @Nullable MachineMenu menu) {

    }
}
