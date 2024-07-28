package org.irmc.industrialrevival.core.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.Pair;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class DropListener extends AbstractIRListener {
    @EventHandler
    public void onMobDrop(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        List<Pair<ItemStack, Double>> drops =
                IndustrialRevival.getInstance().getRegistry().getMobDrops().get(entity.getType());

        if (drops != null) {
            Random random = new Random();
            for (Pair<ItemStack, Double> drop : drops) {
                double chance = random.nextDouble(100);
                if (chance <= drop.getB()) {
                    ItemStack item = drop.getA();
                    world.dropItemNaturally(location, item);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Material material = e.getBlock().getType();
        List<Pair<ItemStack, Double>> drops =
                IndustrialRevival.getInstance().getRegistry().getBlockDrops().get(material);
        Player player = e.getPlayer();

        if (drops != null && !drops.isEmpty() && player.getGameMode() != GameMode.CREATIVE) {
            Random random = new Random();
            for (Pair<ItemStack, Double> drop : drops) {
                double chance = random.nextDouble(100);
                if (chance <= drop.getB()) {
                    ItemStack item = drop.getA();
                    e.getPlayer().getInventory().addItem(item);
                }
            }
        }
    }
}
