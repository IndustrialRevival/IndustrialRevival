package org.irmc.industrialrevival.api.researches;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.objects.exceptions.IdConflictException;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.jetbrains.annotations.Nullable;

@Getter
@AllArgsConstructor
public class Research {
    // private final int requiredExpLevel;
    private final NamespacedKey key;
    private final String name;

    private IndustrialRevivalAddon addon;

    @Nullable public static Research getResearch(NamespacedKey key) {
        return IndustrialRevival.getInstance().getRegistry().getResearches().get(key);
    }

    public boolean playerCanResearch(PlayerProfile profile) {
        return false;
    }

    public boolean isPlayerResearched(PlayerProfile profile) {
        return profile.hasResearched(key);
    }

    public void register(IndustrialRevivalAddon addon) {
        Research mayConflicting = getResearch(key);
        if (mayConflicting != null) {
            throw new RuntimeException(
                    new IdConflictException(key.asString() + "(research)", addon, mayConflicting.getAddon()));
        }

        this.addon = addon;

        IndustrialRevival.getInstance().getRegistry().getResearches().put(key, this);
    }
}
