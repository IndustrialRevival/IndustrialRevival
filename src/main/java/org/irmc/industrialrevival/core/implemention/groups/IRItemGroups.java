package org.irmc.industrialrevival.core.implemention.groups;

import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.core.utils.Icons;
import org.irmc.industrialrevival.core.utils.Keys;

public class IRItemGroups {
    public static final ItemGroup ORES = new ItemGroup(Keys.customKey("ores"), Icons.GROUP_ORE);
    public static final ItemGroup MANUAL_MACHINES = new ItemGroup(Keys.customKey("manual_machines"), Icons.GROUP_MANUAL_MACHINES);
    public static final ItemGroup MATERIALS = new ItemGroup(Keys.customKey("materials"), Icons.GROUP_MATERIALS);
    public static final ItemGroup SMELTING = new ItemGroup(Keys.customKey("smelting"), Icons.GROUP_SMELTING);
    public static final ItemGroup ELECTRIC_MACHINES = new ItemGroup(Keys.customKey("electric_machines"), Icons.GROUP_ELECTRIC_MACHINES);
    public static final ItemGroup TOOLS = new ItemGroup(Keys.customKey("tools"), Icons.GROUP_TOOLS);
    public static final ItemGroup ARMORS = new ItemGroup(Keys.customKey("armors"), Icons.GROUP_ARMORS);
    public static final ItemGroup DEFENSE = new ItemGroup(Keys.customKey("defense"), Icons.GROUP_DEFENSE);
    public static final ItemGroup FOOD = new ItemGroup(Keys.customKey("food"), Icons.GROUP_FOOD);
    public static final ItemGroup MISC = new ItemGroup(Keys.customKey("misc"), Icons.GROUP_MISC);

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
