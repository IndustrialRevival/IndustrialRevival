package org.irmc.industrialrevival.implementation.items.chemistry;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompounds;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.compounds.CompoundContainerHolder;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.machines.process.IOperation;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.api.elements.reaction.Decomposer;
import org.irmc.industrialrevival.implementation.items.register.ChemicalCompoundSetup;
import org.irmc.industrialrevival.utils.ColorUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.industrialrevival.utils.NumberUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ItemStack -> Internal Data -> ItemStack
 *
 * @author balugaq
 */
public abstract class Reactor extends BasicMachine {
    public Reactor() {
        loadDecomposers();
        loadRecipes();
    }

    public static final double DEFAULT_THRESHOLD = 0.1;
    public static final ItemStack DEFAULT_STATUS_ICON = new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            "&cRunning Status"
    ).getBukkit();

    public final CompoundContainerHolder holder = new CompoundContainerHolder();
    public final Map<ItemStack, Decomposer> decomposers = new HashMap<>();
    public final List<MachineRecipe> recipes = new ArrayList<>();

    public static ItemStack getBaseStatusIcon() {
        return DEFAULT_STATUS_ICON;
    }

    public static double getThreshold() {
        return DEFAULT_THRESHOLD;
    }

    public static List<Component> getStatusIcon(ReactOperation operation) {
        List<Component> lore = new ArrayList<>();
        var formula = operation.getRunning();
        lore.add(formula.humanize(false).color(TextColor.color(ColorUtil.generateFormulaColor(formula))));
        return lore;
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
        Solution solution = ChemicalCompoundSetup.solutions.get(compound.getName());
        ItemStack itemStack = solution.getIcon().clone();
        solution.setMass(itemStack, mass);
        return itemStack;
    }

    public static Map<ChemicalCompound, Double> asDataLevel(SimpleMenu menu, int[] inputSlots) {
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

    public abstract void loadRecipes();

    public void loadDecomposers() {
        decomposers.put(new ItemStack(Material.DIRT), new Decomposer(
                ChemicalCompounds.Al2O3, 1,
                ChemicalCompounds.Fe2O3, 1,
                ChemicalCompounds.CaCO3, 1,
                ChemicalCompounds.H2O, 1,
                ChemicalCompounds.N2, 1,
                ChemicalCompounds.CO2, 1,
                ChemicalCompounds.O2, 1
        ));
        decomposers.put(new ItemStack(Material.POTION), new Decomposer(
                ChemicalCompounds.H2O, 1
        ));
        decomposers.put(new ItemStack(Material.WATER_BUCKET), new Decomposer(
                ChemicalCompounds.H2O, 1
        ));
    }

    public ItemStack getStatusIcon(Location location, @Nullable Reactor.ReactOperation operation) {
        var icon = getBaseStatusIcon().clone();

        List<Component> extraLore = new ArrayList<>();
        for (var entry : holder.getOrNew(location).getMixed().entrySet()) {
            extraLore.add(Component.text(NumberUtil.toSubscript(entry.getKey().getName()) + "=" + NumberUtil.round(entry.getValue(), 4)).color(TextColor.color(ColorUtil.generateMoleColor(entry.getKey()))));
        }

        if (operation != null) {
            extraLore.addAll(getStatusIcon(operation));
        }

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

    public void input(MachineMenu menu) {
        var map = asDataLevel(menu, getInputSlots());
        holder.mix(menu.getLocation(), map);
    }

    public void output(MachineMenu menu, Set<ChemicalCompound> take) {
        if (take.isEmpty()) {
            return;
        }

        var all = holder.getOrNew(menu.getLocation()).getMixed();
        var fixed = all.entrySet().stream()
                .filter(entry -> take.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var items = asItemLevel(fixed);
        holder.clear(menu.getLocation(), take);
        menu.pushItem(items, getOutputSlots());
    }

    @Override
    public void tick(@NotNull BlockTickEvent event) {
        var menu = event.getMenu();

        decompose(menu);

        var operation = findNextOperation(menu);
        if (operation == null) {
            updateMenu(menu, null);
            return;
        }

        var location = menu.getLocation();

        double sum = 0D;
        for (var d : operation.getProduce().values()) {
            sum += d;
        }

        if (sum < getThreshold()) {
            Set<ChemicalCompound> take = new HashSet<>(operation.getProduce().keySet());

            ChemicalCompound maxConsumed = null;
            double maxConsumedValue = Double.MAX_VALUE;
            for (var entry : operation.getConsume().entrySet()) {
                var value = entry.getValue();
                if (value < maxConsumedValue) {
                    maxConsumed = entry.getKey();
                    maxConsumedValue = value;
                }
            }

            take.add(maxConsumed);

            output(menu, take);
        } else {
            consume(location, operation.getConsume());
            store(location, operation.getProduce());
        }

        updateMenu(menu, operation);
    }

    public void consume(Location location, Map<ChemicalCompound, Double> compounds) {
        holder.consume(location, compounds);
    }

    public void store(Location location, Map<ChemicalCompound, Double> compounds) {
        holder.mix(location, compounds);
    }

    public void updateMenu(MachineMenu menu) {
        updateMenu(menu, null);
    }

    public void updateMenu(MachineMenu menu, ReactOperation operation) {
        if (menu.hasViewer()) {
            menu.setItem(getStatusSlot(), getStatusIcon(menu.getLocation(), operation));
        }
    }

    public int getStatusSlot() {
        return getMatrixMenuDrawer().getCharPositions("S")[0];
    }

    public ReactOperation findNextOperation(MachineMenu menu) {
        ReactResult result = ReactHelper.react0(new ReactCondition[]{ReactCondition.ELECTROLYSIS}, holder.getOrNew(menu.getLocation()).getMixed());
        if (result == ReactResult.FAILED) {
            return null;
        }

        return new ReactOperation(result.formula(), result.consume(), result.produce());
    }

    public void decompose(MachineMenu menu) {
        // decompose DIRT -> Al2O3, Fe2O3, CaCO3, H2O, N2, CO2, O2
        // decompose WATER_BOTTLE, WATER_BUCKET -> H2O
        for (var slot : getInputSlots()) {
            var item = menu.getItem(slot);
            if (item == null || item.getType() == Material.AIR) {
                continue;
            }

            var decomposer = decomposers.get(item.asOne());
            if (decomposer != null) {
                decomposer.decompose(holder, menu.getLocation());
                item.setAmount(item.getAmount() - 1);
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
    public static class ReactOperation implements IOperation {
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
}
