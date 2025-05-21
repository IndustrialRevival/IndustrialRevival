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
import java.util.Set;

public class ReactHelper {
    /**
     * React the items with the conditions
     *
     * @param conditions the conditions of the reaction
     * @param items      the items to react
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react(Set<ReactCondition> conditions, ItemStack... items) {
        return react(conditions, List.of(items));
    }

    /**
     * React the items with the conditions
     *
     * @param conditions the conditions of the reaction
     * @param items      the items to react
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react(Set<ReactCondition> conditions, List<ItemStack> items) {
        Map<ChemReactable, ItemStack> reactables = new HashMap<>();
        for (ItemStack item : items) {
            if (IndustrialRevivalItem.getByItem(item) instanceof ChemReactable cr) {
                reactables.put(cr, item);
            }
        }

        return react(conditions, reactables);
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions the conditions of the reaction
     * @param reactables the reactables to react
     * @return the produce of the reaction
     */
    public static ReactResult react(Set<ReactCondition> conditions, Map<ChemReactable, ItemStack> reactables) {
        Map<ChemicalCompound, Double> currentMasses = new HashMap<>();
        for (ChemReactable reactable : reactables.keySet()) {
            currentMasses.put(reactable.getChemicalCompound(reactables.get(reactable)), reactable.getMass(reactables.get(reactable)));
        }
        return react0(conditions, currentMasses);
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions    the conditions of the reaction
     * @param currentMasses the masses of the reactants
     * @return the produce of the reaction
     */
    @NotNull
    public static ReactResult react0(@NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> currentMasses) {
        List<ChemicalFormula> formulas = new ArrayList<>(IRRegistry.getInstance().getChemicalFormulas().values());

        // shuffle the formulas, ensure that every reaction occurs uniformly
        formulas = JavaUtil.shuffle(formulas);
        for (ChemicalFormula formula : formulas) {
            if (!conditionSatisfied(currentMasses, formula.getConditions(), conditions)) {
                continue;
            }

            if (!inputsSatisfied(formula.getInput(), currentMasses.keySet())) {
                continue;
            }

            return calculateOutput(conditions, currentMasses, formula);
        }

        return ReactResult.FAILED;
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions    the conditions of the reaction
     * @param currentMasses the masses of the reactants
     * @return the produce of the reaction
     */
    @NotNull
    public static List<ReactResult> reactBalanced(@NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> currentMasses) {
        List<ChemicalFormula> formulas = new ArrayList<>(IRRegistry.getInstance().getChemicalFormulas().values());

        // shuffle the formulas, ensure that every reaction occurs uniformly
        formulas = JavaUtil.shuffle(formulas);

        List<ReactResult> results = new ArrayList<>();
        for (ChemicalFormula formula : formulas) {
            if (!conditionSatisfied(currentMasses, formula.getConditions(), conditions)) {
                continue;
            }

            if (!inputsSatisfied(formula.getInput(), currentMasses.keySet())) {
                continue;
            }

            results.add(calculateOutput(conditions, currentMasses, formula));
        }

        return results;
    }

    /**
     * Check if the current conditions satisfy the conditions of the reaction
     *
     * @param required the required conditions of the reaction
     * @param current  the current conditions of the reaction
     * @return true if the current conditions satisfy the conditions of the reaction, false otherwise
     */
    public static boolean conditionSatisfied(@NotNull Map<ChemicalCompound, Double> masses, @NotNull Set<ReactCondition> required, @NotNull Set<ReactCondition> current) {
        for (ReactCondition requiredCondition : required) {
            if (!current.contains(requiredCondition)) {
                if (requiredCondition.getType() == ReactCondition.Type.CATALYZER) {
                    if (!masses.containsKey(requiredCondition.getCatalyst())) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check if the current reactants satisfy the inputs of the reaction
     *
     * @param inputs  the inputs of the reaction
     * @param current the reactants
     * @return true if the current reactants satisfy the inputs of the reaction, false otherwise
     */
    public static boolean inputsSatisfied(Map<ChemicalCompound, Integer> inputs, Set<ChemicalCompound> current) {
        ;
        for (ChemicalCompound compound : inputs.keySet()) {
            if (!current.contains(compound)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calculate the output of a reaction
     *
     * @param conditions    the conditions of the reaction
     * @param currentMasses the masses of the reactants
     * @param formula       the chemical formula of the reaction
     * @return the produce of the reaction
     */
    public static ReactResult calculateOutput(Set<ReactCondition> conditions, Map<ChemicalCompound, Double> currentMasses, ChemicalFormula formula) {
        // input
        var proportion = formula.getInput();
        Double maxProportion = Double.MAX_VALUE;
        for (ChemicalCompound compound : proportion.keySet()) {
            maxProportion = Math.min(maxProportion, currentMasses.get(compound) / proportion.get(compound));
        }

        // reaction should react slowly instead of output all the products directly
        maxProportion /= 6;
        if (formula.getConditionSensor() != null) {
            maxProportion = formula.getConditionSensor().apply(conditions, maxProportion);
        }

        // if the maxProportion is null, the reaction should fail
        if (maxProportion == null) {
            return ReactResult.FAILED;
        }

        var totalMolarMass = 0.0;
        for (ChemicalCompound compound : proportion.keySet()) {
            totalMolarMass += proportion.get(compound) * compound.getMolarMass();
        }

        var finalConsume = new HashMap<ChemicalCompound, Double>();
        for (ChemicalCompound compound : proportion.keySet()) {
            /* proportion.get(compound) * maxProportion = Molar mass */
            /* Molar mass / totalMolarMass * compound.getMolarMass() = Real mass */
            finalConsume.put(compound, proportion.get(compound) * maxProportion / totalMolarMass * compound.getMolarMass());
        }

        var finalResult = new HashMap<ChemicalCompound, Double>();
        for (ChemicalCompound compound : formula.getOutput().keySet()) {
            /* formula.getOutput().get(compound) * maxProportion = Molar mass */
            /* Molar mass / totalMolarMass * compound.getMolarMass() = Real mass */
            finalResult.put(compound, formula.getOutput().get(compound) * maxProportion / totalMolarMass * compound.getMolarMass());
        }

        return new ReactResult(formula, finalConsume, finalResult);
    }
}
