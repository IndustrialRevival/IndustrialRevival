package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ItemDroppable;
import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.DataUtil;

import java.security.SecureRandom;
import java.util.List;

public class DropListener implements Listener {
    @EventHandler
    public void onMobDrop(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        List<MobDropMethod> drops =
                IRDock.getPlugin().getRegistry().getMobDrops().get(entity.getType());

        if (drops != null) {
            SecureRandom random = new SecureRandom(entity.getUniqueId().toString().getBytes());
            for (MobDropMethod method : drops) {
                double chance = random.nextDouble(100);
                if (chance <= method.getChance()) {
                    ItemStack item = method.getItemToDrop();
                    // banned item should not method
                    IndustrialRevivalItem irItem = IndustrialRevivalItem.getByItem(item);
                    if (irItem != null && irItem.isDisabledInWorld(entity.getWorld())) {
                        Player killer = entity.getKiller();
                        if (killer != null) {
                            IRDock.getPlugin().getLanguageManager()
                                    .sendMessage(killer, "dropping_banned_item");
                        }
                        continue;
                    }

                    ItemStack drop = item.clone();
                    drop.setAmount(method.getDropAmount());
                    world.dropItemNaturally(location, drop);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        World world = location.getWorld();
        Player breaker = e.getPlayer();
        IndustrialRevivalItem iritem = DataUtil.getItem(location);
        if (iritem instanceof ItemDroppable id) {
            if (!iritem.isDisabledInWorld(world)) {
                List<ItemStack> drops = id.drops(breaker);
                if (!id.dropBlockDropItems()) {
                    return;
                }

                for (ItemStack item : drops) {
                    world.dropItemNaturally(location, item);
                }
            }
        }

        List<BlockDropMethod> methods = IRDock.getPlugin().getRegistry().getBlockDrops().get(material);
        if (methods == null) {
            return;
        }

        SecureRandom random = new SecureRandom(material.toString().getBytes());
        for (BlockDropMethod method : methods) {
            double chance = random.nextDouble(100);
            if (chance <= method.getChance()) {
                ItemStack item = method.getItemToDrop();
                // banned item should not method
                IndustrialRevivalItem irItem = IndustrialRevivalItem.getByItem(item);
                if (irItem != null && irItem.isDisabledInWorld(world)) {
                    IRDock.getPlugin().getLanguageManager()
                            .sendMessage(breaker, "dropping_banned_item");
                    continue;
                }

                ItemStack drop = item.clone();
                drop.setAmount(method.getDropAmount());
                world.dropItemNaturally(location, drop);
            }
        }
    }
}
