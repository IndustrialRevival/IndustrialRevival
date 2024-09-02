package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Axis;

public interface ChangeableSizeMultiBlock {
    int getSize(Axis axis);

    void setSize(Axis axis, int size);
}
