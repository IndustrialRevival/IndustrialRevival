package org.irmc.industrialrevival.api.machines.process;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class ReactOperation implements IOperation {
    private final ChemicalFormula running;
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
}
