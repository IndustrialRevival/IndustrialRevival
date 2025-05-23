package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.ArmorProtectionType;
import org.irmc.industrialrevival.api.objects.enums.ArmorType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.CraftMethod;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArmorSet {
    @Getter
    private final NamespacedKey key;
    @Getter
    private final ItemGroup group;
    private final Map<ArmorType, ArmorPiece> armor;
    private boolean lock = false;
    @Getter
    private boolean protectWhenFullSet;

    @Getter
    private List<ArmorProtectionType> protectionTypes;

    public ArmorSet(@NotNull NamespacedKey key, @NotNull ItemGroup group) {
        this.key = key;
        this.armor = new HashMap<>(4);
        this.protectWhenFullSet = false;
        this.group = group;
    }

    public void addArmor(
            @NotNull ArmorType armorType,
            @NotNull ItemStack itemStack,
            @NotNull Set<PotionEffect> potionEffects,
            @NotNull ItemStack[] recipe,
            @NotNull RecipeType type) {
        checkLock();
        armor.put(
                armorType,
                new ArmorPiece()
                        .addItemGroup(group)
                        .setIcon(itemStack)
                        .recipe(item -> CraftMethod.of(type, recipe, item))
                        .cast(ArmorPiece.class)
                        .setPotionEffects(potionEffects)
        );
    }

    public void addArmor(
            @NotNull ArmorType armorType,
            @NotNull ArmorPiece armorPiece) {
        checkLock();
        armor.put(armorType, armorPiece);
    }

    public void setProtectWhenFullSet(boolean protectWhenFullSet) {
        checkLock();
        this.protectWhenFullSet = protectWhenFullSet;
    }

    public void setArmorProtectionTypes(@NotNull ArmorProtectionType... types) {
        checkLock();
        protectionTypes = List.of(types);
    }

    public void setArmorProtectionTypes(@NotNull List<ArmorProtectionType> types) {
        checkLock();
        protectionTypes = types;
    }

    public ArmorPiece getArmorPiece(@NotNull ArmorType armorType) {
        return armor.get(armorType);
    }

    public void register(@NotNull IndustrialRevivalAddon addon) {
        for (ArmorPiece p : armor.values()) {
            p.setAddon(addon);
            p.register();
        }

        lock = true;
    }

    private NamespacedKey getNamespacedKey(@NotNull ArmorType armorType) {
        return new NamespacedKey(key.getNamespace(), key.getKey() + "_" + armorType.toString().toLowerCase());
    }

    private void checkLock() {
        if (lock) {
            throw new IllegalStateException("Cannot modify ArmorSet after registration");
        }
    }

}
