package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.attributes.Placeable;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.ItemHandler;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContent;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContents;
import org.jetbrains.annotations.Nullable;

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

    @Getter
    private IndustrialRevivalAddon addon;

    @Getter
    @Setter
    private String wikiText;

    private boolean locked = false;

    public IndustrialRevivalItem(
            ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        Preconditions.checkNotNull(group, "group cannot be null");
        Preconditions.checkNotNull(itemStack, "itemStack cannot be null");
        Preconditions.checkNotNull(recipeType, "recipeType cannot be null");
        Preconditions.checkNotNull(recipe, "recipe cannot be null");

        this.group = group;
        this.itemStack = itemStack;
        this.recipeType = recipeType;

        if (recipe.length < 9) {
            ItemStack[] newRecipe = new ItemStack[9];
            System.arraycopy(recipe, 0, newRecipe, 0, recipe.length);
            recipe = newRecipe;
        }

        if (recipe.length > 9) {
            ItemStack[] newRecipe = new ItemStack[9];
            System.arraycopy(recipe, 0, newRecipe, 0, 9);
            recipe = newRecipe;
        }

        this.recipe = recipe;

        if (this.recipe.length != 9) {
            throw new IllegalArgumentException("Recipe must be a array of 9 items");
        }
    }

    public IndustrialRevivalItemStack getItem() {
        return itemStack;
    }

    @Deprecated(forRemoval = true)
    public void setUsableInWorkbench(boolean usableInWorkbench) {
        if (locked) {
            throw new IllegalStateException("Item is locked and cannot be modified");
        }

        this.usableInWorkbench = usableInWorkbench;
    }

    public String getId() {
        return itemStack.getId();
    }

    /**
     * Registers the item in the registry.
     * @return WILL RETURN NULL IF THE ITEM IS NOT REGISTERED SUCCESSFULLY!!
     */
    public IndustrialRevivalItem register(IndustrialRevivalAddon addon) {
        itemStack.lock();
        this.locked = true;

        this.addon = addon;

        try {
            preRegister();
        } catch (Exception e) {
            return null;
        }

        postRegister();

        if (!group.getItems().contains(this)) {
            group.addItem(this);
        }

        RecipeContents.addRecipeContent(
                this.getId(), new RecipeContent(recipeType, recipeType.getMakerItem(), recipe, this));
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

    @Nullable public <T extends ItemHandler> T getItemHandler(Class<T> clazz) {
        return (T) itemHandlers.get(clazz);
    }

    private void preRegister() throws Exception {
        for (ItemHandler handler : itemHandlers.values()) {
            IncompatibleItemHandlerException ex = handler.isCompatible(this);
            if (ex != null) {
                throw ex;
            }
        }
    }

    private void postRegister() {
        if (this instanceof MobDropItem) {
            IndustrialRevival.getInstance().getRegistry().registerMobDrop(this);
        }

        if (this instanceof BlockDropItem) {
            IndustrialRevival.getInstance().getRegistry().registerBlockDrop(this);
        }

        if (this.getRecipeType() == RecipeType.VANILLA_CRAFTING) {
            NamespacedKey key = new NamespacedKey(addon.getPlugin(), getId().toLowerCase());
            ShapedRecipe shapedRecipe = new ShapedRecipe(key, itemStack.clone());
            shapedRecipe.shape("abc", "def", "ghi");
            char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
            for (int i = 0; i < 9; i++) {
                if (recipe[i] != null) {
                    shapedRecipe.setIngredient(chars[i], recipe[i]);
                }
            }

            Bukkit.addRecipe(shapedRecipe);
        }
    }

    public static IndustrialRevivalItem getById(String id) {
        return IndustrialRevival.getInstance().getRegistry().getItems().get(id);
    }
}
