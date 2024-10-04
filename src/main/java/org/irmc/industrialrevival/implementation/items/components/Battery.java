package org.irmc.industrialrevival.implementation.items.components;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.attributes.Rechargeable;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.language.MessageReplacement;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Battery extends IndustrialRevivalItem implements NotPlaceable, Rechargeable {
    private Type type;
    private Size size;

    @Override
    public Battery setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
        return this;
    }

    @Override
    public Battery setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public Battery addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public Battery setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public Battery setDisabledInWorld(@Nonnull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public Battery setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public Battery addItemDictionary(@Nonnull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    public Battery setType(Type type) {
        this.type = type;
        return this;
    }

    public Battery setSize(Size size) {
        this.size = size;
        return this;
    }

    @Override
    public double getEnergyCapacity() {
        return 300 * size.ordinal();
    }

    @Override
    public void onRecharge(ItemStack item, double energy) {
        double currentEnergy = getItemEnergy(item);
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
        double currentEnergy = getItemEnergy(item);
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
