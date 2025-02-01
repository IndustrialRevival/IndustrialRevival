package org.irmc.industrialrevival.implementation.items.register;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.items.ElementItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.utils.KeyUtil;

@UtilityClass
public class ElementOres {
    private static boolean LOADED = false;
    public static void register() {
        if (LOADED) {
            return;
        }
        LOADED = true;
        for (ElementType elementType : ElementType.values()) {
            if (!elementType.isGas()) {
                new ElementItem()
                        .setElementType(elementType)
                        .setAddon(IndustrialRevival.getInstance())
                        .addItemGroup(IRItemGroups.ORES)
                        .setItemStack(new IndustrialRevivalItemStack(KeyUtil.customKey(elementType.name().toUpperCase() + "_ORE"), Material.IRON_ORE))
                        .register();
            }
        }
    }

    public static IndustrialRevivalItem of(ElementType elementType) {
        if (!LOADED) {
            register();
        }
        return IndustrialRevivalItem.getById(KeyUtil.customKey(elementType.name().toUpperCase() + "_ORE"));
    }
}
