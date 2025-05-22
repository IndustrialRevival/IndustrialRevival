package org.irmc.industrialrevival.api.machines.process;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;

import java.util.Map;

/**
 * @author baluagq
 */
@Data
@RequiredArgsConstructor
public class ReactOperation implements IOperation {
    private final ChemicalFormula formula;
    private final Map<ChemicalCompound, Double> consume;
    private final Map<ChemicalCompound, Double> produce;


    @Override
    public void tick() {
    }

    @Override
    public void addProgress(int progress) {
    }

    @Override
    public int getCurrentProgress() {
        return 0;
    }

    @Override
    public int getTotalProgress() {
        return 0;
    }

    public static ReactOperation warp(ReactResult reactResult) {
        return new ReactOperation(reactResult.formula(), reactResult.consume(), reactResult.produce());
    }
}
