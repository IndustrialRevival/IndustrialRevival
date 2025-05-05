package org.irmc.industrialrevival.api.elements.reaction;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.utils.JavaUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ReactHelper {
    /**
     * React the items with the conditions
     * @param conditions the conditions of the reaction
     * @param items the items to react
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react(ReactCondition[] conditions, ItemStack... items) {
        return react(conditions, List.of(items));
    }

    /**
     * React the items with the conditions
     * @param conditions the conditions of the reaction
     * @param items the items to react
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react(ReactCondition[] conditions, List<ItemStack> items) {
        Map<ChemReactable, ItemStack> reactables = new HashMap<>();
        for (ItemStack item :  items) {
            if (IndustrialRevivalItem.getByItem(item) instanceof ChemReactable cr) {
                reactables.put(cr, item);
            }
        }

        return react(conditions, reactables);
    }

    /**
     * React the reactables with the conditions
     * @param conditions the conditions of the reaction
     * @param reactables the reactables to react
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react(ReactCondition[] conditions, Map<ChemReactable, ItemStack> reactables) {
        List<ChemicalFormula> formulas = new ArrayList<>(IRRegistry.getInstance().getChemicalFormulas().values());

        // shuffle the formulas, ensure that every reaction occurs uniformly
        formulas = JavaUtil.shuffle(formulas);
        for (ChemicalFormula formula : formulas) {
            if (!conditionSatisfied(formula.getConditions(), conditions)) {
                continue;
            }

            Set<ChemicalCompound> current = reactables.entrySet().stream()
                    .map(entry -> entry.getKey().getChemicalCompound(entry.getValue()))
                    .collect(Collectors.toSet());
            if (!inputsSatisfied(formula.getInput(), current)) {
                continue;
            }

            Map<ChemicalCompound, Double> currentMasses = reactables.entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey().getChemicalCompound(entry.getValue()), entry -> entry.getKey().getMass(entry.getValue())));

            return calculateOutput(conditions, currentMasses, formula);
        }

        return ReactResult.FAILED;
    }

    /**
     * Check if the current conditions satisfy the conditions of the reaction
     * @param required the required conditions of the reaction
     * @param current the current conditions of the reaction
     * @return true if the current conditions satisfy the conditions of the reaction, false otherwise
     */
    public static boolean conditionSatisfied( ReactCondition[] required, ReactCondition[] current) {
        for (ReactCondition requiredCondition : required) {
            boolean satisfied = false;
            for (ReactCondition currentCondition : current) {
                if (currentCondition.equals(requiredCondition)) {
                    satisfied = true;
                    break;
                }
            }
            if (!satisfied) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the current reactants satisfy the inputs of the reaction
     * @param inputs the inputs of the reaction
     * @param current the reactants
     * @return true if the current reactants satisfy the inputs of the reaction, false otherwise
     */
    public static boolean inputsSatisfied(Map<ChemicalCompound, Integer> inputs, Set<ChemicalCompound> current) {;
        for (ChemicalCompound compound : inputs.keySet()) {
            if (!current.contains(compound)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calculate the output of a reaction
     * @param conditions the conditions of the reaction
     * @param currentMasses the masses of the reactants
     * @param formula the chemical formula of the reaction
     * @return the produce of the reaction
     */
    public static ReactResult calculateOutput(ReactCondition[] conditions, Map<ChemicalCompound, Double> currentMasses, ChemicalFormula formula) {
        // input
        var proportion = formula.getInput();
        Double maxProportion = Double.MAX_VALUE;
        for (ChemicalCompound compound : proportion.keySet()) {
            maxProportion = Math.min(maxProportion, currentMasses.get(compound) / proportion.get(compound));
        }

        // reaction should react slowly instead of output all the products directly
        maxProportion /= 10;
        if (formula.getConditionSensor() != null) {
            maxProportion = formula.getConditionSensor().apply(conditions, maxProportion);
        }

        // if the maxProportion is null, the reaction should fail
        if (maxProportion == null) {
            return ReactResult.FAILED;
        }

        var finalConsume = new HashMap<ChemicalCompound, Double>();
        for (ChemicalCompound compound : proportion.keySet()) {
            finalConsume.put(compound, proportion.get(compound) * maxProportion);
        }

        var finalResult = new HashMap<ChemicalCompound, Double>();
        for (ChemicalCompound compound : formula.getOutput().keySet()) {
            finalResult.put(compound, formula.getOutput().get(compound) * maxProportion);
        }

        return new ReactResult(formula, finalConsume, finalResult);
    }
}
