package org.irmc.industrialrevival.implementation.items.machines;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormulas;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.process.IOperation;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.items.register.ChemicalCompoundSetup;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ElectrolyticMachine extends ElectricMachine {
    public static final Map<ItemStack, Decomposer> decomposers = new HashMap<>();
    public static final ItemStack STATUS_ICON = new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            "&cRunning Status"
    ).getBukkit();
    // For status display
    public static final Map<Location, ElectrolyticOperation> operationRecord = new HashMap<>();
    public static final List<MachineRecipe> recipes = new ArrayList<>();

    static {
        IRRegistry.getInstance().getChemicalFormulas().values().forEach(formula -> {
            if (formula.getConditions().length == 1 && formula.getConditions()[0] == ReactCondition.ELECTROLYSIS) {
                recipes.add(new MachineRecipe(0, 0, itemStackize(formula.getInput()), itemStackize(formula.getOutput())));
            }
        });
    }

    @Override
    public MatrixMenuDrawer getMatrixMenuDrawer() {
        return new MatrixMenuDrawer(36)
                .addLine("AAAASAAAA")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addExplain("A", "Input and output border", MenuUtil.INPUT_AND_OUTPUT_BORDER)
                .addExplain("S", "Status", STATUS_ICON);
    }

    public static ItemStack getStatus(BlockTickEvent event) {
        var operation = operationRecord.get(event.getBlock().getLocation());

        var icon = STATUS_ICON.clone();

        var extraLore = getStatus(operation);
        if (!extraLore.isEmpty()) {
            icon.setType(Material.GREEN_STAINED_GLASS_PANE);
            icon.lore();
        }

        return icon;
    }

    public static List<Component> getStatus(ElectrolyticOperation operation) {
        List<Component> lore = new ArrayList<>();
        for (var formula : operation.getRunning()) {
            lore.add(formula.humanize(false).color(TextColor.color(formula.hashCode() % 16777216)));
        }
        return lore;
    }

    @Override
    public void tick(@NotNull BlockTickEvent event) {
        var menu = event.getMenu();
        decompose(menu);
        var operation = findNextOperation(menu);
        operationRecord.put(event.getBlock().getLocation(), operation);
    }

    public ElectrolyticOperation findNextOperation(MachineMenu menu) {
        // todo
        return null;
    }

    public void decompose(MachineMenu menu) {
        // decompose DIRT -> Al2O3, Fe2O3, CaCO3, H2O, N2, CO2, O2
        for (var slot : getInputSlots()) {
            var item = menu.getItem(slot);
            if (item == null || item.getType() == Material.AIR) {
                continue;
            }
            
            var decomposer = decomposers.get(item);
            if (decomposer != null) {
                var outputs = decomposer.decompose();
                MenuUtil.pushItem(menu, outputs, getOutputSlots());
            }
        }
    }

    @Override
    public int[] getInputSlots() {
        return getMatrixMenuDrawer().getCharPositions("i");
    }

    @Override
    public int[] getOutputSlots() {
        return getInputSlots();
    }

    @Data
    @RequiredArgsConstructor
    public static class ElectrolyticOperation implements IOperation {
        private final List<ChemicalFormula> running;
        private final int total;

        public ElectrolyticOperation(ChemicalFormula formula, int total) {
            this.running = List.of(formula);
            this.total = total;
        }

        @Override
        public void tick() {
        }

        @Override
        public void addProgress(int progress) {
        }

        @Override
        public int getCurrentProgress() {
            return 0;
        }

        @Override
        public int getTotalProgress() {
            return 0;
        }
    }

    @Data
    public static class Decomposer {
        private final Map<ItemStack, Double> weights;
        public Decomposer(ItemStack output, double weight) {
            this((Object) output, (Object) weight);
        }

        public Decomposer(Object... args) {
            double totalWeight = 0;
            Map<ItemStack, Double> map = new HashMap<>();
            for (int i = 0; i < args.length; i += 2) {
                if (args[i] instanceof ItemStack itemStack && args[i + 1] instanceof Double weight) {
                    map.put(itemStack, weight);
                    totalWeight += weight;
                }
            }

            for (var entry : map.entrySet()) {
                entry.setValue(entry.getValue() / totalWeight);
            }

            this.weights = map;
        }

        public List<ItemStack> decompose() {
            Random random = new Random();
            List<ItemStack> outputs = new ArrayList<>();
            for (var entry : weights.entrySet()) {
                if (random.nextDouble() < entry.getValue()) {
                    outputs.add(entry.getKey());
                }
            }
            return outputs;
        }
    }

    public static List<ItemStack> itemStackize(Map<ChemicalCompound, Integer> compounds) {
        List<ItemStack> items = new ArrayList<>();
        for (var compound : compounds.keySet()) {
            items.add(itemStackize(compound));
        }
        return items;
    }

    public static ItemStack itemStackize(ChemicalCompound compound) {
        return ChemicalCompoundSetup.solutions.get(compound).getIcon().clone();
    }
}
