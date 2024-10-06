package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    @Getter
    private RadiationLevel radiationLevel = RadiationLevel.LOW;

    public RadiativeItem setRadiationLevel(RadiationLevel radiationLevel) {
        checkRegistered();
        this.radiationLevel = radiationLevel;
        return this;
    }

    @Override
    public RadiativeItem setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
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
    public RadiativeItem setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public RadiativeItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public RadiativeItem addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Nullable
    public RadiativeItem setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Nullable
    public RadiativeItem setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }
}
