package org.irmc.industrialrevival.api.multiblock.piece;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MaterialStructurePiece extends StructurePiece {
    private final Material material;

    public MaterialStructurePiece(Material material) {
        this.material = material;
    }

    // DO NOT USE THIS METHOD
    @Override
    public boolean matches(Block b) {
        return matches(b.getType());
    }

    public boolean matches(Material material) {
        return material.equals(this.material);
    }

    @Override
    public ItemStack getDisplay() {
        return new ItemStack(material);
    }
}
