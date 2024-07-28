package org.irmc.industrialrevival.core.impl;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.groups.NormalItemGroup;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class IRGroups {
    public static ItemGroup ORES;

    public static void setup() {
        ORES = new NormalItemGroup(new NamespacedKey(IndustrialRevival.getInstance(), "group_ores"),
                new CustomItemStack(Material.GUNPOWDER, IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "groups.ores")));

        ORES.register();
    }
}
