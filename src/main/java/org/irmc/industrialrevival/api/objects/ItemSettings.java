package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * Used to manage each items' settings in the `items-settings.yml` file.
 *
 * @author lijinhong11
 * @since 1.0
 */
@Getter
@ParametersAreNonnullByDefault
public class ItemSettings {
    private final YamlConfiguration itemCfg;

    public ItemSettings(YamlConfiguration itemCfg) {
        this.itemCfg = itemCfg;
    }

    public ConfigurationSection getSetting(NamespacedKey id) {
        return itemCfg.createSection("specific_items." + id);
    }

    public void disableItem(NamespacedKey id) {
        List<String> disabledItems = itemCfg.getStringList("disabled_items");
        IRDock.getPlugin().getRegistry().getItems().get(id.toString()).setDisabled(true, true);
        disabledItems.add(id.toString());
        itemCfg.set("disabled_items", disabledItems);
    }

    public boolean isItemDisabled(NamespacedKey id) {
        return itemCfg.getStringList("disabled_items").contains(id.toString());
    }
}
