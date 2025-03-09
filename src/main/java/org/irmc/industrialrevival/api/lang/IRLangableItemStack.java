package org.irmc.industrialrevival.api.lang;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;

public class IRLangableItemStack extends LangableItemStack {
    private static final IndustrialRevivalAddon addon = IndustrialRevival.getInstance();

    public IRLangableItemStack(@NotNull String key, @NotNull Material material) {
        super(addon, key, material);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount) {
        super(addon, key, material, amount);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(addon, key, material, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @NotNull Component displayName, @NotNull Component @NotNull ... lore) {
        super(addon, key, material, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @NotNull Component displayName, @NotNull List<Component> lore) {
        super(addon, key, material, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, @NotNull String displayName, @NotNull String @NotNull ... lore) {
        super(addon, key, material, amount, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull Material material, @Range(from = 1, to = Integer.MAX_VALUE) int amount, Component displayName, Component... lore) {
        super(addon, key, material, amount, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull ItemStack itemStack) {
        super(addon, key, itemStack);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount) {
        super(addon, key, itemStack, amount);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, String displayName, String... lore) {
        super(addon, key, itemStack, amount, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull ItemStack itemStack, String displayName, String... lore) {
        super(addon, key, itemStack, displayName, lore);
    }

    public IRLangableItemStack(@NotNull String key, @NotNull ItemStack itemStack, @Range(from = 1, to = Integer.MAX_VALUE) int amount, Component displayName, Component... lore) {
        super(addon, key, itemStack, amount, displayName, lore);
    }
}
