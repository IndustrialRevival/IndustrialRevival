package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.jetbrains.annotations.NotNull;

public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    @Getter
    private RadiationLevel radiationLevel = RadiationLevel.LOW;

    public RadiativeItem setRadiationLevel(RadiationLevel radiationLevel) {
        checkRegistered();
        this.radiationLevel = radiationLevel;
        return this;
    }

    @Override
    public RadiativeItem setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }
    @Override
    public RadiativeItem addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public RadiativeItem setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public RadiativeItem addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public RadiativeItem setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public RadiativeItem setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        super.setDisabledInWorld(world, disabled, saveToConfig);
        return this;
    }

    @Override
    public RadiativeItem setDisabled(boolean disabled, boolean saveToConfig) {
        super.setDisabled(disabled, saveToConfig);
        return this;
    }

    @Override
    public RadiativeItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public RadiativeItem setEnchantable(boolean enchantable, boolean saveToConfig) {
        super.setEnchantable(enchantable, saveToConfig);
        return this;
    }

    @Override
    public RadiativeItem setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        super.setDisenchantable(disenchantable, saveToConfig);
        return this;
    }

    @Override
    public RadiativeItem setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        super.setHideInGuide(hideInGuide, saveToConfig);
        return this;
    }
}
