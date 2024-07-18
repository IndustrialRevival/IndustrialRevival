package org.irmc.industrialrevival.core.guide;

import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuideSettings {
  private boolean fireWorksEnabled = true;
  private boolean learningAnimationEnabled = true;
  private Locale language = Locale.getDefault();
}
