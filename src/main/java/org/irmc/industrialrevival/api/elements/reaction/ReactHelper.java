package org.irmc.industrialrevival.api.elements.reaction;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.machines.process.Environment;
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
     * @return the getProduce of the reaction
     */
    @NotNull
    public static ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, ItemStack... items) {
        return react(environment, conditions, List.of(items));
    }

    /**
     * React the items with the conditions
     *
     * @param conditions the conditions of the reaction
     * @param items      the items to react
     * @return the getProduce of the reaction
     */
    @NotNull
    public static ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull List<ItemStack> items) {
        Map<ChemReactable, ItemStack> reactables = new HashMap<>();
        for (ItemStack item : items) {
            if (IndustrialRevivalItem.getByItem(item) instanceof ChemReactable cr) {
                reactables.put(cr, item);
            }
        }

        return react(environment, conditions, reactables);
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions the conditions of the reaction
     * @param reactables the reactables to react
     * @return the getProduce of the reaction
     */
    public static @NotNull ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Map<ChemReactable, ItemStack> reactables) {
        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        for (ChemReactable reactable : reactables.keySet()) {
            reactants.put(reactable.getChemicalCompound(reactables.get(reactable)), reactable.getMass(reactables.get(reactable)));
        }
        return react0(environment, conditions, reactants);
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions    the conditions of the reaction
     * @param reactants the masses of the reactants
     * @return the getProduce of the reaction
     */
    @NotNull
    public static ReactResult react0(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> reactants) {
        List<ChemicalFormula> formulas = new ArrayList<>(IRRegistry.getInstance().getChemicalFormulas().values());

        // shuffle the formulas, ensure that every reaction occurs uniformly
        formulas = JavaUtil.shuffle(formulas);
        for (ChemicalFormula formula : formulas) {
            if (!conditionSatisfied(environment, reactants, formula.getConditions(), conditions)) {
                continue;
            }

            if (!inputsSatisfied(environment, formula.getInput(), reactants.keySet())) {
                continue;
            }

            return calculateOutput(environment, conditions, reactants, formula);
        }

        return ReactResult.FAILED;
    }

    /**
     * React the reactables with the conditions
     *
     * @param conditions    the conditions of the reaction
     * @param reactants the masses of the reactants
     * @return the getProduce of the reaction
     */
    @NotNull
    public static List<ReactResult> reactBalanced(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> reactants) {
        List<ChemicalFormula> formulas = new ArrayList<>(IRRegistry.getInstance().getChemicalFormulas().values());

        // shuffle the formulas, ensure that every reaction occurs uniformly
        formulas = JavaUtil.shuffle(formulas);

        List<ReactResult> results = new ArrayList<>();
        for (ChemicalFormula formula : formulas) {
            if (!conditionSatisfied(environment, reactants, formula.getConditions(), conditions)) {
                continue;
            }

            if (!inputsSatisfied(environment, formula.getInput(), reactants.keySet())) {
                continue;
            }

            results.add(calculateOutput(environment, conditions, reactants, formula));
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
    public static boolean conditionSatisfied(@NotNull Environment environment, @NotNull Map<ChemicalCompound, Double> masses, @NotNull Set<ReactCondition> required, @NotNull Set<ReactCondition> current) {
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
    public static boolean inputsSatisfied(@NotNull Environment environment, @NotNull Map<ChemicalCompound, Integer> inputs, @NotNull Set<ChemicalCompound> current) {
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
     * @param reactants the masses of the reactants
     * @param formula       the chemical formula of the reaction
     * @return the getProduce of the reaction
     */
    @NotNull
    public static ReactResult calculateOutput(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> reactants, @NotNull ChemicalFormula formula) {
        return calculateOutput(environment, conditions, reactants, formula, false);
    }

    /**
     * Calculate the output of a reaction
     *
     * @param conditions    the conditions of the reaction
     * @param reactants the masses of the reactants
     * @param formula       the chemical formula of the reaction
     * @param reactAll      if true, the reaction will react all the reactants
     * @return the getProduce of the reaction
     */
    @NotNull
    public static ReactResult calculateOutput(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Map<ChemicalCompound, Double> reactants, @NotNull ChemicalFormula formula, boolean reactAll) {
        // input
        var proportion = formula.getInput();
        Double maxProportion = Double.MAX_VALUE;
        for (ChemicalCompound compound : proportion.keySet()) {
            maxProportion = Math.min(maxProportion, reactants.get(compound) / proportion.get(compound));
        }

        // reaction should react slowly instead of output all the products directly
        double max = maxProportion;
        if (!reactAll) {
            maxProportion /= 6;
        }

        // some reaction will be faster with the higher temperature
        if (formula.getConditionSensor() != null) {
            maxProportion = formula.getConditionSensor().apply(environment, conditions, maxProportion);
        }

        // if the maxProportion is null, the reaction should fail
        if (maxProportion == null) {
            return ReactResult.FAILED;
        }

        // normalize
        if (maxProportion > max) {
            maxProportion = max;
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

    public static ReactResult reactAll(Environment environment, Set<ReactCondition> conditions, Map<ChemicalCompound, Double> reactants, ChemicalFormula formula) {
        if (!conditionSatisfied(environment, reactants, formula.getConditions(), conditions)) {
            return ReactResult.FAILED;
        }

        if (!inputsSatisfied(environment, formula.getInput(), reactants.keySet())) {
            return ReactResult.FAILED;
        }

        return calculateOutput(environment, conditions, reactants, formula, true);
    }
}
