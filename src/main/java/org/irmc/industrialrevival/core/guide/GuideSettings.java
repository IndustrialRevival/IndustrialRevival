package org.irmc.industrialrevival.core.guide;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.api.player.PlayerSettings;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.utils.TextUtil;
import org.irmc.pigeonlib.items.CustomItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
public class GuideSettings {
    public static final NamespacedKey KEY_GUIDE_MODE = KeyUtil.customKey("guide_mode");
    public static final NamespacedKey KEY_FIREWORKS_ENABLED = KeyUtil.customKey("fireworks_enabled");
    public static final NamespacedKey KEY_LEARNING_ANIMATION_ENABLED = KeyUtil.customKey("learning_animation_enabled");
    public static final NamespacedKey KEY_LANGUAGE = KeyUtil.customKey("language");

    public static final PlayerSettings<GuideMode> GUIDE_MODE = PlayerSettings.constof(
            Player::isOp,
            KEY_GUIDE_MODE,
            GuideMode.SURVIVAL,
            mode -> new CustomItemStack(
                    Material.BOOK,
                    TextUtil.upperFirstLetterOnly(mode.name()) + " Guide",
                    "Survival Guide"
            ).getBukkit());

    public static final PlayerSettings<Boolean> FIREWORKS_ENABLED = PlayerSettings.constof(
            KEY_FIREWORKS_ENABLED,
            true,
            enabled -> new CustomItemStack(
                    Material.FIREWORK_STAR,
                    "Fireworks",
                    "Fireworks: " + TextUtil.getBooleanText(enabled)
            ).getBukkit());

    public static final PlayerSettings<Boolean> LEARNING_ANIMATION_ENABLED = PlayerSettings.constof(
            KEY_LEARNING_ANIMATION_ENABLED,
            true,
            enabled -> new CustomItemStack(
                    Material.BOOK,
                    "Learning Animation",
                    "Learning Animation: " + TextUtil.getBooleanText(enabled)
            ).getBukkit());

    public static final PlayerSettings<String> LANGUAGE = PlayerSettings.constof(
            KEY_LANGUAGE,
            Locale.getDefault().toLanguageTag(),
            language -> new CustomItemStack(
                    Material.BOOK,
                    "Language",
                    "Language: " + language).getBukkit());

    public static final GuideSettings DEFAULT_SETTINGS =
            GuideSettings.of(
                    GUIDE_MODE.clone(),
                    FIREWORKS_ENABLED.clone(),
                    LEARNING_ANIMATION_ENABLED.clone(),
                    LANGUAGE.clone()
            );

    public final Map<NamespacedKey, PlayerSettings<?>> settings;

    public <T> PlayerSettings<T> getPlayerSettings(PlayerSettings<T> clazz) {
        return (PlayerSettings<T>) settings.get(clazz.getKey());
    }

    public <T> PlayerSettings<T> getPlayerSettings(NamespacedKey key) {
        return (PlayerSettings<T>) settings.get(key);
    }

    public <T> void setGuideSettings(PlayerSettings<T> clazz, T value) {
        getPlayerSettings(clazz).setValue(value);
    }

    public GuideSettings(Map<NamespacedKey, PlayerSettings<?>> settings) {
        this.settings = settings;
    }

    public static GuideSettings of(Map<NamespacedKey, PlayerSettings<?>> settings) {
        return new GuideSettings(settings);
    }

    public static GuideSettings of(PlayerSettings<?>... settings) {
        Map<NamespacedKey, PlayerSettings<?>> map = new HashMap<>();
        for (PlayerSettings<?> setting : settings) {
            map.put(setting.getKey(), setting);
        }

        return of(map);
    }
}
