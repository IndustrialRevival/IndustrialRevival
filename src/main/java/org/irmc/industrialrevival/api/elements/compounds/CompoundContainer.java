package org.irmc.industrialrevival.api.elements.compounds;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @NotNull
    public ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions) {
        return ReactHelper.react0(environment, conditions, mixed);
    }

    @NotNull
    public List<ReactResult> reactBalanced(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions) {
        return ReactHelper.reactBalanced(environment, conditions, mixed);
    }

    @NotNull
    public ReactResult reactAll(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull ChemicalFormula formula) {
        return ReactHelper.reactAll(environment, conditions, mixed, formula);
    }
}
