package org.irmc.industrialrevival.api.objects.enums;

public enum EnergyNetComponentType {
    CONSUMER,
    GENERATOR,
    CONNECTOR,
    CAPACITOR,
    /**
     * A component that limits the flow of energy through it.
     * It's also a connector.
     */
    LIMITER
}
