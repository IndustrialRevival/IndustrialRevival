package org.irmc.industrialrevival.api.multiblock.piece;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

/**
 * A piece that represents a section of a structure. It allows the following pieces to be placed in any order.
 * {@link SectionStructurePiece} just as wonderful as "a or b"
 */
public class SectionStructurePiece extends StructurePiece {
    @Getter
    private StructurePiece[] pieces;
    private final ItemStack display = new CustomItemStack(Material.CHEST,
            "",
            "Section Structure Piece",
            "Allow the following pieces to be placed in any order:").modifyLore(lore -> {
        for (StructurePiece p : pieces) {
            lore.add(new CustomItemStack(p.getDisplay()).displayName());
        }
    });

    public SectionStructurePiece(StructurePiece... piece) {
        this.pieces = piece;
    }

    @Override
    public boolean matches(Block b) {
        return true;
    }

    @Override
    public ItemStack getDisplay() {
        return display;
    }
}
