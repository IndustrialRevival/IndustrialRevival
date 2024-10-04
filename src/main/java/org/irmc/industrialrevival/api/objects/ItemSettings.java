package org.irmc.industrialrevival.api.objects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.List;

public class ItemSettings {
    private final YamlConfiguration itemCfg;

    public ItemSettings(YamlConfiguration itemCfg) {
        this.itemCfg = itemCfg;
    }

    public ConfigurationSection getSetting(String id) {
        if (!id.equals(id.toUpperCase())) {
            throw new IllegalArgumentException("Item ID must be uppercase");
        }

        return itemCfg.createSection("specific_items." + id);
    }

    public void disableItem(String id) {
        if (!id.equals(id.toUpperCase())) {
            throw new IllegalArgumentException("Item ID must be uppercase");
        }

        List<String> disabledItems = itemCfg.getStringList("disabled_items");
        IndustrialRevival.getInstance().getRegistry().getItems().get(id).setDisabled(true);
        disabledItems.add(id);
        itemCfg.set("disabled_items", disabledItems);
    }

    public boolean isItemDisabled(String id) {
        return itemCfg.getStringList("disabled_items").contains(id.toUpperCase());
    }
}
