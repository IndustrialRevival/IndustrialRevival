package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromBlockItem;

/**
 * @author baluagq
 */
@Getter
public class BlockDropMethod implements ProduceMethod {
    private final Material blockType;
    private final int dropAmount;
    private final double chance;
    private final ItemStack itemToDrop;

    public BlockDropMethod(Material blockType, int dropAmount, double chance, ItemStack itemToDrop) {
        this.blockType = blockType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = itemToDrop;
    }

    public BlockDropMethod(Material blockType, int dropAmount, double chance, DropFromBlockItem itemToDrop) {
        this.blockType = blockType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = itemToDrop.getIcon();
    }
}
