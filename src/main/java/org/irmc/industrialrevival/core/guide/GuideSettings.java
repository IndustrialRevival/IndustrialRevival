package org.irmc.industrialrevival.core.guide;

import lombok.Data;

import java.util.Locale;

@Data
public class GuideSettings {
    public static final GuideSettings DEFAULT_SETTINGS = new GuideSettings();

    private boolean fireWorksEnabled = true;
    private boolean learningAnimationEnabled = true;
    private String language = Locale.getDefault().toLanguageTag();
}
