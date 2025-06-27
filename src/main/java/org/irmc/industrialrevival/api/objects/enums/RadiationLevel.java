package org.irmc.industrialrevival.api.objects.enums;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public enum RadiationLevel {
    LOW(1, "radiation.level.low"),
    MEDIUM(3, "radiation.level.medium"),
    HIGH(5, "radiation.level.high"),
    EXTREME(7, "radiation.level.extreme"),
    DEADLY(10, "radiation.level.deadly");

    @Getter
    private final int value;

    private final String translationKey;

    RadiationLevel(int value, String translationKey) {
        this.value = value;
        this.translationKey = translationKey;
    }

    public Component getTranslation() {
        return IRDock.getPlugin().getLanguageManager().getMsgComponent(null, translationKey);
    }
}
