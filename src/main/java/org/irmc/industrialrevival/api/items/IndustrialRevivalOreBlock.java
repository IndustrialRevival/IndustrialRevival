package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.industrialrevival.api.items.attributes.VanillaSmeltingItem;
import org.jetbrains.annotations.NotNull;

/**
 * An ore block that drops itself when mined.
 */
public class IndustrialRevivalOreBlock extends IndustrialRevivalItem implements VanillaSmeltingItem {
    @Getter
    private float exp;
    @Getter
    private int cookingTime;
    private ItemStack output;

    public IndustrialRevivalOreBlock setExp(float exp) {
        checkRegistered();
        this.exp = exp;
        return this;
    }

    public IndustrialRevivalOreBlock setCookingTime(int cookingTime) {
        checkRegistered();
        this.cookingTime = cookingTime;
        return this;
    }

    public IndustrialRevivalOreBlock setOutput(ItemStackHandler handler) {
        checkRegistered();
        ItemStack output = handler.getItemStack(this);
        if (output == null) {
            return this;
        }
        this.output = output;
        return this;
    }

    @Override
    @NotNull
    public RecipeChoice getRecipeInput() {
        return new RecipeChoice.ExactChoice(getIcon());
    }

    @Override
    @NotNull
    public ItemStack getSmeltOutput() {
        return output;
    }

    @FunctionalInterface
    public interface ItemStackHandler {
        ItemStack getItemStack(IndustrialRevivalOreBlock oreBlock);
    }
}
