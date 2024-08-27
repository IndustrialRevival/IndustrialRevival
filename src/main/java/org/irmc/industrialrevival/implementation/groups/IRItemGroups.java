package org.irmc.industrialrevival.implementation.groups;

import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.groups.NormalItemGroup;
import org.irmc.industrialrevival.core.utils.KeyUtil;

public class IRItemGroups {
    public static final ItemGroup ORES = new NormalItemGroup(KeyUtil.customKey("ores"), GroupIcons.GROUP_ORE);
    public static final ItemGroup MANUAL_MACHINES =
            new NormalItemGroup(KeyUtil.customKey("manual_machines"), GroupIcons.GROUP_MANUAL_MACHINES);
    public static final ItemGroup MATERIALS =
            new NormalItemGroup(KeyUtil.customKey("materials"), GroupIcons.GROUP_MATERIALS);
    public static final ItemGroup SMELTING =
            new NormalItemGroup(KeyUtil.customKey("smelting"), GroupIcons.GROUP_SMELTING);
    public static final ItemGroup ELECTRIC_MACHINES =
            new NormalItemGroup(KeyUtil.customKey("electric_machines"), GroupIcons.GROUP_ELECTRIC_MACHINES);
    public static final ItemGroup TOOLS = new NormalItemGroup(KeyUtil.customKey("tools"), GroupIcons.GROUP_TOOLS);
    public static final ItemGroup ARMORS = new NormalItemGroup(KeyUtil.customKey("armors"), GroupIcons.GROUP_ARMORS);
    public static final ItemGroup DEFENSE = new NormalItemGroup(KeyUtil.customKey("defense"), GroupIcons.GROUP_DEFENSE);
    public static final ItemGroup FOOD = new NormalItemGroup(KeyUtil.customKey("food"), GroupIcons.GROUP_FOOD);
    public static final ItemGroup MISC = new NormalItemGroup(KeyUtil.customKey("misc"), GroupIcons.GROUP_MISC);

    public static void setup() {
        ORES.register();
        MANUAL_MACHINES.register();
        MATERIALS.register();
        SMELTING.register();
        ELECTRIC_MACHINES.register();
        TOOLS.register();
        ARMORS.register();
        DEFENSE.register();
        FOOD.register();
        MISC.register();
    }
}
