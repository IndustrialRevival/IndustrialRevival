package org.irmc.industrialrevival.api.objects;

import org.irmc.industrialrevival.core.IndustrialRevival;

public enum RadiationLevel {
    LOW(1, "radiation.level.low"),
    MEDIUM(3, "radiation.level.medium"),
    HIGH(5, "radiation.level.high"),
    EXTREME(7, "radiation.level.extreme"),
    DEADLY(10, "radiation.level.deadly");

    private final int value;
    private final String translationKey;

    RadiationLevel(int value, String translationKey) {
        this.value = value;
        this.translationKey = translationKey;
    }

    public int getValue() {
        return value;
    }

    public String getTranslation() {
        return IndustrialRevival.getInstance().getLanguageManager().getMsg(null, translationKey);
    }
}
