package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerBucketEmptyToIRBlockEvent extends PlayerBucketEmptyEvent {
    private final IndustrialRevivalItem iritem;
    public PlayerBucketEmptyToIRBlockEvent(PlayerBucketEmptyEvent event, IndustrialRevivalItem iritem) {
        this(iritem, event.getPlayer(), event.getBlock(), event.getBlockClicked(), event.getBlockFace(), event.getBucket(), event.getItemStack(), event.getHand());
    }
    public PlayerBucketEmptyToIRBlockEvent(@NotNull IndustrialRevivalItem iritem,  @NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @NotNull EquipmentSlot hand) {
        super(who, block, blockClicked, blockFace, bucket, itemInHand, hand);
        this.iritem = iritem;
    }
}
