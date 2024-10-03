package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromMobItem;

@Getter
public class MobDropMethod {
    private final EntityType dropFromEntity;
    private final int amount;
    private final int chance;
    private final ItemStack itemToDrop;

    public MobDropMethod(EntityType dropFromEntity, int amount, int chance, ItemStack itemToDrop) {
        this.dropFromEntity = dropFromEntity;
        this.amount = amount;
        this.chance = chance;
        this.itemToDrop = itemToDrop;
    }

    public MobDropMethod(EntityType dropFromEntity, int amount, int chance, DropFromMobItem dropFromMobItem) {
        this.dropFromEntity = dropFromEntity;
        this.amount = amount;
        this.chance = chance;
        this.itemToDrop = dropFromMobItem.getItem();
    }
}
