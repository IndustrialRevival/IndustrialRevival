package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.ItemDroppable;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.attributes.VanillaSmeltingItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.ItemHandler;
import org.irmc.industrialrevival.api.menu.Displayable;
import org.irmc.industrialrevival.api.menu.gui.PageableMenu;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.api.recipes.RecipeContents;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.CraftMethod;
import org.irmc.industrialrevival.api.recipes.methods.ProduceMethod;
import org.irmc.industrialrevival.core.translation.ItemTranslator;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItemSetup;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.language.LanguageManager;
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
import java.util.Objects;
import java.util.Set;

/**
 * An industrial revival item. (Data class & Builder)<br>
 * <p>
 * IndustrialRevivalItem is a builder class for creating items.
 * It provides a set of methods for adding attributes to the item.
 * See more {@link IndustrialRevivalItemSetup}
 * <p>
 * By default, the item is not registered in the game.
 * To register the item, use {@link #register()}.
 * The block is placeable by default. If you want it to
 * be unplaceable, implement the {@link NotPlaceable} interface.
 * <p>
 * See more in package {@link org.irmc.industrialrevival.api.items}
 *
 * @author balugaq
 * @author linjinhong11
 * @noinspection ALL
 * @see NotPlaceable
 * @see IndustrialRevivalItemSetup
 */
@SuppressWarnings("unused")
public class IndustrialRevivalItem implements Keyed, Displayable<IndustrialRevivalItem> {
    private final Map<Class<? extends ItemHandler>, ItemHandler> itemHandlers = new HashMap<>();

    @Getter
    private final List<ProduceMethod> produceMethods = new ArrayList<>();
    @Getter
    private final Set<ItemDictionary> itemDictionaries = new HashSet<>();
    private final Set<String> disabledInWorld = new HashSet<>();
    @Getter
    private final Set<ItemGroup> group = new HashSet<>();

    @Getter
    @NotNull
    private IndustrialRevivalAddon addon;
    @Getter
    private NamespacedKey id;
    @Getter
    @NotNull
    private ItemStack icon;
    @Getter
    private ItemStack recipeOutput;
    private ItemState state = ItemState.UNREGISTERED;

    @Getter
    @Nullable
    private String wikiText;

    @Getter
    private boolean enchantable = true;
    @Getter
    private boolean disenchantable = true;
    @Getter
    private boolean hideInGuide = false;
    /**
     * When it is true, item's name and lore will be auto-translated
     */
    private boolean autoTranslation = true;
    /**
     * When it is true, item's addon will be auto-inferred from the {@link ItemGroup}'s addon or the {@link RecipeType}'s addon when registering the item.
     */
    private boolean autoInferAddon = true;
    private String cachedId;

    /**
     * Creates a new instance of {@link IndustrialRevivalItem}.
     */
    public IndustrialRevivalItem() {
    }

