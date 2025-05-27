package org.irmc.industrialrevival.core.guide;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.gui.PageableMenu;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

@Data
@RequiredArgsConstructor
class GuideEntry<T> {
    private final T content;

    @Setter
    private int page;

    public boolean isMenu() {
        return content instanceof SimpleMenu;
    }

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

    public static <T> GuideEntry<T> warp(T value) {
        var e = new GuideEntry<>(value);
        e.setPage(1);
        return e;
    }

    public static <T> GuideEntry<PageableMenu<T>> warp(PageableMenu<T> value) {
        var e = new GuideEntry<>(value);
        e.setPage(value.getCurrentPage());
        return e;
    }
}
