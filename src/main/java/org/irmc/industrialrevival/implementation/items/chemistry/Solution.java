package org.irmc.industrialrevival.implementation.items.chemistry;

import lombok.Data;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.objects.enums.TriState;

import java.util.Map;

public class Solution extends IndustrialRevivalItem implements ChemReactable {
    // todo: ↓
    @Data
    public static class CompoundStorable {
        public final Map<ChemicalCompound, CompoundMetadata> compounds;
    }

    // todo: ↓
    @Data
    public static class CompoundMetadata {
        public final double mass;
        public final TriState isSediment;
        public final TriState isGas;
        // Also means un-save
        public final Map<String, Object> unsafe;
    }
}
