package org.irmc.industrialrevival.core.guide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class GuideSettings {
    public static final GuideSettings DEFAULT_SETTINGS =
            new GuideSettings(true, true, Locale.getDefault().toLanguageTag());

    private boolean fireWorksEnabled;
    private boolean learningAnimationEnabled;
    private String language;
}
