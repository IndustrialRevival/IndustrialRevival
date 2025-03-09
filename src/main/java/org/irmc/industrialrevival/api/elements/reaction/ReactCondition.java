package org.irmc.industrialrevival.api.elements.reaction;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ReactCondition {
    /* Default react conditions */
    public static final ReactCondition NONE = new ReactCondition(Type.NONE);
    public static final ReactCondition LIGHT = new ReactCondition(Type.LIGHT);
    public static final ReactCondition ELECTROLYSIS = new ReactCondition(Type.ELECTROLYSIS);
    public static final ReactCondition HEATING = new ReactCondition(Type.HEATING);
    public static final ReactCondition HIGH_TEMPERATURE = new ReactCondition(Type.HIGH_TEMPERATURE);

    /**
     * -- GETTER --
     *  Gets the type of the reacting condition.
     *
     */
    @Getter
    private final Type type;
    private final @Nullable ChemReactable catalyst;

    private ReactCondition(Type type) {
        this.type = type;
        this.catalyst = null;
    }

    private ReactCondition(Type type, @Nullable ChemReactable catalyst) {
        this.type = type;
        this.catalyst = catalyst;
    }

    /**
     * Null if the react condition is not a catalyst.
     *
     * @return The chem reactable item that is catalyzing the reaction.
     */
    public @Nullable ChemReactable getCatalyst() {
        return catalyst;
    }

    public static @NotNull ReactCondition asCatalyzer(ChemReactable reactable) {
        return new ReactCondition(Type.CATALYZER, reactable);
    }

    public enum Type {
        NONE,
        LIGHT,
        ELECTROLYSIS,
        HEATING,
        HIGH_TEMPERATURE,
        CATALYZER
    }
}
