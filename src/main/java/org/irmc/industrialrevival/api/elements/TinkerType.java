package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import org.bukkit.NamespacedKey;

/**
 * This class is used to define a material / product type in Smeltery.
 *
 * @author balugaq
 * @since 1.0
 */
@Getter
public abstract class TinkerType {
    private final NamespacedKey key;
    private final int level;
    public TinkerType(NamespacedKey key, int level) {
        this.key = key;
        this.level = level;
    }
}
