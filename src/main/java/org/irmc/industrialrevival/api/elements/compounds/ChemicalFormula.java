package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regards it as a formula decoder for chemical reactions.
 *
 * @author balugaq
 */
@Getter
public class ChemicalFormula {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+)");
    private @NotNull final NamespacedKey key;
    private @NotNull final Map<ChemicalCompound, Integer> input; // proportion of each compound
    private @NotNull final Map<ChemicalCompound, Integer> output; // proportion of each compound
    private @Nullable ReactCondition[] conditions;
    private @Nullable final ConditionSensor conditionSensor;

    public ChemicalFormula(@NotNull NamespacedKey key, @NotNull String formula) {
        this(key, formula, null);
    }

    /**
     * Constructor.
     * @param key the key of the formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(@NotNull NamespacedKey key, @NotNull String formula, ReactCondition[] conditions) {
        this(key, formula, conditions, null);
    }

    /**
     * Constructor.
     * @param key the key of the formula
     * @param formula the chemical formula string
     * @param conditions the conditions for the reaction
     *
     * @apiNote The conditions are optional and can be null.
     * @apiNote formula example: "Zn+H2SO4===ZnSO4+H2", "Fe2O3+3H2SO4===Fe2(SO4)_3+3H2O"
     */
    public ChemicalFormula(@NotNull NamespacedKey key, @NotNull String formula, @Nullable ReactCondition[] conditions, ConditionSensor conditionSensor) {
        Preconditions.checkNotNull(formula, "formula cannot be null");
        Preconditions.checkArgument(formula.contains("==="), "Invalid chemical formula: " + formula);
        Preconditions.checkArgument(formula.split("===").length == 2, "Invalid chemical formula: " + formula);
        formula = formula.replaceAll(" ", "");
        String left = formula.split("===")[0];
        String right = formula.split("===")[1];

        String[] leftParts = left.split("\\+");
        String[] rightParts = right.split("\\+");

        this.key = key;
        this.input = parseCompounds(leftParts);
        this.output = parseCompounds(rightParts);
        this.conditions = conditions;
        this.conditionSensor = conditionSensor;
    }

    /**
     * Parses a chemical compound from a string.
     * @param compoundName the name of the compound
     * @return the chemical compound or null if it is not found
     */
    @Nullable
    public static ChemicalCompound parseCompound(@NotNull String compoundName) {
        return ChemicalCompound.forName(compoundName);
    }

    /**
     * Parses a list of chemical compounds from a list of strings.
     * @param parts the list of strings
     * @return the map of chemical compounds and their counts
     */
    @NotNull
    public static Map<ChemicalCompound, Integer> parseCompounds(@NotNull String[] parts) {
        Map<ChemicalCompound, Integer> compounds = new HashMap<>();
        for (String part : parts) {
            // example: "Zn"
            // example: “3H2SO4”
            // if the part begins with a number, it is the count of the compound
            Matcher matcher = NUMBER_PATTERN.matcher(part);
            int count = 1;
            if (matcher.find()) {
                // part example: "3H2SO4"
                // begins with a number
                String number = matcher.group(1);
                count = Integer.parseInt(number);
                part = part.substring(number.length());
            }

            // example: "Zn"
            // example: "H2SO4"  (no count)
            ChemicalCompound compound = parseCompound(part);
            if (compound == null) {
                throw new IllegalArgumentException("Invalid chemical compound: " + part);
            }

            compounds.put(compound, count);
        }

        return compounds;
    }

    public ChemicalFormula register() {
        IndustrialRevival.getInstance().getRegistry().registerChemicalFormula(this);
        return this;
    }

    public ChemicalFormula registerElectrolysis() {
        for (var condition : conditions) {
            if (condition == ReactCondition.ELECTROLYSIS) {
                return register();
            }
        }

        // add electrolysis condition
        ReactCondition[] newConditions = new ReactCondition[conditions.length + 1];
        System.arraycopy(conditions, 0, newConditions, 0, conditions.length);
        newConditions[conditions.length] = ReactCondition.ELECTROLYSIS;
        conditions = newConditions;
        return register();
    }

    public Component humanize() {
        return humanize(false);
    }

    public Component humanize(boolean hoverable) {
        var builder = Component.text();
        builder.append(humanizePart(input));
        builder.append(humanizeConditions(hoverable));
        builder.append(humanizePart(output));
        return builder.build();
    }

    public Component humanizePart(Map<ChemicalCompound, Integer> compounds) {
        var builder = Component.text();
        int index = 1;
        for (var entry : compounds.entrySet()) {
            builder.append(Component.text(entry.getValue())).append(Component.text(entry.getKey().getName()));
            if (index != compounds.size()) {
                builder.append(Component.text("+"));
            }
        }

        return builder.build();
    }

    public Component humanizeConditions(boolean hoverable) {
        if (conditions.length == 0 || conditions[0] == ReactCondition.NONE) {
            return Component.text("===");
        }

        if (hoverable) {
            return Component.text("===").append(Component.translatable("chemistry.formula.conditions").hoverEvent(humanizeConditions(false))).append(Component.text("==="));
        } else {
            var builder = Component.text();
            builder.append(Component.text("==="));
            for (var condition : getConditions()) {
                if (condition != null) {
                    builder.append(condition.humanize());
                }
            }
            builder.append(Component.text("==="));
            return builder.build();
        }
    }

    @FunctionalInterface
    public interface ConditionSensor extends BiFunction<ReactCondition[], Double, Double> {
        /**
         * Returns the max producing proportion
         * @param conditions the function
         * @return the max producing proportion
         */
        @Override
        Double apply(ReactCondition[] conditions, Double current);
    }
}
