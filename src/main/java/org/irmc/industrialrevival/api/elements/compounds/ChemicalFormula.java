package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
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
    private @NotNull final Map<ChemicalCompound, Integer> input;
    private @NotNull final Map<ChemicalCompound, Integer> output;
    private @Nullable final ReactCondition[] conditions;

    /**
     * Constructor.
     * @param key the key of the formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(@NotNull NamespacedKey key, @NotNull String formula) {
        this(key, formula, null);
    }

    /**
     * Constructor.
     * @param key the key of the formula
     * @param formula the chemical formula string
     * @param conditions the conditions for the reaction
     *
     * @apiNote The conditions are optional and can be null.
     * @apiNote formula example: "Zn+H2SO4===ZnSO4+H2", "Fe2O3+3H2SO4===Fe2SO4_3+3H2O"
     */
    public ChemicalFormula(@NotNull NamespacedKey key, @NotNull String formula, @Nullable ReactCondition[] conditions) {
        Preconditions.checkNotNull(formula, "formula cannot be null");
        Preconditions.checkArgument(formula.matches("[A-Z][a-z]?[0-9]*"), "Invalid chemical formula: " + formula);
        String left = formula.split("===")[0];
        String right = formula.split("===")[1];

        String[] leftParts = left.split("\\+");
        String[] rightParts = right.split("\\+");

        this.key = key;
        this.input = parseCompounds(leftParts);
        this.output = parseCompounds(rightParts);
        this.conditions = conditions;
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
                part = part.replace(number, "");
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
}
