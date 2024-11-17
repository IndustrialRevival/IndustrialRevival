package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;

@Getter
public class MenuOpenEvent extends PlayerRightClickEvent {
    private final PlayerRightClickEvent rightClickEvent;
    private final MachineMenu openedMenu;

    public MenuOpenEvent(PlayerRightClickEvent originalEvent, @NotNull MachineMenu openedMenu) {
        super(originalEvent.getOriginalEvent());
        this.rightClickEvent = originalEvent;
        this.openedMenu = openedMenu;
    }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
