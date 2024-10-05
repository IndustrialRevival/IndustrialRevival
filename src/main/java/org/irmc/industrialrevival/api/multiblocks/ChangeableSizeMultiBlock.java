package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Axis;
import org.jetbrains.annotations.NotNull;

public interface ChangeableSizeMultiBlock {
    int getSize(@NotNull Axis axis);

    void setSize(@NotNull Axis axis, int size);
}
