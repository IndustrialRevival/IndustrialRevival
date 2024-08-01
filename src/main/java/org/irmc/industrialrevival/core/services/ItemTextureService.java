package org.irmc.industrialrevival.core.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class ItemTextureService {
    private final Map<Material, Integer> customModelDataMap;
    private final Map<Location, BlockModel> blockModelMap;

    public ItemTextureService() {
        customModelDataMap = new HashMap<>();
        blockModelMap = new HashMap<>();

        setup(IndustrialRevival.getInstance()
                .getBlockDataService()
                .getBlockDataMap()
                .values());
    }

    private void setup(Collection<IRBlockData> blockData) {
        for (IRBlockData data : blockData) {
            String id = data.getId();
            IndustrialRevivalItem item = IndustrialRevivalItem.getById(id);
            if (item == null) {
                // just ignore
                return;
            }

            Location location = data.getLocation();
            blockModelMap.put(location, new BlockModel(location, item.getItem()));
        }
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

    public void blockPlacing(BlockPlaceEvent e) {
        Location location = e.getBlockPlaced().getLocation();
        ItemStack itemStack = e.getItemInHand();

        blockModelMap.put(location, new BlockModel(location, itemStack));
    }

    public void blockBreaking(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();
        BlockModel model = blockModelMap.remove(location);
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

            world.spawn(entityLoc, ItemDisplay.class, i -> {
                i.setItemStack(item);
                i.setPersistent(true);
                i.setInvulnerable(true);
                i.setGravity(false);
                i.setSilent(true);

                this.entity = i;
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
