package org.irmc.industrialrevival.implementation.items.register;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.items.ElementOre;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.items.CustomItemStack;

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
                new ElementOre()
                        .elementType(elementType)
                        .itemGroup(IRItemGroups.ORES)
                        .id(elementType.name().toLowerCase() + "_ore")
                        .icon(new CustomItemStack(Material.IRON_ORE, Component.translatable("item.industrial_revival.element_ore.name", "Unnamed Element Ore").color(TextColor.color(167778))).getBukkit())
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
