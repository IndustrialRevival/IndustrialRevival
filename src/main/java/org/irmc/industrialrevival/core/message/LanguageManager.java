package org.irmc.industrialrevival.core.message;

import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.jetbrains.annotations.Nullable;

/**
 * A class to manage language files and messages.
 * You can create a new instance of this class by passing a reference to your plugin.
 */
public final class LanguageManager {
    private final Plugin plugin;

    private final Map<String, YamlConfiguration> configurations = new HashMap<>();
    private YamlConfiguration defaultConfiguration;

    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;

        loadLanguages();
    }

    private void loadLanguages() {
        defaultConfiguration =
                YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "language/en-US.yml"));

        File pluginFolder = plugin.getDataFolder();

        URL fileURL = Objects.requireNonNull(plugin.getClass().getClassLoader().getResource("language/"));
        String jarPath = fileURL.toString().substring(0, fileURL.toString().indexOf("!/") + 2);

        try {
            URL jar = new URL(jarPath);
            JarURLConnection jarCon = (JarURLConnection) jar.openConnection();
            JarFile jarFile = jarCon.getJarFile();
            Enumeration<JarEntry> jarEntries = jarFile.entries();

            while (jarEntries.hasMoreElements()) {
                JarEntry entry = jarEntries.nextElement();
                String name = entry.getName();
                if (name.startsWith("language/") && !entry.isDirectory()) {
                    String realName = name.replaceAll("language/", "");
                    try (InputStream stream = plugin.getClass().getClassLoader().getResourceAsStream(name)) {
                        File destinationFile = new File(pluginFolder, "language/" + realName);

                        if (!destinationFile.exists() && stream != null) {
                            plugin.saveResource("language/" + realName, false);
                        }

                        FileUtil.completeLangFile(plugin, "language/" + realName);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        File[] languageFiles = new File(pluginFolder, "language").listFiles();
        if (languageFiles != null) {
            for (File languageFile : languageFiles) {
                String language = convertToRightLangCode(languageFile.getName().replaceAll(".yml", ""));
                configurations.put(language, YamlConfiguration.loadConfiguration(languageFile));
            }
        }
    }

    private String convertToRightLangCode(String lang) {
        if (lang == null || lang.isBlank()) return "en-US";
        String[] split = lang.split("-");
        if (split.length == 1) {
            String[] split2 = lang.split("_");
            if (split2.length == 1) return lang;
            return lang.replace(split2[1], split2[1].toUpperCase()).replace("_", "-");
        }
        return lang.replace(split[1], split[1].toUpperCase());
    }

    public Component getItemName(String id) {
        return parseToComponent(getMsg(null, "item." + id + ".name"));
    }

    public List<Component> getItemLore(String id) {
        return parseToComponentList(getMsgList(null, "item." + id + ".lore"));
    }

    public Component getRecipeTypeName(NamespacedKey key) {
        return parseToComponent(getMsg(null, "recipe_type." + key.getKey() + ".name"));
    }

    public List<Component> getRecipeTypeLore(NamespacedKey key) {
        return parseToComponentList(getMsgList(null, "recipe_type." + key.getKey() + ".lore"));
    }

    public Component getGroupName(String id) {
        return parseToComponent(getMsg(null, "group." + id + ".name"));
    }

    public List<Component> getGroupLore(String id) {
        return parseToComponentList(getMsgList(null, "group." + id + ".lore"));
    }

    public void sendMessage(CommandSender CommandSender, String key, MessageReplacement... args) {
        CommandSender.sendMessage(parseToComponent(getMsg(CommandSender, key, args)));
    }

    public static Component parseToComponent(String msg) {
        return MiniMessage.miniMessage().deserialize(msg).decoration(TextDecoration.ITALIC, false);
    }

    public static List<Component> parseToComponentList(List<String> msgList) {
        return msgList.stream().map(LanguageManager::parseToComponent).toList();
    }

    public Component getMsgComponent(@Nullable CommandSender CommandSender, String key, MessageReplacement... args) {
        return parseToComponent(getMsg(CommandSender, key, args));
    }

    public List<Component> getMsgComponentList(
            @Nullable CommandSender CommandSender, String key, MessageReplacement... args) {
        return parseToComponentList(getMsgList(CommandSender, key, args));
    }

    public String getMsg(@Nullable CommandSender CommandSender, String key, MessageReplacement... args) {
        String msg = getConfiguration(CommandSender).getString(key);
        if (msg == null) {
            return key;
        }

        for (MessageReplacement arg : args) {
            msg = arg.parse(msg);
        }

        return msg;
    }

    public List<String> getMsgList(@Nullable CommandSender CommandSender, String key, MessageReplacement... args) {
        List<String> msgList = getConfiguration(CommandSender).getStringList(key);
        for (MessageReplacement arg : args) {
            msgList.replaceAll(arg::parse);
        }

        return msgList;
    }

    private Configuration getConfiguration(CommandSender p) {
        if (!(p instanceof Player pl)) {
            return defaultConfiguration;
        }

        return configurations.getOrDefault(pl.locale().toLanguageTag(), defaultConfiguration);
    }
}
