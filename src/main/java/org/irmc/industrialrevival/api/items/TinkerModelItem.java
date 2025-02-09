package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.irmc.industrialrevival.api.elements.TinkerType;
import org.irmc.industrialrevival.api.items.attributes.TinkerModel;

@Getter
public class TinkerModelItem extends IndustrialRevivalItem implements TinkerModel {
    private TinkerType tinkerType;
    public TinkerModelItem() {
        super();
    }

    public TinkerModelItem setTinkerType(TinkerType tinkerType) {
        this.tinkerType = tinkerType;
        return this;
    }
}
