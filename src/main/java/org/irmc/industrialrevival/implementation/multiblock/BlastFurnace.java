package org.irmc.industrialrevival.implementation.multiblock;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.machines.process.MachineProcessor;
import org.irmc.industrialrevival.api.machines.process.ProcessorHolder;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.StructureBuilder;
import org.irmc.industrialrevival.api.multiblock.StructureUtil;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// todo: unfinished, maybe "blast resistance" is not right, need to check
public class BlastFurnace extends MultiBlock implements ProcessorHolder<MachineOperation> {
    private static final float BLAST_TICK_RATE = 0.05f;
    private static final ItemStack ICON_NO_ENOUGH_FUEL = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "No enough fuel");
    private static final Material PROGRESS_BAR_MATERIAL = Material.FLINT_AND_STEEL;
    private static final ItemStack FUEL_BORDER = new CustomItemStack(Material.COAL_BLOCK, "Fuel Input", "A Fuel Input");
    private static final ItemStack HALTED = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "Halted");
    private static final MachineRecipes recipes = new MachineRecipes();
    private static final MatrixMenuDrawer menuDrawer = new MatrixMenuDrawer(5*9)
            .addLine("IIIIIBBBB")
            .addLine("IiiiIBOOO")
            .addLine("IiiiIPOoO")
            .addLine("IiiiIBOOO")
            .addLine("IFfFIBBBB")
            .addExplain("I", MenuUtil.INPUT_BORDER)
            .addExplain("O", MenuUtil.OUTPUT_BORDER)
            .addExplain("B", MenuUtil.BACKGROUND)
            .addExplain("F", FUEL_BORDER);
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>(this);
    private final Map<Location, Float> fuels = new HashMap<>();
    private @Getter
    final ItemStack RECIPE_TYPE_ICON = new CustomItemStack(Material.BLAST_FURNACE, "Combustion Furnace", "A Combustion Furnace", "This block is a MultiBlock structure that can be used to create Combustion Recipes.", "For testing purposes only so far.");
    private @Getter
    final RecipeType RECIPE_TYPE = new RecipeType(getAddon(), getKey(), RECIPE_TYPE_ICON,
            (inputs, output) -> {
                float blastResistance = 0;
                for (ItemStack itemStack : inputs) {
                    blastResistance += itemStack.getType().getBlastResistance();
                }
                MachineRecipe recipe = new MachineRecipe((int) blastResistance, 0L, List.of(inputs), List.of(output));
                recipes.addRecipe(recipe);
            },
            (inputs, output) -> {
            // todo: not supported unregister recipe yet
            });

    public BlastFurnace(NamespacedKey key) {
        super(key);
        Material iron = Material.IRON_BLOCK;
        Material lava = Material.LAVA;
        Material glass = Material.GRAY_STAINED_GLASS;
        Material composter = Material.COMPOSTER;
        Material table = Material.SMITHING_TABLE;
        StructureBuilder sb = new StructureBuilder()
            .setPieces(
                StructureUtil.createStructure(new Material[][][] {
                        {
                            {iron, iron, iron},
                            {iron, iron, iron},
                            {iron, iron, iron}
                        },
                        {
                            {iron, glass, iron},
                            {glass, lava, glass},
                            {iron, table, iron}
                        },
                        {
                            {iron, glass, iron},
                            {glass, lava, glass},
                            {iron, composter, iron}
                        },
                        {
                            {iron, iron, iron},
                            {iron, iron, iron},
                            {iron, iron, iron}
                        }
                })
            );
        setStructure(sb.build());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Debug.log("BlastFurnace interacted by " + player.getName());
        SimpleMenu menu = new SimpleMenu("Blast Furnace");
        menu.addMenuDrawer(menuDrawer);
        menu.open(player);
        Location location = event.getClickedBlock().getLocation();
        work(menu, location);
    }

    private void work(@Nonnull SimpleMenu menu, @Nonnull Location location) {
        MachineOperation operation = processor.getProcess(location);
        if (operation != null) {
            if (operation.isDone()) {
                if (MenuUtil.fits(menu, operation.getOutputStacks(), getOutputSlots())) {
                    MenuUtil.pushItem(menu, operation.getOutputStacks(), getOutputSlots());
                    updateStatus(menu, location);
                }
            } else {
                if (isBurning(location)) {
                    operation.tick();
                    blastTick(location);
                    updateStatus(menu, location);
                } else {
                    ItemStack fuel = menu.getItem(getFuelSlot());
                    if (isVanillaFuel(fuel)) {
                        fuel.setAmount(fuel.getAmount() - 1);
                        MachineOperation newOperation = findNextRecipe(menu);
                        processor.startProcess(location, newOperation);
                    } else {
                        // no enough fuel
                        menu.setItem(getStatusSlot(), ICON_NO_ENOUGH_FUEL);
                    }
                }
            }
        }
    }

    @Override
    public @NotNull MachineProcessor<MachineOperation> getProcessor() {
        return processor;
    }

    public static int[] getOutputSlots() {
        return menuDrawer.getCharPositions("o");
    }

    public static int[] getInputSlots() {
        return menuDrawer.getCharPositions("i");
    }

    public static int getFuelSlot() {
        return menuDrawer.getCharPositions("f")[0];
    }

    @Nullable
    public MachineOperation findNextRecipe(SimpleMenu menu) {
        Map<ItemStack, Integer> inputMap = new HashMap<>();
        for (int slot : getInputSlots()) {
            ItemStack itemStack = menu.getItem(slot);
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                inputMap.put(itemStack, inputMap.getOrDefault(itemStack, 0) + 1);
            }
        }
        MachineRecipe recipe = recipes.findNextRecipe(inputMap);
        if (recipe != null) {
            return new MachineOperation(recipe);
        }

        return null;
    }

    public boolean isVanillaFuel(@Nullable ItemStack fuel) {
        if (fuel != null && fuel.getType().isAir()) {
            return ItemUtils.isItemSimilar(fuel, new ItemStack(fuel.getType()));
        }
        return false;
    }

    public boolean isBurning(Location location) {
        return getBlastResistance(location) > 0.0f;
    }

    public float getBlastResistance(Location location) {
        return fuels.getOrDefault(location, 0.0f);
    }

    public void blastTick(Location location) {
        float blastResistance = getBlastResistance(location);
        if (blastResistance > 0.0f) {
            blastResistance -= BLAST_TICK_RATE;
            if (blastResistance <= 0.0f) {
                fuels.remove(location);
            } else {
                fuels.put(location, blastResistance);
            }
        }
    }

    public void updateStatus(SimpleMenu menu, Location location) {
        if (menu.hasViewer()) {
            MachineOperation operation = processor.getProcess(location);
            if (operation == null) {
                menu.setItem(getStatusSlot(), HALTED);
            } else {
                menu.setItem(getStatusSlot(), MenuUtil.getProgressBar(PROGRESS_BAR_MATERIAL, operation));
            }
        }
    }

    public int getStatusSlot() {
        return menuDrawer.getCharPositions("P")[0];
    }
}
