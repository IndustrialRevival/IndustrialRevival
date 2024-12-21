package org.irmc.industrialrevival.implementation.multiblock;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.StructureBuilder;
import org.irmc.industrialrevival.api.multiblock.StructureUtil;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ProfessionalLaboratory extends MultiBlock {
    private final ItemStack RECIPE_TYPE_ICON = new CustomItemStack(Material.BLAST_FURNACE, "Combustion Furnace", "A Combustion Furnace", "This block is a MultiBlock structure that can be used to create Combustion Recipes.", "For testing purposes only so far.").getBukkit();
    private final Map<ItemStack[], ItemStack> RECIPES = new HashMap<>();

    private final RecipeType RECIPE_TYPE = new RecipeType(getAddon(), getKey(), RECIPE_TYPE_ICON,
            RECIPES::put,
            (input, _) -> RECIPES.remove(input));

    public ProfessionalLaboratory(NamespacedKey key) {
        super(key);

        Material quartz = Material.QUARTZ_BLOCK;
        Material lantern = Material.SEA_LANTERN;
        Material cartography = Material.CARTOGRAPHY_TABLE;
        Material furnace = Material.FURNACE;
        Material loom = Material.LOOM;
        Material smoker = Material.SMOKER;
        Material jukebox = Material.JUKEBOX;
        Material fletching = Material.FLETCHING_TABLE;
        Material blast = Material.BLAST_FURNACE;
        Material smithing = Material.SMITHING_TABLE;
        Material lectern = Material.LECTERN;
        Material glass = Material.WHITE_STAINED_GLASS;
        Material pane = Material.WHITE_STAINED_GLASS_PANE;
        Material air = Material.AIR;

        StructureBuilder sb = new StructureBuilder()
            .setPieces(
                StructureUtil.createStructure(new Material[][][] {
                    {
                        {quartz, quartz, quartz, quartz, quartz},
                        {quartz, cartography, furnace, loom, quartz},
                        {quartz, smoker, quartz, jukebox, quartz},
                        {quartz, fletching, blast, smithing, quartz},
                        {quartz, quartz, quartz, quartz, quartz}
                    },
                    {
                        {pane, pane, pane, pane, pane},
                        {pane, air, air, air, pane},
                        {pane, air, lectern, air, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane}
                    },
                    {
                        {pane, pane, pane, pane, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane}
                    },
                    {
                        {pane, pane, pane, pane, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane},
                        {pane, air, air, air, pane}
                    },
                    {
                        {quartz, quartz, quartz, quartz, quartz},
                        {quartz, glass, glass, glass, quartz},
                        {quartz, glass, lantern, glass, quartz},
                        {quartz, glass, glass, glass, quartz},
                        {quartz, quartz, quartz, quartz, quartz}
                    }
                })
            );
        setStructure(sb.build());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        // todo
        IndustrialRevival.getInstance().getLogger().info("WoodenPress interacted by " + event.getPlayer().getName());
    }
}
