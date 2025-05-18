package org.irmc.industrialrevival.implementation.multiblock;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.StructureBuilder;
import org.irmc.industrialrevival.api.multiblock.StructureUtil;
import org.irmc.industrialrevival.api.multiblock.piece.AnyStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.MaterialStructurePiece;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResearchTable extends MultiBlock {
    private
    final ItemStack RECIPE_TYPE_ICON = new CustomItemStack(Material.BLAST_FURNACE, "Combustion Furnace", "A Combustion Furnace", "This block is a MultiBlock structure that can be used to create Combustion Recipes.", "For testing purposes only so far.");
    private
    final Map<ItemStack[], ItemStack> RECIPES = new HashMap<>();
    private
    final RecipeType RECIPE_TYPE = new RecipeType(getAddon(), getKey(), RECIPE_TYPE_ICON,
            RECIPES::put,
            (input, output) -> {
                RECIPES.remove(input);
            });

    public ResearchTable(NamespacedKey key) {
        super(key);
        Material copper = Material.WAXED_COPPER_BLOCK;
        Material quartz = Material.QUARTZ_BLOCK;
        Material quartz_slab = Material.QUARTZ_SLAB;
        Material rod = Material.END_ROD;
        Material brick_slab = Material.BRICK_SLAB;
        Material lectern = Material.LECTERN;
        Material air = Material.AIR;
        StructureBuilder sb = new StructureBuilder()
                .setPieces(
                        StructureUtil.createStructure(new Material[][][]{
                                {
                                        {copper, copper, copper},
                                        {copper, copper, copper},
                                        {copper, copper, copper}
                                },
                                {
                                        {quartz, lectern, quartz},
                                        {quartz_slab, air, quartz_slab},
                                        {air, air, air}
                                },
                                {
                                        {rod, air, rod}
                                },
                                {
                                        {brick_slab, brick_slab, brick_slab}
                                }
                        })
                )
                .replaceAll(new MaterialStructurePiece(air), new AnyStructurePiece())
                .setCenter(1, 0, 1);
        setStructure(sb.build());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        // todo
        IndustrialRevival.getInstance().getLogger().info("WoodenPress interacted by " + event.getPlayer().getName());
    }
}
