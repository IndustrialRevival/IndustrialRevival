package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.IRInventoryHolder;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricCapacitor is an item that can be used to store energy.
 */
public class ElectricCapacitor extends IndustrialRevivalItem implements IRInventoryHolder, EnergyNetComponent {
    private @Getter final MachineMenu menu;
    private @Getter final long capacity;
    public ElectricCapacitor(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, MachineMenu menu, long capacity) {
        super(group, itemStack, recipeType, recipe);
        this.menu = menu;
        this.capacity = capacity;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu.getInventory();
    }
}
