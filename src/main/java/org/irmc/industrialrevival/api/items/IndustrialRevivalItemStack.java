package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class IndustrialRevivalItemStack extends ItemStack {
    private final String id;
    private boolean locked;

    public IndustrialRevivalItemStack(String id, ItemStack itemStack) {
        super(itemStack);

        Preconditions.checkArgument(id.equals(id.toUpperCase()), "ID must be uppercase");

        this.id = id;
    }

    public IndustrialRevivalItemStack(String id, Material material, String name, String... lore) {
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

    public String getId() {
        return id;
    }

    @Override
    public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        return super.setItemMeta(itemMeta);
    }

    @Override
    public void setAmount(int amount) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        super.setAmount(amount);
    }

    @Override
    public void setType(@NotNull Material type) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        super.setType(type);
    }

    @Deprecated
    @Override
    public void setData(@Nullable MaterialData data) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        super.setData(data);
    }

    void lock() {
        this.locked = true;
    }
}
