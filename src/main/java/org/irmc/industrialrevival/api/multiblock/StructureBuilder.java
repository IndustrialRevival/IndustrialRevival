package org.irmc.industrialrevival.api.multiblock;

import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class StructureBuilder {
    public int[] center;
    public StructurePiece[][][] pieces;
    public StructureBuilder() {
        center = new int[] {0, 0, 0};
    }

    public StructureBuilder setColumn(int layerIndex, int rowIndex, int colIndex, @NotNull StructurePiece piece) {
        pieces[layerIndex][rowIndex][colIndex] = piece;
        return this;
    }

    public StructureBuilder setRow(int layerIndex, int rowIndex, @NotNull StructurePiece[] pieces) {
        System.arraycopy(pieces, 0, this.pieces[layerIndex][rowIndex], 0, pieces.length);
        return this;
    }

    public StructureBuilder setLayer(int layerIndex, @NotNull StructurePiece[][] pieces) {
        System.arraycopy(pieces, 0, this.pieces[layerIndex], 0, pieces.length);
        return this;
    }

    public StructureBuilder setPieces(@NotNull StructurePiece[][][] pieces) {
        this.pieces = pieces;
        return this;
    }

    public StructureBuilder setCenter(int[] center) {
        this.center = center;
        return this;
    }

    public StructureBuilder setCenter(int x, int y, int z) {
        return this.setCenter(new int[] {x, y, z});
    }

    public Structure build() {
        return new Structure(this.pieces, this.center);
    }
}
