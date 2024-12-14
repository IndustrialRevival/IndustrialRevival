package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.ItemDroppable;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.attributes.VanillaSmeltingItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.ItemHandler;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.api.recipes.RecipeContents;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * An industrial revival item.<br>
 *
 * IndustrialRevivalItem is a builder class for creating items.
 * It provides a set of methods for adding attributes to the item.
 * See more {@link IndustrialRevivalItemSetup}
 *
 * By default, the item is not registered in the game.
 * To register the item, use {@link #register()}.
 * The block is placeable by default. If you want it to
 * be unplaceable, implement the {@link NotPlaceable} interface.
 *
 * See more in package {@link org.irmc.industrialrevival.api.items}
 *
 * @author balugaq
 * @author linjinhong11
 * @see NotPlaceable
 * @see IndustrialRevivalItemSetup
 * @noinspection ALL
 */
@SuppressWarnings("unused")
public class IndustrialRevivalItem {
    private final Map<Class<? extends ItemHandler>, ItemHandler> itemHandlers = new HashMap<>();

    @Getter
    private final List<CraftMethod> craftMethods = new ArrayList<>();
    @Getter
    private final Set<ItemDictionary> itemDictionaries = new HashSet<>();
    private final Set<String> disabledInWorld = new HashSet<>();
    @Getter
    private final Set<ItemGroup> group = new HashSet<>();
    @Getter
    private IndustrialRevivalAddon addon;
    private IndustrialRevivalItemStack itemStack;
    private ItemState state = ItemState.UNREGISTERED;
    @Getter
    private Optional<String> wikiText;
    @Getter
    private boolean enchantable = true;
    @Getter
    private boolean disenchantable = true;
    @Getter
    private boolean hideInGuide = false;

    public IndustrialRevivalItem() {
    }

    public <T> T cast(Class<T> clazz) {
        return (T) this;
    }

    @Nullable
    public static IndustrialRevivalItem getById(@NotNull NamespacedKey id) {
        return IndustrialRevival.getInstance().getRegistry().getItems().get(id);
    }

    @Nullable
    public static IndustrialRevivalItem getByItem(@NotNull IndustrialRevivalItemStack irStack) {
        return getById(irStack.getId());
    }

    @Nullable
    public static IndustrialRevivalItem getByItem(@NotNull ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }

        String id = PersistentDataAPI.getString(item.getItemMeta(), Constants.ItemStackKeys.ITEM_ID_KEY);
        if (id != null) {
            return getById(id);
        }

        return null;
    }

    public Collection<ItemHandler> getItemHandlers() {
        return itemHandlers.values();
    }

    @NotNull
    public IndustrialRevivalItem addItemGroup(@NotNull ItemGroup group) {
        checkRegistered();
        Preconditions.checkArgument(group != null, "ItemGroup cannot be null");
        this.group.add(group);
        group.addItem(this);
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        checkRegistered();
        Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null");
        this.itemStack = itemStack;
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setWikiText(@NotNull String wikiText) {
        checkRegistered();
        Preconditions.checkArgument(wikiText != null, "WikiText cannot be null");
        this.wikiText = Optional.of(wikiText);
        return this;
    }

    @NotNull
    public IndustrialRevivalItem addCraftMethod(@NotNull CraftMethodHandler craftMethodHandler) {
        Preconditions.checkArgument(craftMethodHandler != null, "craftMethodHandler cannot be null");
        CraftMethod craftMethod = craftMethodHandler.getCraftMethod(this);
        if (craftMethod == null) {
            return this;
        }

        craftMethods.add(craftMethod);
        return this;
    }

    @NotNull
    public IndustrialRevivalItem addItemDictionary(@NotNull ItemDictionary itemDictionary) {
        checkRegistered();
        Preconditions.checkArgument(itemDictionary != null, "ItemDictionary cannot be null");
        itemDictionary.tagItem(this, true);
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setDisabled(boolean disabled, boolean saveToConfig) {
        checkRegistered();
        if (disabled) {
            this.state = ItemState.DISABLED;
        } else {
            this.state = ItemState.ENABLED;
        }
        // todo: save to config
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setEnchantable(boolean enchantable, boolean saveToConfig) {
        checkRegistered();
        this.enchantable = enchantable;
        // todo: save to config
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        checkRegistered();
        this.disenchantable = disenchantable;
        // todo: save to config
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        Preconditions.checkArgument(world != null, "World cannot be null");
        ConfigurationSection setting = getItemSetting();
        List<String> worlds = setting.getStringList("disabled_in_worlds");

        if (disabled) {
            disabledInWorld.add(world.getName());
            worlds.add(world.getName());
        } else {
            disabledInWorld.remove(world.getName());
            worlds.remove(world.getName());
        }

        if (saveToConfig) {
            setting.set("disabled_in_worlds", worlds);
        }
        return this;
    }

    @NotNull
    public IndustrialRevivalItem setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        checkRegistered();
        this.hideInGuide = hideInGuide;
        return this;
    }

    @NotNull
    public IndustrialRevivalItemStack getItem() {
        return itemStack;
    }

    @NotNull
    public NamespacedKey getId() {
        return itemStack.getId();
    }

    /**
     * Registers the item in the registry.
     *
     * @return WILL RETURN NULL IF THE ITEM IS NOT REGISTERED SUCCESSFULLY!!
     */
    @Nullable
    public IndustrialRevivalItem register() {
        Preconditions.checkArgument(addon != null, "Losing addon reference! Please set it before registering the item.");
        checkRegistered();

        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Cannot register item before your plugin is enabled");
        }

        try {
            this.preRegister();
        } catch (Exception e) {
            e.printStackTrace();
            this.state = ItemState.DISABLED;
            return null;
        }

        try {
            this.postRegister();
        } catch (Exception e) {
            e.printStackTrace();
            this.state = ItemState.DISABLED;
            return null;
        }

        IndustrialRevival.getInstance().getRegistry().registerItem(this);
        this.state = this.state == ItemState.UNREGISTERED ? ItemState.ENABLED : this.state;

        return this;
    }

    @NotNull
    public Component getItemName() {
        return ItemUtils.getDisplayName(getItem().getItemStack());
    }

    @NotNull
    public <T extends ItemHandler> T getItemHandler(Class<T> clazz) {
        return clazz.cast(itemHandlers.get(clazz));
    }

    @CanIgnoreReturnValue
    protected IndustrialRevivalItem addItemHandlers(@NotNull ItemHandler... handlers) {
        checkRegistered();
        for (ItemHandler handler : handlers) {
            itemHandlers.put(handler.getIdentifier(), handler);
        }
        return this;
    }

    @OverridingMethodsMustInvokeSuper
    protected void preRegister() throws Exception {
        for (ItemHandler handler : itemHandlers.values()) {
            IncompatibleItemHandlerException ex = handler.isCompatible(this);
            if (ex != null) {
                throw ex;
            }
        }

        if (this instanceof ItemDroppable && !ItemUtils.isActualBlock(getItem().getItemStack().getType())) {
            throw new UnsupportedOperationException("Only actual block can be drop items!");
        }
    }

    @OverridingMethodsMustInvokeSuper
    protected void postRegister() {
        if (this instanceof MultiBlock mb) {
            IndustrialRevival.getInstance().getRegistry().registerMultiBlock(mb);
        }
        if (this instanceof MobDropItem mdi) {
            IndustrialRevival.getInstance().getRegistry().registerMobDrop(mdi);
        }

        if (this instanceof BlockDropItem bdi) {
            IndustrialRevival.getInstance().getRegistry().registerBlockDrop(bdi);
        }

        if (this instanceof VanillaSmeltingItem vsi) {
            NamespacedKey key = new NamespacedKey(addon.getPlugin(), "irrt_" + getId().getNamespace() + "_" + getId().getKey());
            FurnaceRecipe fr = new FurnaceRecipe(
                    key, vsi.getRecipeOutput(), vsi.getRecipeInput(), vsi.getExp(), vsi.getCookingTime());

            Bukkit.addRecipe(fr);
        }

        for (CraftMethod craftMethod : craftMethods) {
            if (craftMethod.getRecipeType() == RecipeType.VANILLA_CRAFTING) {
                NamespacedKey key = new NamespacedKey(addon.getPlugin(), "irrt_" + getId().getNamespace() + "_" + getId().getKey());
                ShapedRecipe shapedRecipe = new ShapedRecipe(key, itemStack.cloneItemStack());
                shapedRecipe.shape("abc", "def", "ghi");
                char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
                for (int i = 0; i < 9; i++) {
                    if (craftMethod.getIngredients()[i] != null) {
                        shapedRecipe.setIngredient(chars[i], craftMethod.getIngredients()[i]);
                    }
                }

                Bukkit.addRecipe(shapedRecipe);
            }

            RecipeContents.addRecipeContent(this.getId(),
                    new RecipeContent(
                            craftMethod.getRecipeType(),
                            craftMethod.getRecipeType().getMakerItem(),
                            craftMethod.getIngredients(),
                            this));
        }
    }

    protected void checkRegistered() {
        if (state != ItemState.UNREGISTERED) {
            throw new IllegalStateException("Item is registered and cannot be modified");
        }
    }

    public boolean isDisabledInWorld(@NotNull World world) {
        return state == ItemState.DISABLED || disabledInWorld.contains(world.getName());
    }

    @NotNull
    protected ConfigurationSection getItemSetting() {
        return IndustrialRevival.getInstance().getItemSettings().getSetting(getId());
    }

    public IndustrialRevivalItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        checkRegistered();
        Preconditions.checkArgument(addon != null, "Addon cannot be null");
        this.addon = addon;
        return this;
    }

    public boolean isEnabled() {
        return state == ItemState.ENABLED;
    }

    public boolean isDisabled() {
        return !isEnabled();
    }

    @NotNull
    public ItemStack[] getRecipeIngredients(RecipeType recipeType) {
        for (CraftMethod craftMethod : craftMethods) {
            if (craftMethod.getRecipeType() == recipeType) {
                return craftMethod.getIngredients();
            }
        }

        return null;
    }

    @Nullable
    public ItemStack getRecipeOutput(RecipeType recipeType) {
        for (CraftMethod craftMethod : craftMethods) {
            if (craftMethod.getRecipeType() == recipeType) {
                return craftMethod.getOutput();
            }
        }

        return null;
    }

    public ItemStack getItemStack() {
        return itemStack.getItemStack();
    }

    public enum ItemState {
        UNREGISTERED,
        ENABLED,
        DISABLED
    }

    @FunctionalInterface
    public interface CraftMethodHandler {
        @NotNull CraftMethod getCraftMethod(@NotNull IndustrialRevivalItem item);
    }
}
