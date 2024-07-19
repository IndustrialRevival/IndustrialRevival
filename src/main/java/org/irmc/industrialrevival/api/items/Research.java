package org.irmc.industrialrevival.api.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.player.PlayerProfile;

@Getter
@AllArgsConstructor
public class Research {
    private final int requiredExpLevel;
    private final NamespacedKey key;
    private final String name;

    public static Research getResearch(NamespacedKey key) {
        return IndustrialRevival.getInstance().getRegistry().getResearches().get(key);
    }

    public boolean isPlayerResearched(PlayerProfile profile) {
        return profile.hasResearched(key);
    }
}
