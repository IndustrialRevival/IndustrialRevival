package org.irmc.industrialrevival.implementation.multiblock;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.StructureBuilder;
import org.irmc.industrialrevival.api.multiblock.StructureUtil;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CombustionFurnace extends MultiBlock {
    private @Getter final ItemStack RECIPE_TYPE_ICON = new CustomItemStack(Material.BLAST_FURNACE, "Combustion Furnace", "A Combustion Furnace", "This block is a MultiBlock structure that can be used to create Combustion Recipes.", "For testing purposes only so far.");
    private @Getter final Map<ItemStack[], ItemStack> RECIPES = new HashMap<>();
    private @Getter final RecipeType RECIPE_TYPE = new RecipeType(getKey(), RECIPE_TYPE_ICON,
            RECIPES::put,
            (input, output) -> {
                RECIPES.remove(input);
            });

    public CombustionFurnace(NamespacedKey key) {
        super(key);
        StructureBuilder sb = new StructureBuilder()
            .setPieces(StructureUtil.createCube(Material.BRICKS, 3))
            .setColumn(1, 0, 1, StructureUtil.material(Material.FURNACE))
            .setCenter(1, 0, 1);
        setStructure(sb.build());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {
        // todo
    }
}
