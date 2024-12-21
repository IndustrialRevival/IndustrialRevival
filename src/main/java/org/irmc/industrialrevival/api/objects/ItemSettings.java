package org.irmc.industrialrevival.api.objects;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.List;

public class ItemSettings {
    private final YamlConfiguration itemCfg;

    public ItemSettings(YamlConfiguration itemCfg) {
        this.itemCfg = itemCfg;
    }

    public YamlConfiguration getItemCfg() {
        return itemCfg;
    }

    public ConfigurationSection getSetting(NamespacedKey id) {
        return itemCfg.createSection("specific_items." + id.toString());
    }

    public void disableItem(NamespacedKey id) {
        List<String> disabledItems = itemCfg.getStringList("disabled_items");
        IndustrialRevival.getInstance().getRegistry().getItems().get(id.toString()).setDisabled(true, true);
        disabledItems.add(id.toString());
        itemCfg.set("disabled_items", disabledItems);
    }

    public boolean isItemDisabled(NamespacedKey id) {
        return itemCfg.getStringList("disabled_items").contains(id.toString());
    }
}
