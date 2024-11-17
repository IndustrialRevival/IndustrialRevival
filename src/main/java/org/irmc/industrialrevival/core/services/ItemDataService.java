package org.irmc.industrialrevival.core.services;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.DataUtil;

import java.util.Optional;

public class ItemDataService {
    public Optional<String> getIRId(ItemStack stack) {
        if (stack == null) {
            return Optional.empty();
        }

        if (!stack.hasItemMeta() || stack.getItemMeta() == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                stack.getItemMeta().getPersistentDataContainer().get(Constants.ItemStackKeys.ITEM_ID_KEY, PersistentDataType.STRING));
    }

    public Optional<RadiationLevel> getRadiationLevel(ItemStack stack) {
        if (stack == null) {
            return Optional.empty();
        }

        if (!stack.hasItemMeta() || stack.getItemMeta() == null) {
            return Optional.empty();
        }
        return Optional.of(RadiationLevel.valueOf(stack.getItemMeta()
                .getPersistentDataContainer()
                .get(Constants.ItemStackKeys.RADIATION_LEVEL_KEY, PersistentDataType.STRING)));
    }

    public void setRadiationLevel(ItemStack stack, RadiationLevel radiationLevel) {
        if (stack == null) {
            return;
        }

        if (!stack.hasItemMeta() || stack.getItemMeta() == null) {
            return;
        }
        ItemMeta meta = stack.getItemMeta();
        DataUtil.setPDC(meta, Constants.ItemStackKeys.RADIATION_LEVEL_KEY, radiationLevel.name());
        stack.setItemMeta(meta);
    }
}
