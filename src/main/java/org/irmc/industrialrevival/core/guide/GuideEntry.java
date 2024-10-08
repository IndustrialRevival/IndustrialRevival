package org.irmc.industrialrevival.core.guide;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

@Getter
@RequiredArgsConstructor
class GuideEntry<T> {
    private final T content;

    @Setter
    private int page;

    public boolean isGroup() {
        return content instanceof ItemGroup;
    }

    public boolean isItem() {
        return content instanceof IndustrialRevivalItem;
    }

    public boolean isGuide() {
        return content instanceof IRGuideImplementation;
    }

    public boolean isSearch() {
        return content instanceof SurvivalGuideImplementation.SearchGUI;
    }
}
