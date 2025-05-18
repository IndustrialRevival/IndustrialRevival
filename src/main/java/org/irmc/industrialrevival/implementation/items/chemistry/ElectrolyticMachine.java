package org.irmc.industrialrevival.implementation.items.chemistry;

import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.utils.MenuUtil;

public class ElectrolyticMachine extends Reactor {
    @Override
    public MatrixMenuDrawer getMatrixMenuDrawer() {
        return new MatrixMenuDrawer(36)
                .addLine("AAAASAAAA")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addExplain("A", "Input and output border", MenuUtil.INPUT_AND_OUTPUT_BORDER)
                .addExplain("S", "Status", getBaseStatusIcon())
                .addExplain("i", "Item slot");
    }
    @Override
    public void loadRecipes() {
        IRRegistry.getInstance().getChemicalFormulas().values().forEach(formula -> {
            if (formula.getConditions().length == 1 && formula.getConditions()[0] == ReactCondition.ELECTROLYSIS) {
                recipes.add(new MachineRecipe(0, 0, asRawItemLevel(formula.getInput()), asRawItemLevel(formula.getOutput())));
            }
        });
    }
}
