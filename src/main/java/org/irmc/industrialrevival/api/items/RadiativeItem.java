package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

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
    public RadiativeItem setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public RadiativeItem setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public RadiativeItem addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }
}
