package org.irmc.industrialrevival.api.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.attributes.Placeable;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class IndustrialRevivalItem implements Placeable {
    private final Map<Class<? extends ItemHandler>, ItemHandler> itemHandlers = new HashMap<>();

    @Getter
    private final ItemGroup group;

    private final IndustrialRevivalItemStack itemStack;

    @Getter
    private final RecipeType recipeType;

    @Getter
    private final ItemStack[] recipe;

    @Getter
    private boolean usableInWorkbench = false;

    private boolean locked = false;

    public IndustrialRevivalItem(
            ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        this.group = group;
        this.itemStack = itemStack;
        this.recipeType = recipeType;

        if (recipe == null) {
            recipe = new ItemStack[9];
        }

        if (recipe.length > 9) {
            recipe = Arrays.copyOfRange(recipe, 0, 9);
        }

        if (recipe.length < 9) {
            ItemStack[] newRecipe = new ItemStack[9];
            System.arraycopy(recipe, 0, newRecipe, 0, recipe.length);
            recipe = newRecipe;
        }

        this.recipe = recipe;
    }

    public IndustrialRevivalItemStack getItem() {
        return itemStack;
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

    public IndustrialRevivalItem register() {
        itemStack.lock();
        this.locked = true;

        IndustrialRevival.getInstance().getRegistry().getItems().put(getId(), this);

        return this;
    }

    @SuppressWarnings("removal")
    public Component getItemName() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.hasDisplayName()) {
            return meta.displayName();
        } else {
            return Component.translatable(itemStack.getType().getTranslationKey());
        }
    }

    protected void addItemHandlers(ItemHandler... handlers) {
        for (ItemHandler handler : handlers) {
            itemHandlers.put(handler.getClass(), handler);
        }
    }

    public static IndustrialRevivalItem getById(String id) {
        return IndustrialRevival.getInstance().getRegistry().getItems().get(id);
    }
}
