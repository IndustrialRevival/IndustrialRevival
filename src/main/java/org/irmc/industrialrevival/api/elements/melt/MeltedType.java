package org.irmc.industrialrevival.api.elements.melt;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a type of melted.
 * @author balugaq
 * @see MeltedObject
 * @see Smeltery
 */
public abstract class MeltedType {
    /**
     * The identifier of the melted type.
     * @return the identifier
     */
    @NotNull
    public abstract NamespacedKey getIdentifier();

    /**
     * The name of the melted type.
     * @return the name
     */
    @NotNull
    public abstract Component getName();

    /**
     * The name of the melted object.
     * @return the name of the melted object
     */
    @NotNull
    public abstract Component getMeltedName();

    /**
     * The color of the melted object.
     * @return the color of the melted object
     */
    @NotNull
    public abstract TextColor getColor();
}
