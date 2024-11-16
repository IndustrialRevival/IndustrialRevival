package org.irmc.industrialrevival.api.multiblock.piece;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

// todo : add SectionStructurePiece
/**
 * A piece of a structure.
 * Use {@link #getByMaterial(Material)} to get a vanilla piece for a given material,
 * or {@link #getByIRItem(IndustrialRevivalItem)} to get an IR piece for a given IR item.
 * Use {@link #getUnion(ItemStack, StructurePiece...)} to get a piece that matches any of the given pieces.
 * Use {@link #getAny()} to get a piece that matches any block.
 *
 * @author balugaq
 */
public abstract class StructurePiece {
    public static final EnumMap<Material, StructurePiece> VANILLA_CACHE = new EnumMap<>(Material.class);
    public static final Map<String, StructurePiece> IR_CACHE = new HashMap<>();
    static StructurePiece getByMaterial(Material m) {
        return VANILLA_CACHE.computeIfAbsent(m, MaterialStructurePiece::new);
    }
    static StructurePiece getByIRItem(IndustrialRevivalItem iritem) {
        return IR_CACHE.computeIfAbsent(iritem.getId(), IndustrialRevivalStructurePiece::new);
    }
    static StructurePiece getUnion(ItemStack display, StructurePiece... pieces) {
        return new UnionStructurePiece(display, pieces);
    }
    static StructurePiece getAny() {
        return new AnyStructurePiece();
    }

    public abstract boolean matches(Block b);
    public abstract ItemStack getDisplay();
}