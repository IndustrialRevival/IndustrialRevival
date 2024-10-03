package org.irmc.industrialrevival.core.listeners;

import java.security.SecureRandom;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ItemDroppable;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.Pair;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class DropListener extends AbstractIRListener {
    @EventHandler
    public void onMobDrop(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        List<Pair<ItemStack, Double>> drops =
                IndustrialRevival.getInstance().getRegistry().getMobDrops().get(entity.getType());

        if (drops != null) {
            SecureRandom random =
                    new SecureRandom(entity.getUniqueId().toString().getBytes());
            for (Pair<ItemStack, Double> drop : drops) {
                double chance = random.nextDouble(100);
                if (chance <= drop.getB()) {
                    ItemStack item = drop.getA();
                    // banned item should not drop
                    IndustrialRevivalItem irItem = IndustrialRevivalItem.getByItem(item);
                    if (irItem != null && irItem.isDisabledInWorld(entity.getWorld())) {
                        Player killer = entity.getKiller();
                        if (killer != null) {
                            IndustrialRevival.getInstance().getLanguageManager()
                                    .sendMessage(killer, "dropping_banned_item");
                        }
                        continue;
                    }
                    world.dropItemNaturally(location, item);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Location loc = e.getBlock().getLocation();
        IRBlockData data = IndustrialRevival.getInstance().getBlockDataService().getBlockData(loc);
        boolean continueDrop = true;
        if (data != null) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item != null) {
                e.setDropItems(false);
                if (item instanceof ItemDroppable droppable) {
                    continueDrop = droppable.dropBlockDropItems();
                    List<ItemStack> items = droppable.drops(e.getPlayer());
                    if (items != null && !items.isEmpty()) {
                        World world = e.getBlock().getWorld();
                        for (ItemStack itemStack : items) {
                            // banned item should not drop
                            IndustrialRevivalItem irItem = IndustrialRevivalItem.getByItem(itemStack);
                            if (irItem != null && irItem.isDisabledInWorld(world)) {
                                IndustrialRevival.getInstance().getLanguageManager()
                                        .sendMessage(e.getPlayer(), "dropping_banned_item");
                                continue;
                            }
                            world.dropItemNaturally(loc, itemStack);
                        }
                    }
                }
                IndustrialRevival.getInstance().getDataManager().handleBlockBreaking(data.getLocation());
            }
        }

        if (continueDrop) {
            Material material = e.getBlock().getType();
            List<Pair<ItemStack, Double>> drops = IndustrialRevival.getInstance()
                    .getRegistry()
                    .getBlockDrops()
                    .get(material);
            Player player = e.getPlayer();
            World world = e.getBlock().getWorld();

            drops = drops == null ? List.of() : drops;

            if (drops.isEmpty() && data != null) {
                IndustrialRevivalItem irItem = IndustrialRevivalItem.getById(data.getId());
                drops = List.of(new Pair<>(irItem.getItem().cloneIR(), 100.0));
            }

            if (player.getGameMode() != GameMode.CREATIVE) {
                SecureRandom random = new SecureRandom();
                for (Pair<ItemStack, Double> drop : drops) {
                    double chance = random.nextDouble(100);
                    if (chance <= drop.getB()) {
                        ItemStack item = drop.getA();
                        world.dropItemNaturally(loc, item);
                    }
                }
            }
        }
    }
}
