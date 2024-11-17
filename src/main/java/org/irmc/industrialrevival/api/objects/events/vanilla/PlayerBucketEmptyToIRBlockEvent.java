package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerBucketEmptyToIRBlockEvent extends PlayerBucketEmptyEvent implements RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    public PlayerBucketEmptyToIRBlockEvent(PlayerBucketEmptyEvent event, IndustrialRevivalItem iritem) {
        this(iritem, event.getPlayer(), event.getBlock(), event.getBlockClicked(), event.getBlockFace(), event.getBucket(), event.getItemStack(), event.getHand());
    }
    public PlayerBucketEmptyToIRBlockEvent(@NotNull IndustrialRevivalItem iritem,  @NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @NotNull EquipmentSlot hand) {
        super(who, block, blockClicked, blockFace, bucket, itemInHand, hand);
        this.iritem = iritem;
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
