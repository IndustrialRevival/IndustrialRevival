package org.irmc.industrialrevival.api.items;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.attributes.Placeable;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public class IndustrialRevivalItem implements Placeable {
    private final ItemGroup group;
    private final IndustrialRevivalItemStack itemStack;
    private final RecipeType recipeType;
    private final ItemStack[] recipe;

    private boolean usableInWorkbench = false;
    private boolean locked = false;

    public IndustrialRevivalItem(ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        this.group = group;
        this.itemStack = itemStack;
        this.recipeType = recipeType;
        this.recipe = recipe;
    }

    public ItemGroup getGroup() {
        return group;
    }

    public IndustrialRevivalItemStack getItem() {
        return itemStack;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public ItemStack[] getRecipe() {
        return recipe;
    }

    public boolean isUsableInWorkbench() {
        return usableInWorkbench;
    }

    public void setUsableInWorkbench(boolean usableInWorkbench) {
        if (locked) {
            throw new IllegalStateException("Item is locked and cannot be modified");
        }

        this.usableInWorkbench = usableInWorkbench;
    }

    public String getId() {
        return itemStack.getId();
    }

    public void register() {
        itemStack.lock();
        this.locked = true;
    }

    public Component getItemName() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.hasDisplayName()) {
            return meta.displayName();
        } else {
            return Component.translatable(itemStack.getType().getTranslationKey());
        }
    }
}
