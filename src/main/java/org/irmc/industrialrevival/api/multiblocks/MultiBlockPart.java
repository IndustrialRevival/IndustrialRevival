package org.irmc.industrialrevival.api.multiblocks;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public class MultiBlockPart extends IndustrialRevivalItem {
    private MultiBlockCore parentCore;
    private RelativeLocation relativeLocation;
    public MultiBlockPart setParentCore(MultiBlockCore parentCore) {
        this.parentCore = parentCore;
        return this;
    }
    public MultiBlockPart setRelativeLocation(RelativeLocation relativeLocation) {
        this.relativeLocation = relativeLocation;
        return this;
    }
}
