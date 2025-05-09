package org.irmc.industrialrevival.implementation.items.machines;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.process.IOperation;
import org.irmc.industrialrevival.api.machines.process.MachineProcessor;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormulas;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.process.IOperation;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.items.chemistry.Solution;
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
  
    public static final List<MachineRecipe> recipes = new ArrayList<>();
    public static final MachineProcessor<ElectrolyticOperation> processor = new MachineProcessor<>();

    static {
        IRRegistry.getInstance().getChemicalFormulas().values().forEach(formula -> {
            if (formula.getConditions().length == 1 && formula.getConditions()[0] == ReactCondition.ELECTROLYSIS) {
                recipes.add(new MachineRecipe(0, 0, asRawItemLevel(formula.getInput()), asRawItemLevel(formula.getOutput())));
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
                .addExplain("S", "Status", STATUS_ICON)
                .addExplain("i", "Item slot", new ItemStack(Material.AIR), (player, clicked, _, _, _) -> {
                    var cursor = player.getItemOnCursor();
                    if (clicked != null && clicked.getType() != Material.AIR) {
                        return false;
                    } else {
                        return IndustrialRevivalItem.getByItem(cursor) instanceof ChemReactable;
                    }
                });
    }

    @NotNull
    public static MachineProcessor<ElectrolyticOperation> getProcessor0() {
        return processor;
    }

    public static ItemStack getStatus(Location location) {
        var operation = getProcessor0().getProcess(location);

        var icon = STATUS_ICON.clone();

        var extraLore = getStatus(operation);
        if (!extraLore.isEmpty()) {
            icon.setType(Material.GREEN_STAINED_GLASS_PANE);
            var lore = icon.lore();
            if (lore != null) {
                lore.addAll(extraLore);
            } else {
                lore = extraLore;
            }
            icon.lore(lore);
        }

        return icon;
    }

    public static List<Component> getStatus(ElectrolyticOperation operation) {
        List<Component> lore = new ArrayList<>();
        var formula = operation.getRunning();
        lore.add(formula.humanize(false).color(TextColor.color(formula.hashCode() % 16777216)));
        return lore;
    }

    @Override
    public void tick(@NotNull BlockTickEvent event) {
        var menu = event.getMenu();
        decompose(menu);

        var current = getProcessor0().getProcess(menu.getLocation());
        if (current == null) {
            var operation = findNextOperation(menu);
            getProcessor0().startProcess(menu, operation);
            if (menu.hasViewer()) {
                updateMenu(menu);
            }
        } else {
            // 将输入槽的东西转为数据层，并清空
            var map = asDataLevel(menu, getInputSlots());
            for (var entry : current.consume.entrySet()) {
                map.replace(entry.getKey(), map.get(entry.getKey()) - entry.getValue());
            }
            for (var entry : current.produce.entrySet()) {
                map.replace(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
            }

            // 重新输出
            var items = asItemLevel(map);
            menu.pushItem(items, getOutputSlots());
        }
    }

    public void updateMenu(MachineMenu menu) {
        menu.setItem(getStatusSlot(), getStatus(menu.getLocation()));
    }

    public int getStatusSlot() {
        return getMatrixMenuDrawer().getCharPositions("S")[0];
    }

    public ElectrolyticOperation findNextOperation(MachineMenu menu) {
        List<ItemStack> inputs = new ArrayList<>();
        for (var slot : getInputSlots()) {
            var item = menu.getItem(slot);
            inputs.add(item);
        }

        ReactResult result = ReactHelper.react(new ReactCondition[] {ReactCondition.ELECTROLYSIS}, inputs);
        if (result == ReactResult.FAILED) {
            return null;
        }

        return new ElectrolyticOperation(result.formula(), result.consume(), result.produce());
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
        private final ChemicalFormula running;
        private final Map<ChemicalCompound, Double> consume;
        private final Map<ChemicalCompound, Double> produce;


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
            this(output, (Object) weight);
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

    public static List<ItemStack> asItemLevel(Map<ChemicalCompound, Double> compounds) {
        List<ItemStack> items = new ArrayList<>();
        for (var entry : compounds.entrySet()) {
            items.add(asItemLevel(entry.getKey(), entry.getValue()));
        }
        return items;
    }

    public static List<ItemStack> asRawItemLevel(Map<ChemicalCompound, Integer> compounds) {
        List<ItemStack> items = new ArrayList<>();
        for (var compound : compounds.keySet()) {
            items.add(asItemLevel(compound));
        }
        return items;
    }

    public static ItemStack asItemLevel(ChemicalCompound compound) {
        return asItemLevel(compound, 0D);
    }

    public static ItemStack asItemLevel(ChemicalCompound compound, double mass) {
        Solution solution = ChemicalCompoundSetup.solutions.get(compound);
        ItemStack itemStack = solution.getIcon().clone();
        solution.setMass(itemStack, mass);
        return itemStack;
    }

    public static Map<ChemicalCompound, Double> asDataLevel(MachineMenu menu, int[] inputSlots) {
        Map<ChemicalCompound, Double> map = new HashMap<>();
        for (int slot : inputSlots) {
            var item = menu.getItem(slot);
            if (item != null && item.getType() != Material.AIR) {
                if (IndustrialRevivalItem.getByItem(item) instanceof ChemReactable reactable) {
                    var compound = reactable.getChemicalCompound(item);
                    var mass = reactable.getMass(item);
                    if (mass > 0) {
                        map.merge(compound, mass, Double::sum);
                    }
                }
                menu.setItem(slot, null);
            }
        }

        return map;
    }
}
