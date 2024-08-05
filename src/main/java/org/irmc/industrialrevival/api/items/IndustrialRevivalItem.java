package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.*;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.items.handlers.ItemHandler;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContent;
import org.irmc.industrialrevival.core.implemention.recipes.RecipeContents;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.ItemUtils;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * An industrial revival item.<br>
 * By default, the item is not registered in the game.<br>
 * To register the item, use the register method.<br>
 * The block is placeable by default if you want it to be unplaceable, please see {@link NotPlaceable}
 */
public class IndustrialRevivalItem {
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
            @Nonnull ItemGroup group,
            @Nonnull IndustrialRevivalItemStack itemStack,
            @Nonnull RecipeType recipeType,
            @Nonnull ItemStack[] recipe) {
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
        this.locked = true;
        this.addon = addon;
        if (!addon.getPlugin().isEnabled()) {
            return null;
        }

        try {
            preRegister();
        } catch (Exception e) {
            return null;
        }

        postRegister();

        IndustrialRevival.getInstance().getItemTextureService().setUpTexture(getItem());
        itemStack.lock();

        if (!group.getItems().contains(this)) {
            group.addItem(this);
        }

        RecipeContents.addRecipeContent(
                this.getId(), new RecipeContent(recipeType, recipeType.getMakerItem(), recipe, this));
        IndustrialRevival.getInstance().getRegistry().getItems().put(getId(), this);



        return this;
    }

    public Component getItemName() {
        return ItemUtils.getDisplayName(getItem());
    }

    protected void addItemHandlers(ItemHandler... handlers) {
        for (ItemHandler handler : handlers) {
            itemHandlers.put(handler.getClass(), handler);
        }
    }

    @Nullable public <T extends ItemHandler> T getItemHandler(Class<T> clazz) {
        return (T) itemHandlers.get(clazz);
    }

    protected void preRegister() throws Exception {
        for (ItemHandler handler : itemHandlers.values()) {
            IncompatibleItemHandlerException ex = handler.isCompatible(this);
            if (ex != null) {
                throw ex;
            }

            if (handler instanceof BlockTicker && !ItemUtils.isBlock(getItem().getType())) {
                throw new UnsupportedOperationException("Only actual block can have a BlockTicker!");
            }
        }

        if (this instanceof ItemDroppable && !ItemUtils.isBlock(getItem().getType())) {
            throw new UnsupportedOperationException("Only actual block can be drop items!");
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

        if (this instanceof VanillaSmeltingItem vsi) {
            NamespacedKey key = new NamespacedKey(addon.getPlugin(), getId().toLowerCase());
            FurnaceRecipe fr = new FurnaceRecipe(
                    key, vsi.getRecipeOutput(), vsi.getRecipeInput(), vsi.getExp(), vsi.getCookingTime());

            Bukkit.addRecipe(fr);
        }
    }

    public static IndustrialRevivalItem getById(String id) {
        return IndustrialRevival.getInstance().getRegistry().getItems().get(id);
    }

    public static IndustrialRevivalItem getByItem(@Nullable ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return null;
        }

        if (item instanceof IndustrialRevivalItemStack irStack) {
            return getById(irStack.getId());
        }

        String id = item.getItemMeta().getPersistentDataContainer().get(Constants.ITEM_ID_KEY, PersistentDataType.STRING);
        if (id != null) {
            return getById(id);
        }

        return null;
    }
}
