package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.core.IndustrialRevival;

import java.util.List;
import java.util.Random;

public class MobDropListener extends AbstractIRListener {
    @EventHandler
    public void onMobDrop(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();
        List<MobDropItem> drops = IndustrialRevival.getInstance().getRegistry().getMobDrops().get(entity.getType());
        if (drops != null) {
            Random random = new Random();
            for (MobDropItem drop : drops) {
                double chance = random.nextDouble(100);
                if (chance <= drop.getChance()) {
                    ItemStack item = drop.dropItems();
                    world.dropItemNaturally(location, item);
                }
            }
        }
    }
}
