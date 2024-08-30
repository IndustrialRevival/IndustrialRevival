package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public class RadiationDetectTask implements Consumer<WrappedTask> {
    @Override
    public void accept(WrappedTask wrappedTask) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("industrialrevival.bypass.radiation")) {
                continue;
            }

            Inventory inv = p.getInventory();
            EntityEquipment ee = p.getEquipment();

            ItemStack helmet = ee.getHelmet();
            IndustrialRevivalItem irKit = IndustrialRevivalItem.getByItem(helmet);
            if (irKit != null) {

            }
            //todo: add radiation detection logic
        }
    }
}
