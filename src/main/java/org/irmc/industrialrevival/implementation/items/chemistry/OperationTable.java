package org.irmc.industrialrevival.implementation.items.chemistry;

import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.MenuUtil;

import java.util.List;
import java.util.Set;

public class OperationTable extends Reactor {
    public OperationTable() {
        super();
        setAddon(IndustrialRevival.getInstance());
    }

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
        for (var formula : IRRegistry.getInstance().getChemicalFormulas().values()) {
            recipes.add(new MachineRecipe(0, 0, asRawItemLevel(formula.getInput()), asRawItemLevel(formula.getOutput())));
        }
    }

    // todo: for test ↓
    public static final Set<ReactCondition> CONDITIONS = Set.of(
            ReactCondition.ELECTROLYSIS,
            ReactCondition.HIGH_TEMPERATURE,
            ReactCondition.HEATING,
            ReactCondition.LIGHT
    );

    @Override
    public Set<ReactCondition> getReactConditions(MachineMenu menu) {
        return CONDITIONS;
    }
}
