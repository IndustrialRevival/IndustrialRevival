package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromMobItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * @author baluagq
 */
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
        this.itemToDrop = dropFromMobItem.getIcon();
    }

    @Override
    public RecipeType getRecipeType() {
        return RecipeType.KILL_MOB;
    }

    @Override
    public ItemStack[] getIngredients() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack[] getOutput() {
        return new ItemStack[] { itemToDrop };
    }
}
