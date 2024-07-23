package org.irmc.industrialrevival.api.objects.exceptions;

public class IncompatibleItemHandlerException extends Exception {
    public IncompatibleItemHandlerException(String message, String id) {
        super(
                """
                Incompatible item handler found in item with id: %s
                Reason: %s
                It will not be registered.
                """
                        .formatted(id, message));
    }
}
