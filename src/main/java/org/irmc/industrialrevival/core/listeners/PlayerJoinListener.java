package org.irmc.industrialrevival.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.utils.Constants;

public class PlayerJoinListener extends AbstractIRListener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerProfile.getOrRequestProfile(p.getName());

        if (!p.hasPlayedBefore()) {
            PlayerInventory inv = p.getInventory();
            inv.addItem(Constants.GUIDE_BOOK_ITEM.clone());
        }
    }
}
