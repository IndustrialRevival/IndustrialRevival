package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.ArmorProtectionType;
import org.irmc.industrialrevival.api.objects.enums.ArmorType;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
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
                        .setPotionEffects(potionEffects)
                        .addItemGroup(group)
                        .setItemStack(new IndustrialRevivalItemStack(namespacedKeyToId(armorType), itemStack))
                        .addCraftMethod(item -> new CraftMethod(type, recipe, item))
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

    private String namespacedKeyToId(@NotNull ArmorType armorType) {
        return key.getNamespace().toUpperCase() + "_" + key.getKey().toUpperCase() + "_" + armorType.toString();
    }

    private void checkLock() {
        if (lock) {
            throw new IllegalStateException("Cannot modify ArmorSet after registration");
        }
    }

    @Getter
    public static class ArmorPiece extends IndustrialRevivalItem {
        private final Set<PotionEffect> potionEffects = new HashSet<>();
        private ArmorSet parent;

        public ArmorPiece addPotionEffect(@NotNull PotionEffect effect) {
            checkRegistered();
            potionEffects.add(effect);
            return this;
        }

        public ArmorPiece setPotionEffects(@NotNull Set<PotionEffect> effects) {
            checkRegistered();
            potionEffects.clear();
            potionEffects.addAll(effects);
            return this;
        }

        public ArmorPiece setParent(@NotNull ArmorSet parent) {
            checkRegistered();
            this.parent = parent;
            return this;
        }

        @Override
        public ArmorPiece setAddon(@NotNull IndustrialRevivalAddon addon) {
            super.setAddon(addon);
            return this;
        }
        @Override
        public ArmorPiece addItemGroup(@NotNull ItemGroup group) {
            super.addItemGroup(group);
            return this;
        }

        @Override
        public ArmorPiece setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
            super.setItemStack(itemStack);
            return this;
        }

        @Override
        public ArmorPiece addCraftMethod(@NotNull CraftMethodHandler handler) {
            super.addCraftMethod(handler);
            return this;
        }

        @Override
        public ArmorPiece setWikiText(@NotNull String wikiText) {
            super.setWikiText(wikiText);
            return this;
        }

        @Override
        public ArmorPiece setDisabledInWorld(@NotNull World world, boolean disabled) {
            super.setDisabledInWorld(world, disabled);
            return this;
        }

        @Override
        public ArmorPiece setDisabled(boolean disabled) {
            super.setDisabled(disabled);
            return this;
        }

        @Override
        public ArmorPiece addItemDictionary(@NotNull ItemDictionary dictionary) {
            super.addItemDictionary(dictionary);
            return this;
        }

        @Override
        public ArmorPiece setEnchantable(boolean enchantable) {
            super.setEnchantable(enchantable);
            return this;
        }

        @Override
        public ArmorPiece setDisenchantable(boolean disenchantable) {
            super.setDisenchantable(disenchantable);
            return this;
        }

        @Override
        public ArmorPiece setHideInGuide(boolean hideInGuide) {
            super.setHideInGuide(hideInGuide);
            return this;
        }
    }
}
