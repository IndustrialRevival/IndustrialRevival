package org.irmc.industrialrevival.api.multiblocks;

import lombok.Getter;

@Getter
public class RelativeLocation {
    private final int x;
    private final int y;
    private final int z;
    public RelativeLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
