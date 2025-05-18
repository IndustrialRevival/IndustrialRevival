package org.irmc.industrialrevival.api.elements.melt;

import lombok.Data;
import lombok.Setter;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.jetbrains.annotations.NotNull;

/**
 * An object that describes the melted object and its amount.
 *
 * @author balugaq
 * @see Smeltery
 */
@Data
public class MeltedObject {
    private final @NotNull MeltedType type;
    @Setter
    private int amount;

    /**
     * Constructor.
     *
     * @param type   the type of the melted object.
     * @param amount the amount of the melted object.
     */
    public MeltedObject(@NotNull MeltedType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
