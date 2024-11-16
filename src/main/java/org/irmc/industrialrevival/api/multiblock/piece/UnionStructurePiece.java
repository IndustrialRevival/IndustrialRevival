package org.irmc.industrialrevival.api.multiblock.piece;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * logical or of its parameters
 */
public class UnionStructurePiece extends StructurePiece {
    @Getter
    private final ItemStack display;
    private final List<StructurePiece> childPieces;

    public UnionStructurePiece(ItemStack display, StructurePiece... childPieces) {
        this.display = display;
        this.childPieces = Arrays.asList(childPieces);
    }

    @Override
    public boolean matches(Block b) {
        for (StructurePiece childPiece : childPieces) {
            if (childPiece.matches(b)) return true;
        }
        return false;
    }
}
