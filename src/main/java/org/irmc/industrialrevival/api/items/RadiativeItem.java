package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

@Builder
public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    @Getter
    private RadiationLevel radiationLevel = RadiationLevel.LOW;

    public RadiativeItem setRadiationLevel(RadiationLevel radiationLevel) {
        checkRegistered();
        this.radiationLevel = radiationLevel;
        return this;
    }
}