    /**
     * Gets the item from a {@link String}.
     *
     * @param id the {@link String} of the item
     * @return the item with the given {@link String}, or null if not found
     */
    @Nullable
    public static IndustrialRevivalItem getById(@NotNull String id) {
        if (id.contains(":")) {
            return getById(NamespacedKey.fromString(id));
        }

        for (IndustrialRevivalItem item : IRDock.getPlugin().getRegistry().getItems().values()) {
            if (item.getId().getKey().equals(id)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Gets the item from a {@link NamespacedKey}.
     *
     * @param id the {@link NamespacedKey} of the item
     * @return the item with the given {@link NamespacedKey}, or null if not found
     */
    @Nullable
    public static IndustrialRevivalItem getById(@NotNull NamespacedKey id) {
        return IRDock.getPlugin().getRegistry().getItems().get(id);
    }

    /**
     * Gets the item from an {@link ItemStack}.
     *
     * @param item the {@link ItemStack} to get the item from
     * @return the item with the given {@link ItemStack}, or null if not found
     */
    @Nullable
    public static IndustrialRevivalItem getByItem(@NotNull ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }

        @Nullable NamespacedKey id = PersistentDataAPI.getNamespacedKey(item.getItemMeta(), Constants.ItemStackKeys.ITEM_ID_KEY);
        if (id != null) {
            return getById(id);
        }

        return null;
    }

    /**
     * Avoid type erasure
     *
     * @param clazz the class to cast to
     * @param <T>   the type of the class to cast to, must be a subclass of {@link IndustrialRevivalItem}
     * @return this as the given class
     */
    public <T extends IndustrialRevivalItem> T cast(Class<T> clazz) {
        return (T) this;
    }

    /**
     * Gets all item handlers of the item.
     *
     * @return the item handlers of the item
     */
    public Collection<ItemHandler> getItemHandlers() {
        return itemHandlers.values();
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the ID of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem id(@NotNull String id) {
        return setId(id);
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the ID of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem id(@NotNull NamespacedKey id) {
        return setId(id);
    }

    /**
     * Sets the icon of the item.
     *
     * @param icon the icon of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setIcon(@NotNull ItemStack icon) {
        applyKeys(icon);
        this.icon = icon;
        return this;
    }

    public void applyKeys(ItemStack icon) {
        ItemMeta meta = icon.getItemMeta();
        meta.getPersistentDataContainer().set(ItemTranslator.TRANSLATE_KEY, PersistentDataType.STRING, getId().toString().replaceFirst(":", "-"));
        meta.getPersistentDataContainer().set(Constants.ItemStackKeys.ITEM_ID_KEY, PersistentDataType.STRING, getId().toString());
        icon.setItemMeta(meta);
    }

    /**
     * Sets the icon of the item.
     *
     * @param icon the icon of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem icon(@NotNull ItemStack icon) {
        return setIcon(icon);
    }

    /**
     * Sets the icon of the item.
     *
     * @param icon the icon of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem icon(@NotNull CustomItemStack icon) {
        return icon(new org.irmc.pigeonlib.items.CustomItemStack(icon));
    }

    /**
     * Sets the icon of the item.
     *
     * @param icon the icon of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem icon(@NotNull org.irmc.pigeonlib.items.CustomItemStack icon) {
        return icon(icon.getBukkit());
    }

    /**
     * Sets the recipe output of the item.
     *
     * @param recipeOutput the recipe output of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem recipeOutput(@NotNull ItemStack recipeOutput) {
        return setRecipeOutput(recipeOutput);
    }

    /**
     * Adds the {@link ItemGroup} of the item.
     *
     * @param group the {@link ItemGroup} of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem addItemGroup(@NotNull ItemGroup group) {
        checkRegistered();
        Preconditions.checkArgument(group != null, "ItemGroup cannot be null");
        this.group.add(group);
        group.addItem(this);
        inferAddon(group.getKey().getNamespace());
        return this;
    }

    /**
     * Sets the {@link ItemGroup} of the item.
     *
     * @param group the {@link ItemGroup} of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem itemGroup(@NotNull ItemGroup group) {
        return addItemGroup(group);
    }

    /**
     * Enables auto-translation of the item's name and lore.
     *
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem enableAutoTranslation() {
        checkRegistered();
        this.autoTranslation = true;
        return this;
    }

    /**
     * Disables auto-translation of the item's name and lore.
     *
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem disableAutoTranslation() {
        checkRegistered();
        this.autoTranslation = false;
        return this;
    }

    /**
     * Enables auto-infer-addon for the item.
     *
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem enableAutoInferAddon() {
        checkRegistered();
        this.autoInferAddon = true;
        return this;
    }

    /**
     * Disables auto-infer-addon for the item.
     *
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem disableAutoInferAddon() {
        checkRegistered();
        this.autoInferAddon = false;
        return this;
    }

    /**
     * Sets the wiki text of the item.
     *
     * @param wikiText the wiki text of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setWikiText(@NotNull String wikiText) {
        checkRegistered();
        Preconditions.checkArgument(wikiText != null, "WikiText cannot be null");
        this.wikiText = wikiText;
        return this;
    }

    /**
     * Sets the wiki text of the item.
     *
     * @param wikiText the wiki text of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem wikiText(@NotNull String wikiText) {
        return setWikiText(wikiText);
    }

    /**
     * Adds a {@link ProduceMethod} to the item.
     *
     * @param produceMethodGetter the getProduce method lambda of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem recipe(@NotNull IndustrialRevivalItem.ProduceMethodGetter produceMethodGetter) {
        Preconditions.checkArgument(produceMethodGetter != null, "craftMethodHandler cannot be null");
        ProduceMethod produceMethod = produceMethodGetter.getProduceMethod(this);
        if (produceMethod == null) {
            return this;
        }

        return recipe(produceMethod);
    }

    /**
     * Adds a {@link ProduceMethod} to the item.
     *
     * @param produceMethod the getProduce method of the item
     * @return this instance
     */
    public IndustrialRevivalItem recipe(@NotNull ProduceMethod produceMethod) {
        produceMethods.add(produceMethod);
        inferAddon(produceMethod.getRecipeType().getAddon());
        return this;
    }

    /**
     * Tag the item with the given {@link ItemDictionary}.
     *
     * @param itemDictionary the item dictionary of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem addItemDictionary(@NotNull ItemDictionary itemDictionary) {
        checkRegistered();
        Preconditions.checkArgument(itemDictionary != null, "ItemDictionary cannot be null");
        itemDictionary.tagItem(this, true);
        inferAddon(itemDictionary.getKey().getNamespace());
        return this;
    }

    /**
     * Sets disables the item.
     *
     * @param disabled     the disabled state of the item
     * @param saveToConfig if true, the disabled state will be saved to the config file
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setDisabled(boolean disabled, boolean saveToConfig) {
        checkRegistered();
        if (disabled) {
            this.state = ItemState.DISABLED;
        } else {
            this.state = ItemState.ENABLED;
        }

        if (saveToConfig) {
            IRDock.getPlugin().getItemSettings().disableItem(getId());
        }
        return this;
    }

    /**
     * Sets enchantable of the item.
     *
     * @param enchantable  the enchantable state of the item
     * @param saveToConfig if true, the enchantable state will be saved to the config file
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setEnchantable(boolean enchantable, boolean saveToConfig) {
        checkRegistered();
        this.enchantable = enchantable;

        if (saveToConfig) {
            getItemSetting().set("enchantable", enchantable);
        }
        return this;
    }

    /**
     * Sets disenchantable of the item.
     *
     * @param disenchantable the disenchantable state of the item
     * @param saveToConfig   if true, the disenchantable state will be saved to the config file
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        checkRegistered();
        this.disenchantable = disenchantable;

        if (saveToConfig) {
            getItemSetting().set("disenchantable", disenchantable);
        }
        return this;
    }

    /**
     * Sets disabled in the given world.
     *
     * @param world        the world to disable in
     * @param disabled     the disabled state of the item in the world
     * @param saveToConfig if true, the disabled state will be saved to the config file
     * @return
     */
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

    /**
     * Sets hideInGuide of the item.
     *
     * @param hideInGuide  the hideInGuide state of the item
     * @param saveToConfig if true, the hideInGuide state will be saved to the config file
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        checkRegistered();
        this.hideInGuide = hideInGuide;
        if (saveToConfig) {
            getItemSetting().set("hide_in_guide", hideInGuide);
        }
        return this;
    }

    /**
     * Gets the ID of the item.
     *
     * @return the ID of the item
     */
    @NotNull
    public NamespacedKey getId() {
        return this.id;
    }

    /**
     * Sets ID of the item.
     *
     * @param id the ID of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setId(@NotNull NamespacedKey id) {
        this.id = id;
        return this;
    }

    /**
     * Sets ID of the item.
     *
     * @param id the ID of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setId(@NotNull String id) {
        if (addon == null) {
            // allowed to generate ID without addon when registering
            cachedId = id;
            return this;
        }
        this.id = new NamespacedKey(addon.getPlugin(), id);
        return this;
    }

    /**
     * Registers the item in the registry.
     *
     * @return NULL IF THE ITEM IS NOT REGISTERED SUCCESSFULLY!!
     */
    @CanIgnoreReturnValue
    @Nullable
    public IndustrialRevivalItem register() {
        Preconditions.checkArgument(addon != null, "Losing addon reference! Please set it before registering the item.");
        checkRegistered();

        if (!IRDock.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Cannot register item before IndustrialRevival is enabled");
        }

        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Cannot register item before your plugin is enabled");
        }

        if (id == null) {
            throw new IllegalStateException("Id cannot be null");
        }

        if (autoTranslation) {
            LanguageManager lm = new LanguageManager(addon.getPlugin());
            Component name = lm.getItemName(getId().getKey());
            if (name != null) {
                getIcon().getItemMeta().displayName(name);
            }

            List<Component> lore = lm.getItemLore(getId().getKey());
            if (lore != null && !lore.isEmpty()) {
                getIcon().getItemMeta().lore(lore);
            }
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

        IRDock.getPlugin().getRegistry().registerItem(this);
        this.state = this.state == ItemState.UNREGISTERED ? ItemState.ENABLED : this.state;

        return this;
    }

    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    @NotNull
    public Component getItemName() {
        return ItemUtils.getDisplayName(getIcon());
    }

    /**
     * Gets the {@link ItemHandler} for the given class.
     *
     * @param clazz the class of the item handler to get
     * @param <T>   the type of the item handler to get, must be a subclass of {@link ItemHandler}
     * @return the item handler for the given class, or null if not found
     */
    @Nullable
    public <T extends ItemHandler> T getItemHandler(Class<T> clazz) {
        return clazz.cast(itemHandlers.get(clazz));
    }

    /**
     * Adds the given item handlers to the item.
     *
     * @param handlers the item handlers to add
     * @return this instance
     */
    @CanIgnoreReturnValue
    protected IndustrialRevivalItem addItemHandlers(@NotNull ItemHandler... handlers) {
        checkRegistered();
        for (ItemHandler handler : handlers) {
            itemHandlers.put(handler.getIdentifier(), handler);
        }
        return this;
    }

    /**
     * Adds the given item handlers to the item.
     *
     * @param handlers the item handlers to add
     * @return this instance
     */
    @CanIgnoreReturnValue
    protected IndustrialRevivalItem itemHandlers(@NotNull ItemHandler... handlers) {
        return addItemHandlers(handlers);
    }

    /**
     * Pre-registers the item.
     *
     * @throws Exception if the item is not compatible with any of the item handlers
     */
    @OverridingMethodsMustInvokeSuper
    protected void preRegister() throws Exception {
        for (ItemHandler handler : itemHandlers.values()) {
            IncompatibleItemHandlerException ex = handler.isCompatible(this);
            if (ex != null) {
                throw ex;
            }
        }

        if (this instanceof ItemDroppable && !ItemUtils.isActualBlock(getIcon().getType())) {
            throw new UnsupportedOperationException("Only actual block can be drop items!");
        }
    }

    /**
     * Post-registers the item.
     * Registers as {@link MultiBlock}, {@link MobDropItem}, {@link BlockDropItem} and so on.
     */
    @OverridingMethodsMustInvokeSuper
    protected void postRegister() {
        if (this instanceof MultiBlock mb) {
            IRDock.getPlugin().getRegistry().registerMultiBlock(mb);
        }
        if (this instanceof MobDropItem mdi) {
            IRDock.getPlugin().getRegistry().registerMobDrop(mdi);
        }

        if (this instanceof BlockDropItem bdi) {
            IRDock.getPlugin().getRegistry().registerBlockDrop(bdi);
        }

        if (this instanceof VanillaSmeltingItem vsi) {
            NamespacedKey key = new NamespacedKey(addon.getPlugin(), "irsi_" + getId().getNamespace() + "_" + getId().getKey());
            FurnaceRecipe fr = new FurnaceRecipe(
                    key, vsi.getSmeltOutput(), vsi.getRecipeInput(), vsi.getExp(), vsi.getCookingTime());

            Bukkit.addRecipe(fr);
        }

        for (var produceMethod : produceMethods) {
            if (produceMethod instanceof CraftMethod craftMethod) {
                if (craftMethod.getRecipeType() == RecipeType.VANILLA_CRAFTING) {
                    NamespacedKey key = new NamespacedKey(addon.getPlugin(), "irvc_" + getId().getNamespace() + "_" + getId().getKey());
                    ShapedRecipe shapedRecipe = new ShapedRecipe(key, getIcon().clone());
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
    }

    /**
     * Checks if the item is registered.
     *
     * @throws IllegalStateException if the item is registered
     * @apiNote This method should be only called before registering the item.
     */
    protected final void checkRegistered() {
        if (state != ItemState.UNREGISTERED) {
            throw new IllegalStateException("This IndustrialRevivalItem is registered and cannot be modified");
        }

        if (id == null && addon != null && cachedId != null) {
            id = new NamespacedKey(addon.getPlugin(), cachedId);
        }
    }

    /**
     * Gets the icon of the item.
     *
     * @param world the world to get the icon for, or null to get the default icon
     * @return the icon of the item
     */
    public boolean isDisabledInWorld(@NotNull World world) {
        return state == ItemState.DISABLED || disabledInWorld.contains(world.getName());
    }

    /**
     * Gets the setting of the item.
     *
     * @return the setting of the item
     */
    @NotNull
    protected ConfigurationSection getItemSetting() {
        return IRDock.getPlugin().getItemSettings().getSetting(getId());
    }

    public IndustrialRevivalItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        checkRegistered();
        Preconditions.checkArgument(addon != null, "Addon cannot be null");
        this.addon = addon;
        return this;
    }

    public IndustrialRevivalItem addon(@NotNull IndustrialRevivalAddon addon) {
        return setAddon(addon);
    }

    public final boolean isEnabled() {
        return state == ItemState.ENABLED;
    }

    public final boolean isDisabled() {
        return !isEnabled();
    }

    @Nullable
    public ItemStack[] getRecipeIngredients(@NotNull RecipeType recipeType) {
        for (var produceMethod : produceMethods) {
            if (produceMethod.getRecipeType() == recipeType) {
                return produceMethod.getIngredients();
            }
        }

        return null;
    }

    @Nullable
    public ItemStack[] getRecipeIngredients() {
        ProduceMethod method = produceMethods.stream().findFirst().orElse(null);
        if (method != null) {
            return method.getIngredients();
        }

        return null;
    }

    @Nullable
    public ItemStack[] getRecipeOutput(@NotNull RecipeType recipeType) {
        for (ProduceMethod produceMethod : produceMethods) {
            if (produceMethod.getRecipeType() == recipeType) {
                return produceMethod.getOutput();
            }
        }

        return null;
    }

    @Nullable
    public ItemStack[] getRecipeOutput() {
        ProduceMethod method = produceMethods.stream().findFirst().orElse(null);
        if (method != null) {
            return method.getOutput();
        }

        return null;
    }

    /**
     * Sets the recipe output of the item.
     *
     * @param recipeOutput the recipe output of the item
     * @return this instance
     */
    @NotNull
    public IndustrialRevivalItem setRecipeOutput(@NotNull ItemStack recipeOutput) {
        this.recipeOutput = recipeOutput;
        return this;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId(), getIcon(), itemHandlers, produceMethods, itemDictionaries, disabledInWorld, group, addon, state, autoTranslation, wikiText, enchantable, disenchantable, hideInGuide);
    }

    @Deprecated
    public ItemStack getItemStack() {
        return getIcon();
    }

    public final boolean isRegistered() {
        return IRDock.getPlugin().getRegistry().getItems().containsKey(getId());
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return id;
    }

    public final void inferAddon(@NotNull String pluginName) {
        if (this.autoInferAddon && this.addon == null) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
            if (plugin instanceof IndustrialRevivalAddon ira) {
                this.addon = ira;
            }
        }
    }

    public final void inferAddon(@NotNull IndustrialRevivalAddon addon) {
        if (this.autoInferAddon && this.addon == null) {
            this.addon = addon;
        }
    }

    public Patcher patcher() {
        return new Patcher(this);
    }

    @Override
    public ItemStack getDisplayItem(IndustrialRevivalItem item) {
        return PageableMenu.getDisplayItem0(item);
    }

    public enum ItemState {
        UNREGISTERED,
        ENABLED,
        DISABLED
    }

    @FunctionalInterface
    public interface ProduceMethodGetter {
        @NotNull ProduceMethod getProduceMethod(@NotNull IndustrialRevivalItem item);
    }

    @RequiredArgsConstructor
    public static class Patcher {
        private final IndustrialRevivalItem item;

        public Patcher patchId(NamespacedKey id) {
            item.setId(id);
            return this;
        }

        public Patcher patchIcon(ItemStack icon) {
            item.setIcon(icon);
            return this;
        }

        // todo
    }
}
