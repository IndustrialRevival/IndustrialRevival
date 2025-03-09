package org.irmc.industrialrevival.api.elements.tinker;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Industrial Revival Tinker Type.
 *
 * @author balugaq
 * @since 1.0
 */
public class IRTinkerType extends TinkerType {
    public IRTinkerType(@NotNull NamespacedKey key, int level, boolean meltable, boolean outputAble) {
        super(key, level, meltable, outputAble);
    }

    public IRTinkerType(@NotNull NamespacedKey key, int level) {
        super(key, level, true, true);
    }
}
