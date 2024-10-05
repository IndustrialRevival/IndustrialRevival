package org.irmc.industrialrevival.api.items.attributes;

import com.google.common.base.Preconditions;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;

import java.util.function.Consumer;

/**
 * This interface defines a machine that has an inventory. <br>
 * <br>
 * <b>Note: </b> Right-click to open the inventory.
 */
public interface InventoryBlock extends ItemAttribute {
    int[] getInputSlots();

    int[] getOutputSlots();

    default void createMenu(IndustrialRevivalItem item, Consumer<MachineMenuPreset> consumer) {
        MachineMenuPreset menu = new MachineMenuPreset(item.getId(), item.getItemName());
        consumer.accept(menu);

        menu.register();
    }

    default void createMenu(IndustrialRevivalItem item, MachineMenuPreset menu) {
        Preconditions.checkArgument(item.getId().equals(menu.getId()), "Item id and menu id must match");

        menu.register();
    }
}
