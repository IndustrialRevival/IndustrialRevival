package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.irmc.industrialrevival.core.utils.CleanedItemGetter;
import org.irmc.pigeonlib.dict.Dictionary;
import org.irmc.pigeonlib.items.ItemUtils;

@Getter
public class ItemStackReference {
    private final ReferenceType referenceType;
    private Dictionary dictionary;
    private ItemStack itemStack;

    public ItemStackReference(ItemStack itemStack) {
        this.referenceType = ReferenceType.ITEMSTACK;
        this.itemStack = CleanedItemGetter.getCleanedItem(itemStack);
    }

    public ItemStackReference(Dictionary dictionary) {
        this.referenceType = ReferenceType.DICTIONARY;
        this.dictionary = dictionary;
    }

    public boolean itemsMatch(ItemStack incomingItemStack) {
        if (this.referenceType == ReferenceType.ITEMSTACK) {
            return ItemUtils.isItemSimilar(this.itemStack, incomingItemStack);
        }

        if (this.referenceType == ReferenceType.DICTIONARY) {
            if (incomingItemStack == null || incomingItemStack.getType() == Material.AIR) {
                return false;
            }

            return dictionary.hasDictMeta(incomingItemStack);
        }

        return false;
    }

    public enum ReferenceType {
        ITEMSTACK,
        DICTIONARY
    }
}
