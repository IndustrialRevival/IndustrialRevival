package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Axis;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;

public interface IMultiBlock {
    boolean environmentCheck(Block block, MachineMenu menu);

    void setLimit(int maxX, int maxY, int maxZ);

    int getLimit(Axis axis);
}
