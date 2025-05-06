package org.irmc.industrialrevival.implementation.multiblock;

import com.destroystokyo.paper.MaterialTags;
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
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.StructureBuilder;
import org.irmc.industrialrevival.api.multiblock.StructureUtil;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BlastFurnace extends MultiBlock implements ProcessorHolder<MachineOperation> {
    // todo: save
    private static final Map<Location, MachineMenu> menus = new HashMap<>();
    private static final float FUEL_TICK_RATE = 0.05f;
    private static final ItemStack ICON_NO_ENOUGH_FUEL = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "No enough fuel");
    private static final Material PROGRESS_BAR_MATERIAL = Material.FLINT_AND_STEEL;
    private static final ItemStack FUEL_BORDER = new CustomItemStack(Material.COAL_BLOCK, "Fuel Input", "A Fuel Input");
    private static final ItemStack HALTED = new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "Halted");
    private static final MachineRecipes recipes = new MachineRecipes();
    private static final MachineMenuPreset preset = new MachineMenuPreset(KeyUtil.customKey("blast_furnace"), "Blast Furnace");
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
    static {
        preset.withMenuDrawer(menuDrawer);
    }
    private final MachineProcessor<MachineOperation> processor = new MachineProcessor<>();
    private final Map<Location, Float> fuels = new HashMap<>();
    private @Getter
    final ItemStack RECIPE_TYPE_ICON = new CustomItemStack(Material.BLAST_FURNACE, "Combustion Furnace", "A Combustion Furnace", "This block is a MultiBlock structure that can be used to create Combustion Recipes.", "For testing purposes only so far.");
    private @Getter
    final RecipeType RECIPE_TYPE = new RecipeType(getAddon(), getKey(), RECIPE_TYPE_ICON,
            (inputs, output) -> {
                int totalBurnTime = inputs.length;
                MachineRecipe recipe = new MachineRecipe(totalBurnTime, 0, List.of(inputs), List.of(output));
                recipes.addRecipe(recipe);
            },
            (inputs, output) -> {
                Map<ItemStack, Integer> inputMap = new HashMap<>();
                for (ItemStack itemStack : inputs) {
                    ItemStack key = itemStack.asOne();
                    inputMap.put(key, inputMap.getOrDefault(key, 0) + itemStack.getAmount());
                }
                synchronized (recipes) {
                    for (MachineRecipe recipe : recipes.getRecipes()) {
                        if (recipe.isMatch(inputMap)) {
                            Map<ItemStack, Integer> o = recipe.getOutputs();
                            Integer value = o.get(output);
                            if (value != null && value == output.getAmount()) {
                                recipes.removeRecipe(recipe);
                                break;
                            }
                        }
                    }
                }
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
            )
            .setCenter(2, 2, 1);
        setStructure(sb.build());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Debug.log("BlastFurnace interacted by " + player.getName());
        Location location = event.getClickedBlock().getLocation();
        if (!menus.containsKey(location)) {
            MachineMenu menu = new MachineMenu(location, preset);
            menus.put(location, menu);
        }
        MachineMenu menu = menus.get(location);
        menu.open(player);
        work(menu, location);
    }

    private void work(@Nonnull MachineMenu menu, @Nonnull Location location) {
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
                        fuels.put(location, getBurnTime(fuel.getType()));
                    } else {
                        // no enough fuel
                        menu.setItem(getStatusSlot(), ICON_NO_ENOUGH_FUEL);
                    }
                }
            }
        } else {
            MachineOperation newOperation = findNextRecipe(menu);
            processor.startProcess(location, newOperation);
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
    public MachineOperation findNextRecipe(MachineMenu menu) {
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
        return getFuel(location) >= FUEL_TICK_RATE;
    }

    public float getFuel(Location location) {
        return fuels.getOrDefault(location, 0.0f);
    }

    public void blastTick(Location location) {
        float fuel = getFuel(location);
        if (fuel > 0.0f) {
            fuel -= FUEL_TICK_RATE;
            if (fuel <= 0.0f) {
                fuels.remove(location);
            } else {
                fuels.put(location, fuel);
            }
        }
    }

    public void updateStatus(MachineMenu menu, Location location) {
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
    public static float getBurnTime(Material material) {
        float time = switch (material) {
            case LAVA_BUCKET -> 100;
            case COAL_BLOCK -> 80;
            case DRIED_KELP_BLOCK -> 20.005f;
            case BLAZE_ROD -> 12;
            case COAL, CHARCOAL -> 8;
            case BAMBOO_RAFT, BAMBOO_CHEST_RAFT -> 6;
            case BAMBOO_HANGING_SIGN -> 4;
            case BAMBOO_BLOCK, STRIPPED_BAMBOO_BLOCK, BAMBOO_PLANKS, BAMBOO_MOSAIC, BAMBOO_PRESSURE_PLATE, BAMBOO_FENCE, BAMBOO_FENCE_GATE, BAMBOO_STAIRS, BAMBOO_MOSAIC_STAIRS, BAMBOO_TRAPDOOR, CRAFTING_TABLE, CARTOGRAPHY_TABLE, FLETCHING_TABLE, SMITHING_TABLE, LOOM, BOOKSHELF, LECTERN, COMPOSTER, CHEST, TRAPPED_CHEST, BARREL, DAYLIGHT_DETECTOR, JUKEBOX, NOTE_BLOCK, BOW, CROSSBOW, FISHING_ROD, LADDER, MANGROVE_ROOTS, CHISELED_BOOKSHELF -> 1.5f;
            case WOODEN_PICKAXE, WOODEN_SHOVEL, WOODEN_HOE, WOODEN_AXE, WOODEN_SWORD, BAMBOO_SIGN, BAMBOO_DOOR, BAMBOO_SLAB, BAMBOO_MOSAIC_SLAB -> 1;
            case BAMBOO_BUTTON, BOWL, STICK, AZALEA -> 0.5f;
            case SCAFFOLDING, BAMBOO -> 0.25f;
            default -> 0;
        };
        if (time == 0) {
            String name = material.name();
            if ("LEAF_LITTER".equals(name)) {
                time = 0.5f;
            }
            else if (name.startsWith("CRIMSON_") ||name.startsWith("WARPED_")) {
                time = 0;
            }
            else if (name.endsWith("_BOAT") || name.endsWith("_CHEST_BOAT")) {
                time = 6;
            }
            else if (name.endsWith("_HANGING_SIGN")) {
                time = 4;
            }
            else if (name.endsWith("_LOG") || name.endsWith("_WOOD") || name.startsWith("STRIPPED_") || name.endsWith("_PLANKS") || (material != Material.STONE_PRESSURE_PLATE && material != Material.HEAVY_WEIGHTED_PRESSURE_PLATE && material != Material.LIGHT_WEIGHTED_PRESSURE_PLATE && MaterialTags.PRESSURE_PLATES.isTagged(material)) || MaterialTags.FENCES.isTagged(material) || MaterialTags.FENCE_GATES.isTagged(material) || material == Material.OAK_STAIRS || material == Material.SPRUCE_STAIRS || material == Material.BIRCH_STAIRS || material == Material.JUNGLE_STAIRS || material == Material.ACACIA_STAIRS || material == Material.DARK_OAK_STAIRS || material == Material.MANGROVE_STAIRS || material == Material.CHERRY_STAIRS || "PALE_STAIRS".equals(name) || (material != Material.IRON_TRAPDOOR && MaterialTags.TRAPDOORS.isTagged(material)) || name.endsWith("_BANNER")) {
                time = 1.5f;
            }
            else if (name.endsWith("_SIGN") || (material != Material.IRON_DOOR && MaterialTags.DOORS.isTagged(material))) {
                time = 1;
            }
            else if (material == Material.OAK_SLAB || material == Material.SPRUCE_SLAB || material == Material.BIRCH_SLAB || material == Material.JUNGLE_SLAB || material == Material.ACACIA_SLAB || material == Material.DARK_OAK_SLAB || material == Material.MANGROVE_SLAB || material == Material.CHERRY_SLAB || "PALE_SLAB".equals(name)) {
                time = 0.75f;
            }
            else if (material != Material.STONE_BUTTON && name.endsWith("_BUTTON") || name.endsWith("_SAPLING") || name.endsWith("_WOOL")) {
                time = 0.5f;
            }
            else if (name.endsWith("_CARPET")) {
                time = 0.335f;
            }
        }

        return time;
    }
}
