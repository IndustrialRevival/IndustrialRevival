package org.irmc.industrialrevival.api.objects.events;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

@Getter
public class MenuOpenEvent extends PlayerRightClickEvent {
    private final HandlerList handlers = new HandlerList();
    private final PlayerRightClickEvent rightClickEvent;
    private final MachineMenu openedMenu;

    public MenuOpenEvent(PlayerRightClickEvent originalEvent, @Nonnull MachineMenu openedMenu) {
        super(originalEvent.getOriginalEvent());
        this.rightClickEvent = originalEvent;
        this.openedMenu = openedMenu;
    }

    public static HandlerList getHandlerList() {
        return new HandlerList();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
