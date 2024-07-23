package org.irmc.industrialrevival.api.items.attributes;

import com.google.common.base.Preconditions;
import java.util.function.Consumer;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.ItemAttribute;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;

public interface InventoryBlock extends ItemAttribute {
    default void createMenu(IndustrialRevivalItem item, Consumer<MachineMenuPreset> consumer) {
        MachineMenuPreset menu = new MachineMenuPreset(item.getId(), item.getItemName());
        consumer.accept(menu);
    }

    default void createMenu(IndustrialRevivalItem item, MachineMenuPreset menu) {
        Preconditions.checkArgument(item.getId().equals(menu.getId()), "Item id and menu id must match");

        menu.register();
    }
}
