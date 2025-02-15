package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.MeltedType;
import org.irmc.industrialrevival.api.elements.TinkerType;
import org.irmc.industrialrevival.api.items.attributes.TinkerModel;
import org.irmc.industrialrevival.api.items.attributes.TinkerProduct;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

@Getter
public class TinkerProductItem extends IndustrialRevivalItem implements TinkerProduct {
    private MeltedType meltedType;
    private TinkerType tinkerType;
    public TinkerProductItem() {
        super();
    }

    public TinkerProductItem setTinkerType(TinkerType tinkerType) {
        this.tinkerType = tinkerType;
        return this;
    }

    public TinkerProductItem setMeltedType(MeltedType meltedType) {
        this.meltedType = meltedType;
        return this;
    }

    @Override
    public void postRegister() {
        super.postRegister();
        if (this.tinkerType == null) {
            throw new IllegalArgumentException("TinkerType must be set for TinkerProductItem");
        }

        if (this.meltedType == null) {
            throw new IllegalArgumentException("MeltedType must be set for TinkerProductItem");
        }
        IndustrialRevival.getInstance().getRegistry().registerTinkerItem(getMeltedType(), this);
    }

    @Override
    public ItemStack getProduct() {
        return getItemStack();
    }

    @Override
    public IndustrialRevivalItem getIRItem() {
        return this;
    }
}
