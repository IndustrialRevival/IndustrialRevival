package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ReactCondition {
    public static final ReactCondition NONE = new ReactCondition(Type.NONE);
    public static final ReactCondition LIGHT = new ReactCondition(Type.LIGHT);
    public static final ReactCondition ELECTROLYSIS = new ReactCondition(Type.ELECTROLYSIS);
    public static final ReactCondition HEATING = new ReactCondition(Type.HEATING);
    public static final ReactCondition HIGH_TEMPERATURE = new ReactCondition(Type.HIGH_TEMPERATURE);

    private final Type type;
    private final @Nullable ChemReactable reactable;

    private ReactCondition(Type type) {
        this.type = type;
        this.reactable = null;
    }

    private ReactCondition(Type type, ChemReactable reactable) {
        this.type = type;
        this.reactable = reactable;
    }

    /**
     * Gets the type of the reacting condition.
     *
     * @return The type of the reacting condition.
     */
    public Type getType() {
        return type;
    }

    /**
     * Null if the react condition is not a catalyst.
     *
     * @return The chem reactable item that is catalyzing the reaction.
     */
    public @Nullable ChemReactable getReactable() {
        return reactable;
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
