package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerBucketEmptyToIRBlockEvent extends PlayerEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final PlayerBucketEmptyEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public PlayerBucketEmptyToIRBlockEvent(PlayerBucketEmptyEvent event, IndustrialRevivalItem iritem) {
        super(event.getPlayer());
        this.originalEvent = event;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
