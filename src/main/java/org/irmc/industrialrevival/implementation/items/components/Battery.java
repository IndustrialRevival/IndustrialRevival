package org.irmc.industrialrevival.implementation.items.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.attributes.Rechargeable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.language.MessageReplacement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Battery extends IndustrialRevivalItem implements NotPlaceable, Rechargeable {
    private final Type type;
    private final Size size;

    @Override
    public Battery addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public Battery addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public Battery setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        super.setDisabledInWorld(world, disabled, saveToConfig);
        return this;
    }

    @Override
    public Battery setDisabled(boolean disabled, boolean saveToConfig) {
        super.setDisabled(disabled, saveToConfig);
        return this;
    }

    @Override
    public Battery addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public Battery setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }

    @Override
    public double getEnergyCapacity() {
        return 300 * (size.ordinal() + 1);
    }

    @Override
    public void onRecharge(ItemStack item, double energy) {
        double currentEnergy = getData(item);
        double newEnergy = currentEnergy + energy;

        if (newEnergy > getEnergyCapacity()) {
            newEnergy = getEnergyCapacity();
        }

        Component stored = IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "energy.stored",
                MessageReplacement.replace("%energy%", newEnergy + "mAh"),
                MessageReplacement.replace("%capacity%", getEnergyCapacity() + "mAh"));

        List<Component> lore = new ArrayList<>();
        lore.add(stored);

        item.lore(lore);
    }

    @Override
    public void onEnergyTaken(ItemStack item, double energy) {
        double currentEnergy = getData(item);
        double newEnergy = currentEnergy - energy;

        if (newEnergy > getEnergyCapacity()) {
            newEnergy = getEnergyCapacity();
        }

        Component stored = IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "energy.stored",
                MessageReplacement.replace("%energy%", newEnergy + "mAh"),
                MessageReplacement.replace("%capacity%", getEnergyCapacity() + "mAh"));

        List<Component> lore = new ArrayList<>();
        lore.add(stored);

        item.lore(lore);
    }

    public enum Type {
        STORAGE,
        LITHIUM,
        DRY,
        SOLAR,
        NiMH
    }

    public enum Size {
        N,
        AAAA,
        AAA,
        AA,
        SC,
        A,
        C,
        D,
        F
    }
}
