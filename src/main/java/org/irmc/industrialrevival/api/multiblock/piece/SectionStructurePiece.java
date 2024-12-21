package org.irmc.industrialrevival.api.multiblock.piece;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.irmc.pigeonlib.items.ItemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A piece that represents a section of a structure. It allows the following pieces to be placed in any order.
 * {@link SectionStructurePiece} just as wonderful as "a or b"
 */
public class SectionStructurePiece extends StructurePiece {
    @Getter
    private final StructurePiece[] pieces;
    private final ItemStack display;

    public SectionStructurePiece(StructurePiece... piece) {
        this.pieces = piece;

        List<Component> lore = new ArrayList<>();
        for (StructurePiece p : pieces) {
            lore.add(ItemUtils.getDisplayName(p.getDisplay()));
        }

        display = new CustomItemStack(Material.CHEST,
                "",
                "Section Structure Piece",
                "Allow the following pieces to be placed in any order:").lore(lore).getBukkit();
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
