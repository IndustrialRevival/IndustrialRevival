package org.irmc.industrialrevival.api.items;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IndustrialRevivalItemStack {
    private final ItemStack itemStack;
    @Getter
    private final NamespacedKey id;

    public IndustrialRevivalItemStack(@NotNull NamespacedKey id, @NotNull ItemStack itemStack) {
        Preconditions.checkArgument(!id.getKey().startsWith("_"), "ID's namespace mustn't start with '_'");
        Preconditions.checkArgument(!id.getKey().contains("-"), "ID's key mustn't contain '-'");

        this.itemStack = itemStack;
        this.id = id;

        //TODO: Add namespaced key persistent data type
        itemStack.editMeta(meta -> PersistentDataAPI.setString(meta, Constants.ItemStackKeys.ITEM_ID_KEY, id.toString()));
    }

    public IndustrialRevivalItemStack(@NotNull NamespacedKey id, @NotNull CustomItemStack customItemStack) {
        this(id, customItemStack.getBukkit());
    }

    public IndustrialRevivalItemStack(@NotNull NamespacedKey id, @NotNull Material material) {
        this(id, ItemStack.of(material));
    }

    public ItemStack cloneItemStack() {
        return itemStack.clone();
    }

    @Nullable
    public IndustrialRevivalItem getIRItem() {
        return IndustrialRevivalItem.getByItem(this);
    }

    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }
}
