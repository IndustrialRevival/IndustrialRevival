package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@SuppressWarnings("deprecation")
public class IndustrialRevivalItemStack {
    private final ItemStack itemStack;
    @Getter
    private final String id;

    private boolean locked;

    public IndustrialRevivalItemStack(@NotNull String id, @NotNull ItemStack itemStack) {
        this.itemStack = new ItemStack(itemStack.getType(), itemStack.getAmount());
        // clone
        this.itemStack.setItemMeta(itemStack.getItemMeta());
        this.itemStack.addEnchantments(itemStack.getEnchantments());
        this.itemStack.setDurability(itemStack.getDurability());
        // clone end

        Preconditions.checkArgument(id.equals(id.toUpperCase()), "ID must be uppercase");

        this.id = id;

        itemStack.editMeta(meta -> PersistentDataAPI.setString(meta, Constants.ItemStackKeys.ITEM_ID_KEY, id));
    }

    public IndustrialRevivalItemStack(@NotNull String id, @NotNull Material material) {
        this(
                id,
                new CustomItemStack(
                        material,
                        IndustrialRevival.getInstance().getLanguageManager().getItemName(id),
                        IndustrialRevival.getInstance().getLanguageManager().getItemLore(id)));
    }

    public IndustrialRevivalItemStack(@NotNull String id, @NotNull Material material, String name, String... lore) {
        this(id, new CustomItemStack(material, name, lore));
    }

    public IndustrialRevivalItemStack(String id, ItemStack itemStack, String name, String... lore) {
        this(id, new CustomItemStack(itemStack, name, lore));
    }

    public IndustrialRevivalItemStack(String id, Material material, Consumer<ItemMeta> consumer) {
        this(id, new CustomItemStack(material, consumer));
    }

    public IndustrialRevivalItemStack(String id, ItemStack itemStack, Consumer<ItemMeta> consumer) {
        this(id, new CustomItemStack(itemStack, consumer));
    }

    public IndustrialRevivalItemStack(String id, IndustrialRevivalItemStack industrialRevivalItemStack) {
        this(id, industrialRevivalItemStack.getItemStack().clone());
    }

    public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        return itemStack.setItemMeta(itemMeta);
    }

    public void setAmount(int amount) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        itemStack.setAmount(amount);
    }

    public void setType(@NotNull Material type) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        itemStack.setType(type);
    }

    public @NotNull IndustrialRevivalItemStack cloneIR() {
        IndustrialRevivalItemStack itemStack = new IndustrialRevivalItemStack(this.id, this);
        itemStack.unlock();
        return itemStack;
    }

    @Override
    public @NotNull ItemStack clone() {
        return itemStack.clone();
    }

    public @NotNull ItemStack deepClone() {
        ItemStack itemStack = new ItemStack(this.itemStack.getType(), this.itemStack.getAmount());
        itemStack.setItemMeta(this.itemStack.getItemMeta());
        return itemStack;
    }

    @Nullable
    public IndustrialRevivalItem getItem() {
        return IndustrialRevivalItem.getByItem(this);
    }

    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }

    void lock() {
        this.locked = true;
    }

    void unlock() {
        this.locked = false;
    }
}
