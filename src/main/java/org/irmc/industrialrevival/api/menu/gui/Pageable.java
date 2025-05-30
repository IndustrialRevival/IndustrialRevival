package org.irmc.industrialrevival.api.menu.gui;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.player.PlayerProfile;

public interface Pageable {
    void previousPage(Player player, PlayerProfile profile, int currentPage);
    void nextPage(Player player, PlayerProfile profile, int currentPage);
}
