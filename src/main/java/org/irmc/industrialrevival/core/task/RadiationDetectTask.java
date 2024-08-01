package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RadiationDetectTask implements Consumer<WrappedTask> {
    @Override
    public void accept(WrappedTask wrappedTask) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("industrialrevival.bypass.radiation")) {
                continue;
            }

            Inventory inv = p.getInventory();
            // TODO implement radiation detection
        }
    }
}
