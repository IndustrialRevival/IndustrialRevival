package org.irmc.industrialrevival.api.objects.exceptions;

import org.bukkit.NamespacedKey;

public class IncompatibleItemHandlerException extends RuntimeException {
    public IncompatibleItemHandlerException(String message, NamespacedKey id) {
        super(
                """
                        Incompatible item handler found in item with id: %s
                        Reason: %s
                        It will not be registered.
                        """
                        .formatted(id.asString(), message));
    }
}
