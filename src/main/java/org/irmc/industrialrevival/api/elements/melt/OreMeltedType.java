package org.irmc.industrialrevival.api.elements.melt;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.ColorUtil;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a melted type of elements in {@link ElementType}
 *
 * @author balugaq
 * @since 1.0
 */
@Getter
public class OreMeltedType extends MeltedType {
    private final @NotNull ElementType elementType;
    private final @NotNull NamespacedKey identifier;
    private final Component name;
    private final Component meltedName;

    /**
     * Constructor for OreMeltedType
     *
     * @param elementType the element type of the ore
     */
    protected OreMeltedType(@NotNull ElementType elementType) {
        this.elementType = elementType;
        this.identifier = KeyUtil.customKey("ore_melted_type_" + elementType.name().toLowerCase());
        this.name = IRDock.getPlugin().getLanguageManager().getMsgComponent(null, "ore_melted_type_name." + elementType.name().toLowerCase());
        this.meltedName = IRDock.getPlugin().getLanguageManager().getMsgComponent(null, "ore_melted_type_melted_name." + elementType.name().toLowerCase());
    }

    /**
     * Gets the {@link OreMeltedType} of the given element type.
     *
     * @param elementType the element type of the ore
     * @return the {@link OreMeltedType} of the given element type.
     */
    public static @NotNull OreMeltedType of(@NotNull ElementType elementType) {
        return new OreMeltedType(elementType);
    }

    /**
     * Gets the {@link TextColor} of the ore.
     *
     * @return the {@link TextColor} of the ore.
     * @see ColorUtil#generateAtomicColor(ElementType)
     */
    @Override
    public @NotNull TextColor getColor() {
        return TextColor.color(ColorUtil.generateAtomicColor(getElementType()));
    }
}
