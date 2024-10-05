package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Axis;

import javax.annotation.Nonnull;

public interface ChangeableSizeMultiBlock {
    int getSize(@Nonnull Axis axis);

    void setSize(@Nonnull Axis axis, int size);
}
