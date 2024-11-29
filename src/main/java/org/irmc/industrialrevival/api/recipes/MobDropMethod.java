package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromMobItem;

@Getter
public class MobDropMethod implements ProduceMethod {
    private final EntityType mobType;
    private final int dropAmount;
    private final double chance;
    private final ItemStack itemToDrop;

    public MobDropMethod(EntityType mobType, int dropAmount, double chance, ItemStack itemToDrop) {
        this.mobType = mobType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = itemToDrop;
    }

    public MobDropMethod(EntityType mobType, int dropAmount, double chance, DropFromMobItem dropFromMobItem) {
        this.mobType = mobType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = dropFromMobItem.getItem().getItemStack();
    }
}
