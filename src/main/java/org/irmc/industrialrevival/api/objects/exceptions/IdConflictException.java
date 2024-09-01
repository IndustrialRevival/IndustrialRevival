package org.irmc.industrialrevival.api.objects.exceptions;

import lombok.Getter;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

@Getter
public class IdConflictException extends Exception {
    private final IndustrialRevivalAddon originalAddon;
    private final IndustrialRevivalAddon conflictingAddon;

    public IdConflictException(String id, IndustrialRevivalAddon origin, IndustrialRevivalAddon conflictingAddon) {
        super(
                """
                        Item id conflict detected:
                        Item id: %s
                        Original addon: %s
                        Conflicting item's addon: %s

                        The conflicting item will not be registered.
                        """
                        .formatted(
                                id,
                                origin.getPlugin().getName(),
                                conflictingAddon.getPlugin().getName()));

        this.originalAddon = origin;
        this.conflictingAddon = conflictingAddon;
    }
}
