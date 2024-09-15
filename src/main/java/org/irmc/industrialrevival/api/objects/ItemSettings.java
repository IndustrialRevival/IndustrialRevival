package org.irmc.industrialrevival.api.objects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.List;

public class ItemSettings {
    private final YamlConfiguration itemCfg;

    public ItemSettings(YamlConfiguration itemCfg) {
        this.itemCfg = itemCfg;
    }

    public ConfigurationSection getSetting(String id) {
        checkValidItemId(id);
        return itemCfg.createSection("specific_items." + id);
    }

    public void disableItem(String id) {
        checkValidItemId(id);
        IndustrialRevival.getInstance().getRegistry().getItems().get(id).setDisabled(true);
        List<String> disabledItems = itemCfg.getStringList("disabled_items");
        disabledItems.add(id);
        itemCfg.set("disabled_items", disabledItems);
    }

    public boolean isItemDisabled(String id) {
        checkValidItemId(id);
        return itemCfg.getStringList("disabled_items").contains(id);
    }

    private void checkValidItemId(String id) {
        IRRegistry registry = IndustrialRevival.getInstance().getRegistry();
        if (!registry.getItems().containsKey(id)) {
            throw new IllegalArgumentException("Invalid item id: " + id);
        }
    }
}
