package org.irmc.industrialrevival.core.services;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemTextureService {
    private final Map<Material, Integer> customModelDataMap;

    public ItemTextureService() {
        customModelDataMap = new HashMap<>();
    }

    public void setUpTexture(ItemStack itemStack) {
        Material material = itemStack.getType();
        int customModelDataId = customModelDataMap.getOrDefault(material, 20000);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(customModelDataId);
        itemStack.setItemMeta(meta);

        customModelDataId++;
        customModelDataMap.put(material, customModelDataId);
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
