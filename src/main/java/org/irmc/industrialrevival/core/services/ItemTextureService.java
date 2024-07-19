package org.irmc.industrialrevival.core.services;

import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemTextureService {
    private int customModelDataId = 20000;

    public ItemTextureService() {

    }

    public void setUpTexture(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(customModelDataId);
        itemStack.setItemMeta(meta);

        customModelDataId++;
    }

    /*
    public void blockPlacing(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        ItemStack itemStack = e.getItemInHand();
        ItemMeta meta = itemStack.getItemMeta();
        int customModelData = meta.getCustomModelData();
        ArmorStand armorStand = block.getWorld().spawn(block.getLocation(), ArmorStand.class);
        armorStand.setInvisible(true);
        armorStand.setCanMove(false);
        armorStand.setCanTick(false);
        armorStand.setInvulnerable(true);
        armorStand.set
    }
     */
}
