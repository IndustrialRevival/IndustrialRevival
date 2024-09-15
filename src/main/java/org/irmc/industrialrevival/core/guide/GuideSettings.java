package org.irmc.industrialrevival.core.guide;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuideSettings {
    public static final GuideSettings DEFAULT_SETTINGS = new GuideSettings(true, true, Locale.getDefault().toLanguageTag());

    private boolean fireWorksEnabled;
    private boolean learningAnimationEnabled;
    private String language;
}
