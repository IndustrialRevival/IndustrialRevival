package org.irmc.industrialrevival.api.elements.reaction;

import lombok.Data;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.items.attributes.CompoundContainerHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author balugaq
 */
@Data
public class Decomposer {
    public static final Random random = new Random();
    private final Map<ChemicalCompound, Double> weights;

    public Decomposer(ChemicalCompound output, double weight) {
        this(output, (Object) weight);
    }

    public Decomposer(Object... args) {
        double totalWeight = 0;
        Map<ChemicalCompound, Double> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (args[i] instanceof ChemicalCompound compound && args[i + 1] instanceof Number weight) {
                map.put(compound, weight.doubleValue());
                totalWeight += weight.doubleValue();
            }
        }

        for (var entry : map.entrySet()) {
            entry.setValue(entry.getValue() / totalWeight);
        }

        this.weights = map;
    }

    public void decompose(CompoundContainerHolder holder, Location location) {
        for (var entry : weights.entrySet()) {
            var r = random.nextDouble();
            if (r < entry.getValue()) {
                holder.mixCompounds(location, entry.getKey(), 1000D);
            }
        }
    }
}
