package org.irmc.industrialrevival.api.objects.enums;

public enum GuideMode {
    SURVIVAL,
    CHEAT;

    public GuideMode next() {
        return switch (this) {
            case SURVIVAL -> CHEAT;
            case CHEAT -> SURVIVAL;
        };
    }
}
