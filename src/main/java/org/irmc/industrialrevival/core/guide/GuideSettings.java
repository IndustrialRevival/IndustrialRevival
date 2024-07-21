package org.irmc.industrialrevival.core.guide;

import java.util.Locale;
import lombok.Data;

@Data
public class GuideSettings {
    private boolean fireWorksEnabled = true;
    private boolean learningAnimationEnabled = true;
    private String language = Locale.getDefault().toLanguageTag();
}
