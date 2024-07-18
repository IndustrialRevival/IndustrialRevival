package org.irmc.industrialrevival.core.guide;

import lombok.Getter;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
record GuideEntry<T>(T content) {
  public boolean isGroup() {
    return content instanceof ItemGroup;
  }

  public boolean isItem() {
    return content instanceof IndustrialRevivalItem;
  }
}
