package org.irmc.industrialrevival.core.guide;

import java.util.Locale;

import lombok.Data;

@Data
public class GuideSettings {
    private String username;
    private boolean fireWorksEnabled = true;
    private boolean learningAnimationEnabled = true;
    private Locale language = Locale.getDefault();

    public GuideSettings() {

    }
}
