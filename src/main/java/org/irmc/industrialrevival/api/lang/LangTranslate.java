package org.irmc.industrialrevival.api.lang;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Getter
public class LangTranslate {
    public static final Map<IndustrialRevivalAddon, LangTranslate> ALL_LANG_TRANSLATE = new HashMap<>();
    private String messageKey = "message";
    private String nameKey = "name";
    private String loreKey = "lore";
    private String itemKey = "item";
    private final IndustrialRevivalAddon addon;
    private Language language;
    private final File langFolder;
    private File langFile;
    private FileConfiguration lang;
    public LangTranslate setMessageKey(@NotNull String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public LangTranslate setNameKey(@NotNull String nameKey) {
        this.nameKey = nameKey;
        return this;
    }

    public LangTranslate setLoreKey(@NotNull String loreKey) {
        this.loreKey = loreKey;
        return this;
    }

    public LangTranslate setItemKey(@NotNull String itemKey) {
        this.itemKey = itemKey;
        return this;
    }

    public LangTranslate(@NotNull IndustrialRevivalAddon addon) {
        this(addon, "lang");
    }

    public LangTranslate(@NotNull IndustrialRevivalAddon addon, @NotNull String folderName) {
        Preconditions.checkNotNull(addon, "addon cannot be null");
        Preconditions.checkNotNull(folderName, "folderName cannot be null");

        this.addon = addon;
        this.language = Language.ZH_CN;
        this.langFolder = new File(addon.getPlugin().getDataFolder(), folderName);
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }
        String langFileName = language.getCode() + ".yml";
        this.langFile = new File(langFolder, langFileName);
        if (!langFile.exists()) {
            try {
                langFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // load from resource
        String resourcePath = folderName + "/" + langFileName;
        InputStreamReader defaultReader = new InputStreamReader(addon.getPlugin().getResource(resourcePath), StandardCharsets.UTF_8);
        FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultReader);
        // add default config to file system
        Iterator<String> keys = defaultConfig.getKeys(true).iterator();

        while (keys.hasNext()) {
            String key = keys.next();
            if (!this.lang.contains(key)) {
                this.lang.set(key, defaultConfig.get(key));
            }
        }

        this.lang = YamlConfiguration.loadConfiguration(langFile);
        ALL_LANG_TRANSLATE.put(addon, this);
    }

    public void setLanguage(@NotNull Language language) {
        this.language = language;
        this.langFile = new File(langFolder, language.getCode() + ".yml");
        this.lang = YamlConfiguration.loadConfiguration(langFile);
    }

    @NotNull
    public String translate(@NotNull String key) {
        return getLang().getString(key);
    }

    @NotNull
    public String name(@NotNull String key, @NotNull String defaultValue) {
        return getLang().getString(path(itemKey, key, nameKey), defaultValue);
    }

    @NotNull
    public List<String> lore(@NotNull String key) {
        return getLang().getStringList(path(itemKey, key, loreKey));
    }

    @NotNull
    public String message(@NotNull String key, @NotNull String defaultValue) {
        return getLang().getString(path(messageKey, key), defaultValue);
    }

    /**
     * Decorates a string with placeholders
     * <p>
     *     Example:
     * <pre>
     *     String decorated = LangTranslate.decorate("Hello {name}, your balance is {balance}", "name", "John", "balance", 1000);
     *     // decorated = "Hello John, your balance is 1000"
     * </p>
     * @param string The string to decorate
     * @param args   The placeholders to replace in the string
     * @return The decorated string
     */
    @Contract("null, _ -> null; !null, _ -> !null")
    public String decorate(@Nullable String string, @Nullable Object... args) {
        if (string == null) {
            return null;
        }
        if (args == null || args.length == 0) {
            return string;
        }
        for (int i = 0; i < args.length; i += 2) {
            String placeholder = "{" + args[i] + "}";
            String value = String.valueOf(args[i + 1]);
            string = string.replace(placeholder, value);
        }
        return string;
    }

    @Contract("_, null -> null; _, !null -> !null")
    public String reg(@NotNull String key, @Nullable String defaultValue) {
        getLang().set(key, defaultValue);
        return defaultValue;
    }

    @Contract("_, null -> null; _, !null -> !null")
    public String regName(@NotNull String key, @Nullable String defaultValue) {
        return reg(path(itemKey, key, nameKey), defaultValue);
    }

    @Contract("_, null -> null; _, !null -> !null")
    public List<String> regLore(@NotNull String key, @Nullable List<String> defaultValue) {
        getLang().set(path(itemKey, key, loreKey), defaultValue);
        return defaultValue;
    }

    @Contract("_, null -> null; _, !null -> !null")
    public String regMessage(@NotNull String key, @Nullable String defaultValue) {
        return reg(path(messageKey, key), defaultValue);
    }

    @Nullable
    public FileConfiguration getLang() {
        return lang;
    }

    public LangTranslate save() throws IOException {
        lang.save(langFile);
        return this;
    }

    public static void saveAll() throws IOException {
        for (LangTranslate language : ALL_LANG_TRANSLATE.values()) {
            language.save();
        }
    }

    @NotNull
    public static String path(@NotNull String... path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            sb.append(path[i]).append((i == path.length - 1 ? "" : "."));
        }
        return sb.toString();
    }

    @NotNull
    public static String getTranslatedName(@NotNull Player player, @NotNull ItemStack itemStack) {
        // TODO: implement
        return null;
    }

    @NotNull
    public static ItemStack getTranslatedItem(@NotNull Player player, @NotNull ItemStack itemStack) {
        ItemStack translatedItem = itemStack.clone();
        // TODO: implement
        return translatedItem;
    }

    @NotNull
    public static void translateItem(@NotNull Player player, @NotNull ItemStack itemStack) {
        // TODO: implement
    }
}
