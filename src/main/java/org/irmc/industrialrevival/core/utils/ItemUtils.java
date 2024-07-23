package org.irmc.industrialrevival.core.utils;

import java.util.List;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.utils.PersistentDataAPI;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class ItemUtils {

    private ItemUtils() {}

    @Contract("null -> null")
    public static ItemStack getCleanedItem(@Nullable ItemStack item) {
        if (item == null) {
            return null;
        }

        ItemStack baseItem = new ItemStack(item.getType(), item.getAmount());
        ItemMeta meta = baseItem.getItemMeta();

        ItemMeta originalMeta = item.getItemMeta();

        meta.setCustomModelData(originalMeta.getCustomModelData());

        if (originalMeta.hasDisplayName()) {
            meta.displayName(originalMeta.displayName());
        }
        if (originalMeta.hasLore()) {
            meta.lore(originalMeta.lore());
        }

        originalMeta.getItemFlags().forEach(meta::addItemFlags);

        meta.setUnbreakable(originalMeta.isUnbreakable());
        item.getEnchantments().forEach(baseItem::addEnchantment);

        if (item instanceof IndustrialRevivalItemStack iris) {
            // Tag the item to verify that is an IR item
            // Useful in recipe click?
            PersistentDataAPI.setString(
                    meta, new NamespacedKey(IndustrialRevival.getInstance(), "cleaned_ir_item_id"), iris.getId());
        }

        baseItem.setItemMeta(meta);

        return item;
    }

    public static boolean isItemSimilar(ItemStack item1, ItemStack item2, boolean checkLore, boolean checkAmount) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }

        if (item1.getType() != item2.getType()) {
            return false;
        }

        if (checkAmount && item1.getAmount() < item2.getAmount()) {
            return false;
        }

        if (item1.hasItemMeta() != item2.hasItemMeta()) {
            return false;
        }

        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();

        if (meta1.hasDisplayName()
                && meta2.hasDisplayName()
                && !Objects.equals(meta1.displayName(), meta2.displayName())) {
            return false;
        }

        if (meta1.hasCustomModelData()
                && meta2.hasCustomModelData()
                && meta1.getCustomModelData() != meta2.getCustomModelData()) {
            return false;
        }

        if (!item1.getEnchantments().isEmpty()
                && !item2.getEnchantments().isEmpty()
                && !item1.getEnchantments().equals(item2.getEnchantments())) {
            return false;
        }

        if (metaNotEquals(meta1, meta2)) {
            return false;
        }

        PersistentDataContainer pdc1 = meta1.getPersistentDataContainer();
        PersistentDataContainer pdc2 = meta2.getPersistentDataContainer();

        if (pdc1.getKeys().size() != pdc2.getKeys().size()) {
            return false;
        }

        if (!pdc1.equals(pdc2)) {
            return false;
        }

        if (checkLore) {
            List<Component> lore1 = meta1.lore();
            List<Component> lore2 = meta2.lore();

            if (lore1 == null || lore2 == null) {
                return lore1 == lore2;
            }

            if (lore1.size() != lore2.size()) {
                return false;
            }

            for (int i = 0; i < lore1.size(); i++) {
                if (!Objects.equals(lore1.get(i), lore2.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    private static boolean metaNotEquals(ItemMeta meta1, ItemMeta meta2) {
        if (meta1 == null || meta2 == null) {
            return meta1 != meta2;
        }

        if (meta1 instanceof Damageable instanceOne && meta2 instanceof Damageable instanceTwo) {
            if (instanceOne.getDamage() != instanceTwo.getDamage()) {
                return true;
            }
        }

        // Axolotl
        if (meta1 instanceof AxolotlBucketMeta instanceOne && meta2 instanceof AxolotlBucketMeta instanceTwo) {
            if (instanceOne.hasVariant() != instanceTwo.hasVariant()) {
                return true;
            }

            if (!instanceOne.hasVariant() || !instanceTwo.hasVariant()) return true;

            if (instanceOne.getVariant() != instanceTwo.getVariant()) {
                return true;
            }
        }

        // Banner
        if (meta1 instanceof BannerMeta instanceOne && meta2 instanceof BannerMeta instanceTwo) {
            if (!instanceOne.getPatterns().equals(instanceTwo.getPatterns())) {
                return true;
            }
        }

        // Books
        if (meta1 instanceof BookMeta instanceOne && meta2 instanceof BookMeta instanceTwo) {
            if (instanceOne.getPageCount() != instanceTwo.getPageCount()) {
                return true;
            }
            if (!Objects.equals(instanceOne.getAuthor(), instanceTwo.getAuthor())) {
                return true;
            }
            if (!Objects.equals(instanceOne.getTitle(), instanceTwo.getTitle())) {
                return true;
            }
            if (!Objects.equals(instanceOne.getGeneration(), instanceTwo.getGeneration())) {
                return true;
            }
        }

        // Compass
        if (meta1 instanceof CompassMeta instanceOne && meta2 instanceof CompassMeta instanceTwo) {
            if (instanceOne.isLodestoneTracked() != instanceTwo.isLodestoneTracked()) {
                return true;
            }
            if (!Objects.equals(instanceOne.getLodestone(), instanceTwo.getLodestone())) {
                return true;
            }
        }

        // Crossbow
        if (meta1 instanceof CrossbowMeta instanceOne && meta2 instanceof CrossbowMeta instanceTwo) {
            if (instanceOne.hasChargedProjectiles() != instanceTwo.hasChargedProjectiles()) {
                return true;
            }
            if (!instanceOne.getChargedProjectiles().equals(instanceTwo.getChargedProjectiles())) {
                return true;
            }
        }

        // Enchantment Storage
        if (meta1 instanceof EnchantmentStorageMeta instanceOne
                && meta2 instanceof EnchantmentStorageMeta instanceTwo) {
            if (instanceOne.hasStoredEnchants() != instanceTwo.hasStoredEnchants()) {
                return true;
            }
            if (!instanceOne.getStoredEnchants().equals(instanceTwo.getStoredEnchants())) {
                return true;
            }
        }

        // Firework Star
        if (meta1 instanceof FireworkEffectMeta instanceOne && meta2 instanceof FireworkEffectMeta instanceTwo) {
            if (!Objects.equals(instanceOne.getEffect(), instanceTwo.getEffect())) {
                return true;
            }
        }

        // Firework
        if (meta1 instanceof FireworkMeta instanceOne && meta2 instanceof FireworkMeta instanceTwo) {
            if (instanceOne.getPower() != instanceTwo.getPower()) {
                return true;
            }
            if (!instanceOne.getEffects().equals(instanceTwo.getEffects())) {
                return true;
            }
        }

        // Leather Armor
        if (meta1 instanceof LeatherArmorMeta instanceOne && meta2 instanceof LeatherArmorMeta instanceTwo) {
            if (!instanceOne.getColor().equals(instanceTwo.getColor())) {
                return true;
            }
        }

        // Maps
        if (meta1 instanceof MapMeta instanceOne && meta2 instanceof MapMeta instanceTwo) {
            if (instanceOne.hasMapView() != instanceTwo.hasMapView()) {
                return true;
            }
            if (instanceOne.hasLocationName() != instanceTwo.hasLocationName()) {
                return true;
            }
            if (instanceOne.hasColor() != instanceTwo.hasColor()) {
                return true;
            }
            if (!Objects.equals(instanceOne.getMapView(), instanceTwo.getMapView())) {
                return true;
            }
            if (!Objects.equals(instanceOne.getLocationName(), instanceTwo.getLocationName())) {
                return true;
            }
            if (!Objects.equals(instanceOne.getColor(), instanceTwo.getColor())) {
                return true;
            }
        }

        // Potion
        if (meta1 instanceof PotionMeta instanceOne && meta2 instanceof PotionMeta instanceTwo) {
            if (!instanceOne.getBasePotionData().equals(instanceTwo.getBasePotionData())) {
                return true;
            }
            if (instanceOne.hasCustomEffects() != instanceTwo.hasCustomEffects()) {
                return true;
            }
            if (instanceOne.hasColor() != instanceTwo.hasColor()) {
                return true;
            }
            if (!Objects.equals(instanceOne.getColor(), instanceTwo.getColor())) {
                return true;
            }
            if (!instanceOne.getCustomEffects().equals(instanceTwo.getCustomEffects())) {
                return true;
            }
        }

        // Skull
        if (meta1 instanceof SkullMeta instanceOne && meta2 instanceof SkullMeta instanceTwo) {
            if (instanceOne.hasOwner() != instanceTwo.hasOwner()) {
                return true;
            }
            if (!Objects.equals(instanceOne.getOwningPlayer(), instanceTwo.getOwningPlayer())) {
                return true;
            }
        }

        // Stew
        if (meta1 instanceof SuspiciousStewMeta instanceOne && meta2 instanceof SuspiciousStewMeta instanceTwo) {
            if (!Objects.equals(instanceOne.getCustomEffects(), instanceTwo.getCustomEffects())) {
                return true;
            }
        }

        // Fish Bucket
        if (meta1 instanceof TropicalFishBucketMeta instanceOne
                && meta2 instanceof TropicalFishBucketMeta instanceTwo) {
            if (instanceOne.hasVariant() != instanceTwo.hasVariant()) {
                return true;
            }
            if (!instanceOne.getPattern().equals(instanceTwo.getPattern())) {
                return true;
            }
            if (!instanceOne.getBodyColor().equals(instanceTwo.getBodyColor())) {
                return true;
            }
            return !instanceOne.getPatternColor().equals(instanceTwo.getPatternColor());
        }

        // Bundle
        if (meta1 instanceof BundleMeta instanceOne && meta2 instanceof BundleMeta instanceTwo) {
            if (instanceOne.hasItems() != instanceTwo.hasItems()) {
                return true;
            }
            return !instanceOne.getItems().equals(instanceTwo.getItems());
        }

        return false;
    }
}
