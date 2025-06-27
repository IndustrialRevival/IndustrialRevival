package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.api.objects.CiFunction;
import org.irmc.industrialrevival.api.objects.exceptions.UnknownChemicalCompoundException;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.NumberUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Regards it as a formula decoder for chemical reactions.
 *
 * @author balugaq
 */
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"input", "output"})
@Data
public class ChemicalFormula {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+)");
    private final int id;
    private @NotNull Map<ChemicalCompound, Integer> input; // molar mass of each compound
    private @NotNull Map<ChemicalCompound, Integer> output; // molar mass of each compound
    private @Nullable final ConditionSensor conditionSensor;
    private @NotNull Set<ReactCondition> conditions;
    private final @NotNull String rawFormula;

    public ChemicalFormula(int id, @NotNull String formula) {
        this(id, formula, new HashSet<>());
    }

    /**
     * Constructor.
     *
     * @param id     the id of the formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull ReactCondition condition) {
        this(id, formula, Set.of(condition), null);
    }

    /**
     * Constructor.
     *
     * @param id     the id of the formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull ReactCondition... conditions) {
        this(id, formula, Arrays.stream(conditions).collect(Collectors.toSet()), null);
    }

    /**
     * Constructor.
     *
     * @param id     the id of the formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull Set<ReactCondition> conditions) {
        this(id, formula, conditions, null);
    }

    /**
     * Constructor.
     *
     * @param id        the key of the formula
     * @param formula    the chemical formula string
     * @param conditions the conditions for the reaction
     * @apiNote The conditions are optional and can be null.
     * @apiNote formula example: "Zn+H2SO4===ZnSO4+H2", "Fe2O3+3H2SO4===Fe2(SO4)_3+3H2O"
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull Set<ReactCondition> conditions, @Nullable ConditionSensor conditionSensor) {
        Preconditions.checkNotNull(formula, "formula cannot be null");
        Preconditions.checkArgument(formula.contains("==="), "Invalid chemical formula: " + formula);
        Preconditions.checkArgument(formula.split("===").length == 2, "Invalid chemical formula: " + formula);
        Preconditions.checkNotNull(conditions, "conditions cannot be null");

        formula = formula.replaceAll(" ", "");
        String left = formula.split("===")[0];
        String right = formula.split("===")[1];

        String[] leftParts = left.split("\\+");
        String[] rightParts = right.split("\\+");

        this.id = id;
        this.rawFormula = formula;
        this.conditions = conditions;
        this.conditionSensor = conditionSensor;

        try {
            this.input = parseCompounds(leftParts);
            this.output = parseCompounds(rightParts);
        } catch (UnknownChemicalCompoundException e) {
            this.input = new HashMap<>();
            this.output = new HashMap<>();
            Debug.error(new UnknownChemicalCompoundException(formula, getId(), e));
            return;
        }

        register();
    }

    /**
     * Parses a chemical compound from a string.
     *
     * @param compoundName the name of the compound
     * @return the chemical compound or null if it is not found
     */
    @Nullable
    public static ChemicalCompound parseCompound(@NotNull String compoundName) {
        return ChemicalCompound.forName(compoundName);
    }

    /**
     * Parses a list of chemical compounds from a list of strings.
     *
     * @param parts the list of strings
     * @return the map of chemical compounds and their counts
     */
    @NotNull
    public static Map<ChemicalCompound, Integer> parseCompounds(@NotNull String[] parts) {
        Map<ChemicalCompound, Integer> compounds = new LinkedHashMap<>();
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
                throw new UnknownChemicalCompoundException("Invalid chemical compound: " + part);
            }

            compounds.put(compound, count);
        }

        return compounds;
    }

    public ChemicalFormula register() {
        IRDock.getPlugin().getRegistry().registerChemicalFormula(this);
        return this;
    }

    public TextComponent humanize() {
        return humanize(false);
    }

    public TextComponent humanize(boolean hoverable) {
        var builder = Component.text();
        builder.append(humanizePart(input));
        builder.append(humanizeConditions(hoverable));
        builder.append(humanizePart(output));
        return builder.build();
    }

    public TextComponent humanizePart(Map<ChemicalCompound, Integer> compounds) {
        var builder = Component.text();
        int index = 1;
        for (var entry : compounds.entrySet()) {
            if (entry.getValue() != 1) {
                builder.append(Component.text(entry.getValue()));
            }

            builder.append(Component.text(NumberUtil.toSubscript(entry.getKey().getHumanizedName())));
            if (index != compounds.size()) {
                builder.append(Component.text("+"));
            }
            index++;
        }

        return builder.build();
    }

    public TextComponent humanizeConditions(boolean hoverable) {
        if (conditions.isEmpty() || conditions.stream().findFirst().orElse(ReactCondition.NONE).equals(ReactCondition.NONE)) {
            return Component.text("===");
        }

        if (hoverable) {
            return Component.empty()
                    .append(Component.text("==="))
                    .append(Component.translatable("chemistry.formula.conditions").hoverEvent(
                            humanizeConditions(false)
                    )).append(Component.text("==="));
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
    public interface ConditionSensor extends CiFunction<Environment, Set<ReactCondition>, Double, Double> {
        /**
         * Returns the max producing proportion
         *
         * @param conditions the function
         * @return the max producing proportion
         */
        @Override
        Double apply(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Double current);
    }
}
