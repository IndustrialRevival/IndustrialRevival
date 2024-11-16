package org.irmc.industrialrevival.api.multiblock.piece;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

public class AnyStructurePiece extends StructurePiece {
    private static final ItemStack DISPLAY = new CustomItemStack(Material.STRUCTURE_VOID, "此处放置任意方块");

    @Override
    public boolean matches(Block b) {
        return true;
    }

    @Override
    public ItemStack getDisplay() {
        return DISPLAY;
    }
}
