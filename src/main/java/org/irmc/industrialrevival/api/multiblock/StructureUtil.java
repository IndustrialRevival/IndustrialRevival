package org.irmc.industrialrevival.api.multiblock;

import org.bukkit.Material;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.multiblock.piece.AnyStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.IRBlockStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.MaterialStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.SectionStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;

public class StructureUtil {
    public static StructurePiece[][] createLayer(Material material, int size) {
        StructurePiece[][] layer = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                layer[i][j] = new MaterialStructurePiece(material);
            }
        }
        return layer;
    }

    public static StructurePiece[][] createLayer(IndustrialRevivalItem item, int size) {
        StructurePiece[][] layer = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                layer[i][j] = new IRBlockStructurePiece(item);
            }
        }
        return layer;
    }

    public static StructurePiece[] createRow(Material material, int size) {
        StructurePiece[] row = new StructurePiece[size];
        for (int i = 0; i < size; i++) {
            row[i] = new MaterialStructurePiece(material);
        }
        return row;
    }

    public static StructurePiece[] createRow(IndustrialRevivalItem item, int size) {
        StructurePiece[] row = new StructurePiece[size];
        for (int i = 0; i < size; i++) {
            row[i] = new IRBlockStructurePiece(item);
        }
        return row;
    }

    public static StructurePiece[][] createOutline(Material material, int size) {
        StructurePiece[][] outline = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    outline[i][j] = new MaterialStructurePiece(material);
                } else {
                    outline[i][j] = null;
                }
            }
        }
        return outline;
    }

    public static StructurePiece[][] createOutline(IndustrialRevivalItem item, int size) {
        StructurePiece[][] outline = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    outline[i][j] = new IRBlockStructurePiece(item);
                } else {
                    outline[i][j] = null;
                }
            }
        }
        return outline;
    }

    public static StructurePiece[][] createHollow(Material material, int size) {
        StructurePiece[][] hollow = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    hollow[i][j] = null;
                } else {
                    hollow[i][j] = new MaterialStructurePiece(material);
                }
            }
        }
        return hollow;
    }

    public static StructurePiece[][] createHollow(IndustrialRevivalItem item, int size) {
        StructurePiece[][] hollow = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    hollow[i][j] = null;
                } else {
                    hollow[i][j] = new IRBlockStructurePiece(item);
                }
            }
        }
        return hollow;
    }

    public static StructurePiece[][][] createCube(Material material, int size) {
        StructurePiece[][][] cube = new StructurePiece[size][size][size];
        for (int i = 0; i < size; i++) {
            cube[i] = createLayer(material, size);
        }
        return cube;
    }

    public static StructurePiece[][][] createCube(IndustrialRevivalItem item, int size) {
        StructurePiece[][][] cube = new StructurePiece[size][size][size];
        for (int i = 0; i < size; i++) {
            cube[i] = createLayer(item, size);
        }
        return cube;
    }

    public static StructurePiece[][][] createStructure(Material[][][] materials) {
        StructurePiece[][][] structure = new StructurePiece[materials.length][materials[0].length][materials[0][0].length];
        for (int i = 0; i < materials.length; i++) {
            for (int j = 0; j < materials[i].length; j++) {
                for (int k = 0; k < materials[i][j].length; k++) {
                    if (materials[i][j][k] != null) {
                        structure[i][j][k] = new MaterialStructurePiece(materials[i][j][k]);
                    } else {
                        structure[i][j][k] = new MaterialStructurePiece(Material.AIR);
                    }
                }
            }
        }
        return structure;
    }

    public static StructurePiece material(Material material) {
        return new MaterialStructurePiece(material);
    }

    public static StructurePiece ir(IndustrialRevivalItem item) {
        return new IRBlockStructurePiece(item);
    }

    public static StructurePiece any() {
        return new AnyStructurePiece();
    }

    public static StructurePiece air() {
        return new MaterialStructurePiece(Material.AIR);
    }

    public static StructurePiece section(StructurePiece... pieces) {
        return new SectionStructurePiece(pieces);
    }
}
