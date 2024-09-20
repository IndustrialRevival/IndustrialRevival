package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.ArmorSet;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.enums.ArmorProtectionType;

public class RadiationDetectTask implements Consumer<WrappedTask> {
    @Override
    public void accept(WrappedTask wrappedTask) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("industrialrevival.bypass.radiation")) {
                continue;
            }

            Inventory inv = p.getInventory();
            /*
            EntityEquipment ee = p.getEquipment();

            ItemStack helmet = ee.getHelmet();
            IndustrialRevivalItem irHelmet = IndustrialRevivalItem.getByItem(helmet);
            if (irHelmet != null) {
                if (irHelmet instanceof ArmorSet.ArmorPiece piece) {
                    ArmorSet parent = piece.getParent();
                    if (parent.getProtectionTypes().contains(ArmorProtectionType.RADIATION)) {
                        if (!parent.isProtectWhenFullSet()) {
                            //
                        }
                    }
                }
            }

             */

            // todo: add radiation detection logic
        }
    }
}
