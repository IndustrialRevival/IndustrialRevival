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
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorSet {
    private boolean lock = false;

    @Getter
    private final NamespacedKey key;
    @Getter
    private final ItemGroup group;

    private final Map<ArmorType, ArmorPiece> armor;

    @Getter
    private boolean protectWhenFullSet;

    @Getter
    private List<ArmorProtectionType> protectionTypes;

    public ArmorSet(NamespacedKey key, ItemGroup group) {
        this.key = key;
        this.armor = new HashMap<>(4);
        this.protectWhenFullSet = false;
        this.group = group;
    }

    public void addArmor(ArmorType armorType, ItemStack itemStack, PotionEffect[] potionEffects, ItemStack[] recipe, RecipeType type) {
        checkLock();
        armor.put(armorType, new ArmorPiece(group, new IndustrialRevivalItemStack(namespacedKeyToId(armorType), itemStack), type, recipe, potionEffects, this));
    }

    public void setProtectWhenFullSet(boolean protectWhenFullSet) {
        checkLock();
        this.protectWhenFullSet = protectWhenFullSet;
    }

    public void setArmorProtectionType(ArmorProtectionType... types) {
        checkLock();
        protectionTypes = List.of(types);
    }

    public void setArmorProtectionType(List<ArmorProtectionType> types) {
        checkLock();
        protectionTypes = types;
    }

    public ArmorPiece getArmorPiece(ArmorType armorType) {
        return armor.get(armorType);
    }

    public void register(IndustrialRevivalAddon addon) {
        for (ArmorPiece p : armor.values()) {
            p.register(addon);
        }

        lock = true;
    }

    private String namespacedKeyToId(ArmorType armorType) {
        return key.getNamespace().toUpperCase() + ":" + key.getKey().toUpperCase() + "_" + armorType.toString();
    }

    private void checkLock() {
        if (lock) {
            throw new IllegalStateException("Cannot modify ArmorSet after registration");
        }
    }

    @Getter
    public static class ArmorPiece extends IndustrialRevivalItem {
        private final PotionEffect[] potionEffects;
        private final ArmorSet parent;

        ArmorPiece(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull PotionEffect[] potionEffects, @NotNull ArmorSet parent) {
            super(group, itemStack, recipeType, recipe);

            this.parent = parent;
            this.potionEffects = potionEffects;
        }
    }
}
