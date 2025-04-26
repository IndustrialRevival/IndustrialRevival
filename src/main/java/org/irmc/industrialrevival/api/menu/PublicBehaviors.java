package org.irmc.industrialrevival.api.menu;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balugaq
 */
@UtilityClass
public class PublicBehaviors {
    public static final Map<NamespacedKey, ClickHandler> PUBLISHED_CLICK_HANDLERS = new HashMap<>();

    @CanIgnoreReturnValue
    public static ClickHandler publish(NamespacedKey key, ClickHandler clickHandler) {
        PUBLISHED_CLICK_HANDLERS.putIfAbsent(key, clickHandler);
        return clickHandler;
    }

    public static ClickHandler forName(NamespacedKey key) {
        return PUBLISHED_CLICK_HANDLERS.get(key);
    }
}
