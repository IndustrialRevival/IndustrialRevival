package org.irmc.industrialrevival.api.elements.compounds;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balugaq
 */
@NoArgsConstructor
@Data
public class CompoundContainer {
    private final Map<ChemicalCompound, Double> mixed = new HashMap<>();

    public CompoundContainer mix(Map<ChemicalCompound, Double> other) {
        for (var entry : other.entrySet()) {
            mixed.merge(entry.getKey(), entry.getValue(), Double::sum);
        }
        return this;
    }

    public CompoundContainer mix(CompoundContainer other) {
        return mix(other.mixed);
    }

    public CompoundContainer consume(Map<ChemicalCompound, Double> other) {
        for (var entry : other.entrySet()) {
            mixed.merge(entry.getKey(), -entry.getValue(), (a, b) -> Math.max(a + b, 0));
            if (mixed.get(entry.getKey()) <= 0) {
                mixed.remove(entry.getKey());
            }
        }

        return this;
    }

    public CompoundContainer consume(CompoundContainer other) {
        return consume(other.mixed);
    }


    public ReactResult react(ReactCondition[] conditions) {
        return ReactHelper.react0(conditions, mixed);
    }
}
