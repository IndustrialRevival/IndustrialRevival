package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemTextureService {
    private final Map<Material, Integer> customModelDataMap;
    private final Map<Integer, BlockModel> blockModelMap;

    public ItemTextureService() {
        customModelDataMap = new HashMap<>();
        blockModelMap = new HashMap<>();

        setup(IRDock.getPlugin()
                .getBlockDataService()
                .getBlockDataMap()
                .values());
    }

    private void setup(Collection<IRBlockData> blockData) {
        for (IRBlockData data : blockData) {
            NamespacedKey id = data.getId();
            IndustrialRevivalItem item = IndustrialRevivalItem.getById(id);
            if (item == null) {
                // just ignore
                continue;
            }

            Location location = data.getLocation();
            blockModelMap.put(location.hashCode(), new BlockModel(location, item.getIcon()));
        }
    }

    public void setupTexture(ItemStack itemStack) {
        Material material = itemStack.getType();
        int customModelDataId = customModelDataMap.getOrDefault(material, 20000);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setCustomModelData(customModelDataId);
        itemStack.setItemMeta(meta);

        customModelDataId++;
        customModelDataMap.put(material, customModelDataId);
    }

    public void blockPlacing(BlockPlaceEvent e) {
        Location location = e.getBlockPlaced().getLocation();
        ItemStack itemStack = e.getItemInHand();

        blockModelMap.put(location.hashCode(), new BlockModel(location, itemStack));
    }

    public void blockBreaking(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();
        BlockModel model = blockModelMap.remove(location.hashCode());
        model.breakBlock();
    }

    private static class BlockModel {
        private final Material BROKEN_MATERIAL = Material.AIR;

        private final Location loc;
        private ItemDisplay entity;

        public BlockModel(Location loc, ItemStack item) {
            this.loc = loc;

            placeBlock(loc, item);
        }

        private void placeBlock(Location loc, ItemStack item) {
            World world = loc.getWorld();

            Location entityLoc = loc.clone().add(0.5, 0.5, 0.5);

            this.entity = world.spawn(entityLoc, ItemDisplay.class, i -> {
                i.setItemStack(item);
                i.setPersistent(true);
                i.setInvulnerable(true);
                i.setGravity(false);
                i.setSilent(true);
            });
        }

        public void breakBlock() {
            if (entity != null) {
                entity.remove();
                entity = null;
            }

            World world = loc.getWorld();
            world.setBlockData(loc, BROKEN_MATERIAL.createBlockData());
        }
    }
}
