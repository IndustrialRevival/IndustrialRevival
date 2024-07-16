package org.irmc.industrialrevival.core.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.api.objects.Pair;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class LanguageManager {
    private final IndustrialRevival plugin;

    private final Map<String, YamlConfiguration> configurations = new HashMap<>();
    private YamlConfiguration defaultConfiguration;

    public LanguageManager(IndustrialRevival plugin) {
        this.plugin = plugin;

        loadLanguages();
    }

    private void loadLanguages() {
        defaultConfiguration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "language/en-US.yml"));

        File pluginFolder = plugin.getDataFolder();

        URL fileURL = Objects.requireNonNull(plugin.getClass().getClassLoader().getResource("lang/"));
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
                    String realName = name.replaceAll("language/","");
                    try (InputStream stream = plugin.getClass().getClassLoader().getResourceAsStream(name)) {
                        File destinationFile = new File(pluginFolder, "lang/" + realName);

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

    public static Component parseToComponent(String msg) {
        return MiniMessage.miniMessage().deserialize(msg);
    }

    public static List<Component> parseToComponentList(List<String> msgList) {
        return msgList.stream().map(LanguageManager::parseToComponent).toList();
    }

    @SafeVarargs
    public final Component getMsgComponent(@Nullable Player player, String key, Pair<String, String>... args) {
        return parseToComponent(getMsg(player, key, args));
    }

    @SafeVarargs
    public final String getMsg(@Nullable Player player, String key, Pair<String, String>... args) {
        String msg = getConfiguration(player).getString(key);
        if (msg == null) {
            return key;
        }

        for (Pair<String, String> arg : args) {
            msg = msg.replaceAll(arg.getKey(), arg.getValue());
        }

        return msg;
    }

    @SafeVarargs
    public final List<String> getMsgList(@Nullable Player player, String key, Pair<String, String>... args) {
        List<String> msgList = getConfiguration(player).getStringList(key);
        for (Pair<String, String> arg : args) {
            msgList.replaceAll(s -> s.replace(arg.getKey(), arg.getValue()));
        }

        return msgList;
    }

    private Configuration getConfiguration(Player p) {
        if (p == null) {
            return defaultConfiguration;
        }

        return configurations.getOrDefault(p.locale().toLanguageTag(), defaultConfiguration);
    }
}
