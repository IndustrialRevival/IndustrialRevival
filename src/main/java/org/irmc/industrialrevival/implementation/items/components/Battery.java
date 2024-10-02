package org.irmc.industrialrevival.implementation.items.components;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.attributes.Rechargeable;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.language.MessageReplacement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Battery extends IndustrialRevivalItem implements NotPlaceable, Rechargeable {
    private final Type type;
    private final Size size;

    public Battery(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull ItemStack[] recipe, Type type, Size size) {
        super(group, itemStack, RecipeType.VANILLA_CRAFTING, recipe);

        this.type = type;
        this.size = size;
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
