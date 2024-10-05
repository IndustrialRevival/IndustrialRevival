package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Axis;

import javax.annotation.NotNull;

public interface ChangeableSizeMultiBlock {
    int getSize(@NotNull Axis axis);

    void setSize(@NotNull Axis axis, int size);
}
