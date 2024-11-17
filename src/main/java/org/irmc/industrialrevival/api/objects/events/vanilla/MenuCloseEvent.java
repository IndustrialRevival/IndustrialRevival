package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;

@Getter
public class MenuCloseEvent extends InventoryCloseEvent {
    private final InventoryCloseEvent originalEvent;
    private final MachineMenu menu;
    public MenuCloseEvent(@NotNull InventoryCloseEvent originalEvent, @NotNull MachineMenu menu) {
        super(originalEvent.getView());
        this.originalEvent = originalEvent;
        this.menu = menu;
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
