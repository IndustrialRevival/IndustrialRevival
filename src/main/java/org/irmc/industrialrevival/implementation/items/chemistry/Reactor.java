package org.irmc.industrialrevival.implementation.items.chemistry;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.irmc.industrialrevival.api.items.attributes.CompoundContainerHolder;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.items.attributes.EnvironmentHolder;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.api.elements.reaction.Decomposer;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.implementation.items.register.ChemicalCompoundSetup;
import org.irmc.industrialrevival.utils.ColorUtil;
import org.irmc.industrialrevival.utils.NumberUtil;
import org.irmc.industrialrevival.utils.TextUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ItemStack -> {@link CompoundContainerHolder} -> ItemStack
 *
 * @author balugaq
 */
public abstract class Reactor extends BasicMachine implements CompoundContainerHolder, EnvironmentHolder {
    public Reactor() {
        loadDecomposers();
        loadRecipes();
    }

    public static final double DEFAULT_THRESHOLD = 0.1;
    public static final ItemStack DEFAULT_STATUS_ICON = new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            "&cRunning Status"
    ).getBukkit();

    public final Map<ItemStack, Decomposer> decomposers = new HashMap<>();
    public final List<MachineRecipe> recipes = new ArrayList<>();

    public static ItemStack getBaseStatusIcon() {
        return DEFAULT_STATUS_ICON;
    }

    public static double getThreshold() {
        return DEFAULT_THRESHOLD;
    }

    @Nonnull
    public static List<Component> getStatusIcon(@Nullable List<ReactOperation> operations) {
        if (operations == null) {
            return new ArrayList<>();
        }

        List<Component> lore = new ArrayList<>();
        for (var operation : operations) {
            var formula = operation.getRunning();
            lore.add(formula.humanize(false).color(TextColor.color(ColorUtil.generateFormulaColor(formula))));
        }
        return TextUtil.crop(lore, 9, Component::text);
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

    public ItemStack getStatusIcon(Location location, @Nullable List<ReactOperation> operations) {
        var icon = getBaseStatusIcon().clone();

        List<Component> extraLore = new ArrayList<>();
        List<Component> extraLore1 = new ArrayList<>(getStatusIcon(operations));
        List<Component> extraLore2 = new ArrayList<>();
        for (var entry : getOrNewCompoundContainer(location).getMixed().entrySet()) {
            extraLore2.add(Component.text(NumberUtil.toSubscript(entry.getKey().getHumanizedName()) + "=" + NumberUtil.round(entry.getValue(), 4)).color(TextColor.color(ColorUtil.generateMoleColor(entry.getKey()))));
        }
        extraLore2 = TextUtil.crop(extraLore2, 9, Component::text);

        if (extraLore1 != null && !extraLore1.isEmpty()) {
            extraLore.addAll(extraLore1);
        }
        if (extraLore2 != null && !extraLore2.isEmpty()) {
            extraLore.addAll(extraLore2);
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
        mixCompounds(menu.getLocation(), map);
    }

    public void output(MachineMenu menu, Set<ChemicalCompound> take) {
        if (take.isEmpty()) {
            return;
        }

        var all = getOrNewCompoundContainer(menu.getLocation()).getMixed();
        var fixed = all.entrySet().stream()
                .filter(entry -> take.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var items = asItemLevel(fixed);
        if (!items.isEmpty()) {
            clearCompounds(menu.getLocation(), take);
            menu.pushItem(items, getOutputSlots());
        }
    }

    @Override
    public void tick(@NotNull BlockTickEvent event) {
        var menu = event.getMenu();

        decompose(menu);

        var operations = findNextOperations(menu);
        if (operations == null || operations.isEmpty()) {
            updateMenu(menu, null);
            return;
        }

        var location = menu.getLocation();

        for (var operation : operations) {
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

                // todo: maybe we should mix all solution in an ItemStack
                output(menu, take);
            } else {
                consumeCompounds(location, operation.getConsume());
                store(location, operation.getProduce());
            }
        }

        updateMenu(menu, operations);
    }

    public void store(Location location, Map<ChemicalCompound, Double> compounds) {
        mixCompounds(location, compounds);
    }

    public void updateMenu(MachineMenu menu) {
        updateMenu(menu, null);
    }

    public void updateMenu(MachineMenu menu, @Nullable List<ReactOperation> operations) {
        if (menu.hasViewer()) {
            menu.setItem(getStatusSlot(), getStatusIcon(menu.getLocation(), operations));
        }
    }

    public int getStatusSlot() {
        return getMatrixMenuDrawer().getCharPositions("S")[0];
    }

    public abstract Set<ReactCondition> getReactConditions(MachineMenu menu);

    public List<ReactOperation> findNextOperations(MachineMenu menu) {
        var location = menu.getLocation();
        List<ReactResult> result = getOrNewCompoundContainer(location).reactBalanced(getOrNewEnvironment(location), getReactConditions(menu));

        return result.stream()
                .filter(r -> !r.equals(ReactResult.FAILED))
                .map(r -> new ReactOperation(r.formula(), r.consume(), r.produce()))
                .collect(Collectors.toList());
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
                decomposer.decompose(this, menu.getLocation());
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

    @Override
    public @NotNull Environment newEnvironment() {
        var e = new Environment();
        e.setTemperature(20.0D);
        e.setPressure(AIR_PRESSURE);
        e.setHumidity(0.0D);
        e.setRadiation(0.0D);
        return e;
    }
}
