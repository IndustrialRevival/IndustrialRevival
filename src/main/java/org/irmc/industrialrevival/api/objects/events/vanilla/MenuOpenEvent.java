package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerEvent;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;

@Getter
public class MenuOpenEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final InventoryOpenEvent rightClickEvent;
    private final MachineMenu openedMenu;

    public MenuOpenEvent(InventoryOpenEvent originalEvent, @NotNull MachineMenu openedMenu) {
        super((Player) originalEvent.getPlayer());
        this.rightClickEvent = originalEvent;
        this.openedMenu = openedMenu;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
