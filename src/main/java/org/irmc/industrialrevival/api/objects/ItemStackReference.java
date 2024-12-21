package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.pigeonlib.items.ItemUtils;

@Getter
public class ItemStackReference {
    private final ReferenceType referenceType;
    private ItemDictionary dictionary;
    private ItemStack itemStack;

    public ItemStackReference(ItemStack itemStack) {
        this.referenceType = ReferenceType.ITEMSTACK;
        this.itemStack = new ItemStack(itemStack.getType(), itemStack.getAmount());
        this.itemStack.setItemMeta(itemStack.getItemMeta());
    }

    public ItemStackReference(ItemDictionary dictionary) {
        this.referenceType = ReferenceType.DICTIONARY;
        this.dictionary = dictionary;
    }

    public ItemStackReference of(ItemStack itemStack) {
        return new ItemStackReference(itemStack);
    }

    public ItemStackReference of(ItemDictionary dictionary) {
        return new ItemStackReference(dictionary);
    }

    public boolean itemsMatch(ItemStack incomingItemStack) {
        if (this.referenceType == ReferenceType.ITEMSTACK) {
            return ItemUtils.isItemSimilar(this.itemStack, incomingItemStack);
        }

        if (this.referenceType == ReferenceType.DICTIONARY) {
            if (incomingItemStack == null || incomingItemStack.getType() == Material.AIR) {
                return false;
            }

            return dictionary.isInDictionary(incomingItemStack);
        }

        return false;
    }

    public enum ReferenceType {
        ITEMSTACK,
        DICTIONARY
    }
}
