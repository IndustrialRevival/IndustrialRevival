package org.irmc.industrialrevival.api.items.attributes;

import com.google.common.base.Preconditions;
import java.util.function.Consumer;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.ItemAttribute;
import org.irmc.industrialrevival.api.menu.MachineMenu;

public interface InventoryBlock extends ItemAttribute {
    default void createMenu(IndustrialRevivalItem item, Consumer<MachineMenu> consumer) {
        MachineMenu menu = new MachineMenu(item.getId(), item.getItemName());
        consumer.accept(menu);
    }

    default void createMenu(IndustrialRevivalItem item, MachineMenu menu) {
        Preconditions.checkArgument(item.getId().equals(menu.getId()), "Item id and menu id must match");

        menu.register();
    }
}
