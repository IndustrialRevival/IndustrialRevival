package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import java.util.function.Consumer;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class IndustrialRevivalItemStack extends ItemStack {
    @Getter
    private final String id;

    private boolean locked;

    public IndustrialRevivalItemStack(@NotNull String id, @NotNull Material material) {
        this(
                id,
                new CustomItemStack(
                        material,
                        IndustrialRevival.getInstance().getLanguageManager().getItemName(id),
                        IndustrialRevival.getInstance().getLanguageManager().getItemLore(id)));
    }

    public IndustrialRevivalItemStack(@NotNull String id, @NotNull ItemStack itemStack) {
        super(itemStack.getType(), itemStack.getAmount());
        //clone
        setItemMeta(itemStack.getItemMeta());
        addEnchantments(itemStack.getEnchantments());
        setDurability(itemStack.getDurability());
        //clone end

        Preconditions.checkArgument(id.equals(id.toUpperCase()), "ID must be uppercase");

        this.id = id;

        editMeta(meta -> PersistentDataAPI.setString(meta, Constants.ITEM_ID_KEY, id));
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
    @SuppressWarnings("deprecation")
    public void setData(@Nullable MaterialData data) {
        if (locked) {
            throw new IllegalStateException("Item is not mutable");
        }

        super.setData(data);
    }

    @Override
    public @NotNull ItemStack clone() {
        var itemStack = new IndustrialRevivalItemStack(this.id, this);
        itemStack.unlock();
        return itemStack;
    }

    void lock() {
        this.locked = true;
    }

    void unlock() {
        this.locked = false;
    }
}
