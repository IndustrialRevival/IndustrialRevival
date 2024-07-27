package org.irmc.industrialrevival.api.items.enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.core.utils.Keys;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Final_ROOT
 * @since 1.0
 */
public class NullEnchantment extends Enchantment {
    public static final Enchantment ENCHANTMENT = new NullEnchantment(Keys.NULL_ENCHANTMENT);

    private NullEnchantment(NamespacedKey key) {
        super(key);
    }

    @Nonnull
    @Override
    public String getName() {
        return "INDUSTRIAL_REVIVAL_NULL_ENCHANTMENT";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Nonnull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@Nonnull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@Nonnull ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nonnull Component displayName(int i) {
        return Component.text("");
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @Nonnull EnchantmentRarity getRarity() {
        return EnchantmentRarity.VERY_RARE;
    }

    @Override
    public float getDamageIncrease(int i, @Nonnull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @Nonnull Set<EquipmentSlot> getActiveSlots() {
        return new HashSet<>();
    }

    @Override
    public @Nonnull String translationKey() {
        return "";
    }

    public static void add(@Nonnull ItemStack item) {
        item.addUnsafeEnchantment(ENCHANTMENT, 0);
    }

    public static void addAndHidden(@Nonnull ItemStack item) {
        item.addUnsafeEnchantment(ENCHANTMENT, 0);
        if (!item.hasItemMeta()) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }
}
