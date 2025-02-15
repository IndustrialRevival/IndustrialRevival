package org.irmc.industrialrevival.implementation.items.collection;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class OreDusts {
    public static final IndustrialRevivalItem ALUMINUM_DUST = new IndustrialRevivalItem()
            .setAddon(IndustrialRevival.getInstance())
            .addItemGroup(IRItemGroups.ORES);

}
