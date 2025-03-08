package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to define a material / product type in Smeltery.
 *
 * @author balugaq
 * @since 1.0
 */
@Getter
public abstract class TinkerType {
    private final @NotNull NamespacedKey key;
    private final int level;
    public TinkerType(@NotNull NamespacedKey key, int level) {
        this.key = key;
        this.level = level;
    }

    public @NotNull Component name() {
        // todo: add localization support
        return Component.text(key.getKey());
    }
}
