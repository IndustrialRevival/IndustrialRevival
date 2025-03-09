package org.irmc.industrialrevival.api.lang;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.irmc.pigeonlib.objects.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@Getter
public class LangableItemStack extends CustomItemStack {
    private static final Map<String, Object> CONST_PUBLIC_PLACEHOLDERS = new HashMap<>();
    private static final Map<String, Supplier<?>> VARIABLE_PUBLIC_PLACEHOLDERS = new HashMap<>();
    private static final Map<IndustrialRevivalAddon, Map<String, Object>> CONST_PRIVATE_PLACEHOLDERS = new HashMap<>();
    private static final Map<IndustrialRevivalAddon, Map<String, Supplier<?>>> VARIABLE_PRIVATE_PLACEHOLDERS = new HashMap<>();
    private final @NotNull IndustrialRevivalAddon addon;
    private final @NotNull String key;
    private final @NotNull Component rawDisplayName;
    private final @Nullable List<Component> rawLore;
    @Getter
    private boolean variable = false;

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material) {
        super(material);
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount) {
        super(material, amount);
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        this(addon, key, material, getLangableItemStack(addon, key, material), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @NotNull Component displayName, @NotNull Component @NotNull ... lore) {
        this(addon, key, material, getLangableItemStack(addon, key, material), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @NotNull Component displayName, @NotNull List<Component> lore) {
        this(addon, key, material, getLangableItemStack(addon, key, material), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        this(addon, key, material, amount, getLangableItemStack(addon, key, material), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, Component displayName, Component... lore) {
        this(addon, key, material, amount, getLangableItemStack(addon, key, material), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack) {
        this(addon, key, itemStack, getLangableItemStack(addon, key, itemStack.getType()));
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount) {
        this(addon, key, itemStack, amount, getLangableItemStack(addon, key, itemStack.getType()));
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, String displayName, String... lore) {
        this(addon, key, itemStack, amount, getLangableItemStack(addon, key, itemStack.getType()), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, String displayName, String... lore) {
        this(addon, key, itemStack, getLangableItemStack(addon, key, itemStack.getType()), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, Component displayName, Component... lore) {
        this(addon, key, itemStack, amount, getLangableItemStack(addon, key, itemStack.getType()), displayName, lore);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Nullable LangableItemStack langableItemStack, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(material, langableItemStack == null ? displayName : langableItemStack.getBukkit().getItemMeta().getDisplayName(), langableItemStack == null ? lore : langableItemStack.getBukkit().getItemMeta().getLore().toArray(new String[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @Nullable LangableItemStack langableItemStack, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(material, amount, langableItemStack == null ? displayName : langableItemStack.getBukkit().getItemMeta().getDisplayName(), langableItemStack == null ? lore : langableItemStack.getBukkit().getItemMeta().getLore().toArray(new String[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @Nullable LangableItemStack langableItemStack, @NotNull Component displayName, @NotNull Component @NotNull ... lore) {
        super(material, amount, langableItemStack == null ? displayName : langableItemStack.rawDisplayName, langableItemStack == null ? lore : langableItemStack.rawLore.toArray(new Component[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @Nullable LangableItemStack langableItemStack) {
        super(itemStack, amount, langableItemStack == null ? itemStack.getItemMeta().displayName() : langableItemStack.rawDisplayName, langableItemStack == null ? itemStack.getItemMeta().lore().toArray(new Component[0]) : langableItemStack.rawLore.toArray(new Component[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Nullable LangableItemStack langableItemStack) {
        super(itemStack, langableItemStack == null ? itemStack.getItemMeta().getDisplayName() : langableItemStack.getBukkit().getItemMeta().getDisplayName(), langableItemStack == null ? itemStack.getItemMeta().getLore().toArray(new String[0]) : langableItemStack.getBukkit().getLore().toArray(new String[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Nullable LangableItemStack langableItemStack, @NotNull Component displayName, @NotNull Component @NotNull ... lore) {
        super(material, langableItemStack == null ? displayName : langableItemStack.rawDisplayName, langableItemStack == null ? lore : langableItemStack.rawLore.toArray(new Component[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @Nullable LangableItemStack langableItemStack, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(itemStack, amount, langableItemStack == null ? displayName : langableItemStack.getBukkit().getItemMeta().getDisplayName(), langableItemStack == null ? lore : langableItemStack.getBukkit().getLore().toArray(new String[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Nullable LangableItemStack langableItemStack, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(itemStack, langableItemStack == null ? displayName : langableItemStack.getBukkit().getItemMeta().getDisplayName(), langableItemStack == null ? lore : langableItemStack.getBukkit().getLore().toArray(new String[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @Nullable LangableItemStack langableItemStack, @NotNull Component displayName, @NotNull Component @NotNull ... lore) {
        super(itemStack.getType(), amount, langableItemStack == null ? displayName : langableItemStack.rawDisplayName, langableItemStack == null ? lore : langableItemStack.rawLore.toArray(new Component[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public LangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, @NotNull Material material, @Nullable LangableItemStack langableItemStack, @NotNull Component displayName, @NotNull List<Component> lore) {
        super(material, langableItemStack == null ? displayName : langableItemStack.rawDisplayName, langableItemStack == null ? lore.toArray(new Component[0]) : langableItemStack.rawLore.toArray(new Component[0]));
        this.addon = addon;
        this.key = key;
        this.rawDisplayName = getBukkit().displayName();
        this.rawLore = getBukkit().lore();
        uploadTranslate(this);
    }

    public static @NotNull Map<String, Object> getConstPublicPlaceholders() {
        return CONST_PUBLIC_PLACEHOLDERS;
    }

    public static @NotNull Map<String, Supplier<?>> getVariablePublicPlaceholders() {
        return VARIABLE_PUBLIC_PLACEHOLDERS;
    }

    /**
     * Registers constant placeholders for all LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code ("key1", value1, "key2", value2)} )
     */
    public static void registerConstPublicPlaceholders(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        CONST_PUBLIC_PLACEHOLDERS.putAll(getMap(placeholders));
    }

    /**
     * Registers variable placeholders for all LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code ("key1", () -> value1, "key2", () -> value2)} )
     */
    public static void registerVariablePublicPlaceholders(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        VARIABLE_PUBLIC_PLACEHOLDERS.putAll(getSupplierMap(placeholders));
    }

    /**
     * Gets the constant placeholders for this LangableItemStack.
     *
     * @param placeholders the placeholders to get (e.g. {@code ("key1", value1, "key2", value2)} )
     * @return the constant placeholders as a map
     */
    public static @NotNull Map<String, Object> getMap(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < placeholders.length; i += 2) {
            if (placeholders[i] instanceof String key) {
                map.put(key, placeholders[i + 1]);
            }
        }

        return map;
    }

    /**
     * Gets the variable placeholders for this LangableItemStack.
     *
     * @param placeholders the placeholders to get (e.g. {@code ("key1", () -> value1, "key2", () -> value2)} )
     * @return the variable placeholders as a map
     */
    public static @NotNull Map<String, Supplier<?>> getSupplierMap(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        Map<String, Supplier<?>> map = new HashMap<>();
        for (int i = 0; i < placeholders.length; i += 2) {
            if (placeholders[i] instanceof String key && placeholders[i + 1] instanceof Supplier<?> supplier) {
                map.put(key, supplier);
            }
        }
        return map;
    }

    @NotNull
    public static String translateString(@NotNull String name) {
        for (Map.Entry<String, Supplier<?>> entry : VARIABLE_PUBLIC_PLACEHOLDERS.entrySet()) {
            name = name.replace("{" + entry.getKey() + "}", entry.getValue().get().toString());
        }

        for (Map.Entry<String, Object> entry : CONST_PUBLIC_PLACEHOLDERS.entrySet()) {
            name = name.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return name;
    }

    @NotNull
    public static String translateString(@NotNull IndustrialRevivalAddon addon, @NotNull String name) {
        name = translateString(name);
        for (Map.Entry<String, Supplier<?>> entry : VARIABLE_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).entrySet()) {
            name = name.replace("{" + entry.getKey() + "}", entry.getValue().get().toString());
        }

        for (Map.Entry<String, Object> entry : CONST_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).entrySet()) {
            name = name.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return name;
    }

    @NotNull
    public static List<String> translateString(@NotNull List<String> lore) {
        List<String> translatedLore = new ArrayList<>();
        for (String line : lore) {
            translatedLore.add(translateString(line));
        }
        return translatedLore;
    }

    @NotNull
    public static List<String> translateString(@NotNull String @NotNull ... lore) {
        List<String> translatedLore = new ArrayList<>();
        for (String line : lore) {
            translatedLore.add(translateString(line));
        }
        return translatedLore;
    }

    public static @NotNull List<String> translateString(@NotNull IndustrialRevivalAddon addon, @NotNull List<String> lore) {
        List<String> translatedLore = new ArrayList<>();
        for (String line : lore) {
            translatedLore.add(translateString(addon, line));
        }
        return translatedLore;
    }

    public static @NotNull Component translate(@NotNull Component component) {
        for (Map.Entry<String, Supplier<?>> entry : VARIABLE_PUBLIC_PLACEHOLDERS.entrySet()) {
            component = component.replaceText(builder -> builder.match("{" + entry.getKey() + "}").replacement(entry.getValue().get().toString()));
        }

        for (Map.Entry<String, Object> entry : CONST_PUBLIC_PLACEHOLDERS.entrySet()) {
            component = component.replaceText(builder -> builder.match("{" + entry.getKey() + "}").replacement(entry.getValue().toString()));
        }
        return component;
    }

    public static @NotNull List<Component> translate(Component @NotNull ... lore) {
        List<Component> translatedLore = new ArrayList<>();
        for (Component line : lore) {
            translatedLore.add(translate(line));
        }
        return translatedLore;
    }

    @NotNull
    public static Component translate(@NotNull IndustrialRevivalAddon addon, @NotNull Component component) {
        component = translate(component);
        for (Map.Entry<String, Supplier<?>> entry : VARIABLE_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).entrySet()) {
            component = component.replaceText(builder -> builder.match("{" + entry.getKey() + "}").replacement(entry.getValue().get().toString()));
        }

        for (Map.Entry<String, Object> entry : CONST_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).entrySet()) {
            component = component.replaceText(builder -> builder.match("{" + entry.getKey() + "}").replacement(entry.getValue().toString()));
        }
        return component;
    }

    public static @NotNull List<Component> translate(@NotNull List<Component> lore) {
        List<Component> translatedLore = new ArrayList<>();
        for (Component line : lore) {
            translatedLore.add(translate(line));
        }
        return translatedLore;
    }

    @NotNull
    public static List<Component> translate(@NotNull IndustrialRevivalAddon addon, @NotNull List<Component> lore) {
        List<Component> translatedLore = new ArrayList<>();
        for (Component line : lore) {
            translatedLore.add(translate(addon, line));
        }
        return translatedLore;
    }

    @Nullable
    public static Object getPublicPlaceholder(@NotNull String key) {
        return CONST_PUBLIC_PLACEHOLDERS.get(key);
    }

    @Nullable
    public static Object getPrivatePlaceholder(@NotNull IndustrialRevivalAddon addon, @NotNull String key) {
        return CONST_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).get(key);
    }

    @Nullable
    public static Object getVariablePublicPlaceholder(@NotNull String key) {
        return VARIABLE_PUBLIC_PLACEHOLDERS.getOrDefault(key, () -> null).get();
    }

    @Nullable
    public static Object getVariablePrivatePlaceholder(@NotNull IndustrialRevivalAddon addon, @NotNull String key) {
        return VARIABLE_PRIVATE_PLACEHOLDERS.getOrDefault(addon, new HashMap<>()).getOrDefault(key, () -> null).get();
    }

    /**
     * Uploads the translation of this LangableItemStack to the default language file for the given addon.
     *
     * @param itemStack The LangableItemStack to upload.
     */
    public static void uploadTranslate(@NotNull LangableItemStack itemStack) {
        FileConfiguration configuration = getDefaultLanguageFile(itemStack.getAddon());
        writeItemStack(itemStack.getKey(), itemStack.getBukkit(), configuration);
    }

    /**
     * Gets a LangableItemStack from the default language file for the given addon and key.
     *
     * @param addon    The addon to get the LangableItemStack from.
     * @param key      The key of the LangableItemStack.
     * @param material The material of the LangableItemStack.
     * @return The LangableItemStack with the given key and material, or null if it doesn't exist.
     */
    @Nullable
    public static LangableItemStack getLangableItemStack(@NotNull IndustrialRevivalAddon addon, @NotNull String key, Material material) {
        FileConfiguration configuration = getDefaultLanguageFile(addon);
        if (configuration.contains(key + ".displayName")) {
            String displayName = configuration.getString(key + ".displayName");
            if (displayName == null) {
                return null;
            }

            List<String> lore = null;
            if (configuration.contains(key + ".lore")) {
                lore = configuration.getStringList(key + ".lore");
            }

            LangableItemStack langableItemStack;
            if (lore != null) {
                langableItemStack = new LangableItemStack(addon, key, material, displayName, lore.toArray(new String[0]));
            } else {
                langableItemStack = new LangableItemStack(addon, key, material, displayName);
            }

            if (configuration.contains(key + ".modelId")) {
                langableItemStack.setCustomModel(configuration.getInt(key + ".modelId"));
            }
            return langableItemStack;
        } else {
            return null;
        }
    }

    /**
     * Gets the default language file for the given addon.
     *
     * @param addon The addon to get the language file for.
     * @return The default language file for the given addon.
     */
    @NotNull
    public static FileConfiguration getDefaultLanguageFile(@NotNull IndustrialRevivalAddon addon) {
        File addonLanguagesFolder = new File(Constants.Files.LANGUAGES_FOLDER, addon.getName());
        if (!addonLanguagesFolder.exists()) {
            addonLanguagesFolder.mkdirs();
        }
        File languageFile = new File(addonLanguagesFolder, addon.getLanguage().getCode() + ".yml");
        if (!languageFile.exists()) {
            try {
                languageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(languageFile);
    }

    /**
     * Writes the ItemStack to the configuration.
     *
     * @param key           The key of the ItemStack.
     * @param itemStack     The ItemStack to write.
     * @param configuration The configuration to write to.
     * @return The updated configuration.
     */
    @CanIgnoreReturnValue
    private static FileConfiguration writeItemStack(@NotNull String key, @NotNull ItemStack itemStack, @NotNull FileConfiguration configuration) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            String displayName = meta.getDisplayName();
            configuration.set(key + ".displayName", displayName);

            List<String> lore = meta.getLore();
            if (lore != null) {
                configuration.set(key + ".lore", lore);
            }

            if (meta.hasCustomModelData()) {
                configuration.set(key + ".modelId", meta.getCustomModelData());
            }
        }
        return configuration;
    }

    /**
     * Registers a constant placeholder for this LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code }("key1", value1, "key2", value2)} )
     * @return this LangableItemStack
     */
    public @NotNull LangableItemStack registerConstPrivatePlaceholder(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        CONST_PRIVATE_PLACEHOLDERS.computeIfAbsent(addon, _ -> new HashMap<>()).putAll(getMap(placeholders));
        return this;
    }

    /**
     * Registers a constant placeholder for all LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code ("key1", value1, "key2", value2)} )
     * @return this LangableItemStack
     */
    public @NotNull LangableItemStack registerConstPublicPlaceholder(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        CONST_PUBLIC_PLACEHOLDERS.putAll(getMap(placeholders));
        return this;
    }

    /**
     * Registers a variable placeholder for this LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code ("key1", () -> value1, "key2", () -> value2)} )
     * @return this LangableItemStack
     */
    public @NotNull LangableItemStack registerVariablePrivatePlaceholder(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        VARIABLE_PRIVATE_PLACEHOLDERS.computeIfAbsent(addon, _ -> new HashMap<>()).putAll(getSupplierMap(placeholders));
        this.variable = true;
        return this;
    }

    /**
     * Registers a variable placeholder for all LangableItemStack.
     *
     * @param placeholders the placeholders to register (e.g. {@code ("key1", () -> value1, "key2", () -> value2)} )
     * @return this LangableItemStack
     */
    public @NotNull LangableItemStack registerVariablePublicPlaceholder(Object @NotNull ... placeholders) {
        Preconditions.checkNotNull(placeholders, "placeholders cannot be null");
        Preconditions.checkArgument(placeholders.length % 2 == 0, "placeholders must be even");
        VARIABLE_PUBLIC_PLACEHOLDERS.putAll(getSupplierMap(placeholders));
        this.variable = true;
        return this;
    }

    /**
     * Updates the placeholders in this LangableItemStack.
     *
     * @return a pair of a boolean indicating if the placeholders were updated and the updated ItemStack
     * @apiNote DO NOT use this method if this do NOT CONTAIN any variable placeholders
     */
    @NotNull
    public Pair<Boolean, ItemStack> update() {
        ItemStack clone = this.getBukkit().clone();
        if (this.isVariable()) {
            clone.editMeta(meta -> {
                meta.displayName(translate(this.getRawDisplayName()));
                meta.lore(translate(this.getRawLore()));
            });
            return new Pair<>(true, clone);
        } else {
            return new Pair<>(false, clone);
        }
    }
}
