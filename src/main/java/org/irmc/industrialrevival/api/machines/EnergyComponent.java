package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

public abstract class EnergyComponent extends BasicMachine implements EnergyNetComponent {
    @Getter
    private long capacity = 0;

    public EnergyComponent setCapacity(long capacity) {
        checkRegistered();
        this.capacity = capacity;
        return this;
    }
}
