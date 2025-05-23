package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ArmorPiece extends IndustrialRevivalItem {
    private final Set<PotionEffect> potionEffects = new HashSet<>();
    private ArmorSet parent;

    public ArmorPiece addPotionEffect(@NotNull PotionEffect effect) {
        checkRegistered();
        potionEffects.add(effect);
        return this;
    }

    public ArmorPiece setPotionEffects(@NotNull Set<PotionEffect> effects) {
        checkRegistered();
        potionEffects.clear();
        potionEffects.addAll(effects);
        return this;
    }

    public ArmorPiece setParent(@NotNull ArmorSet parent) {
        checkRegistered();
        this.parent = parent;
        return this;
    }
}
