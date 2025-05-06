package org.irmc.industrialrevival.implementation.items.register;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.chemistry.OreDust;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.items.CustomItemStack;

@UtilityClass
public class ElementOreDusts {
    private static boolean LOADED = false;
    public static void setup() {
        if (LOADED) {
            return;
        }
        LOADED = true;
        for (ElementType elementType : ElementType.values()) {
            Component name = Component.translatable("item.industrial_revival." + elementType.name().toLowerCase() + ".name", "Unnamed Element Ore Dust").color(TextColor.color(167778));
            if (!elementType.isGas()) {
                new OreDust()
                        .elementType(elementType)
                        .itemGroup(IRItemGroups.ORES)
                        .id(elementType.name().toLowerCase() + "_ore_dust")
                        .icon(new CustomItemStack(Material.IRON_ORE, name).getBukkit())
                        .register();
            }
        }
    }

    public static IndustrialRevivalItem of(ElementType elementType) {
        if (!LOADED) {
            setup();
        }
        return IndustrialRevivalItem.getById(KeyUtil.customKey(elementType.name().toUpperCase() + "_ORE_DUST"));
    }
}
