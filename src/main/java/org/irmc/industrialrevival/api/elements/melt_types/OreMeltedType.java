package org.irmc.industrialrevival.api.elements.melt_types;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.ElementUtils;
import org.irmc.industrialrevival.api.items.attributes.Meltable;
import org.irmc.industrialrevival.api.elements.MeltedType;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.items.ElementItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.Range;

/**
 * This class represents a melted type of elements in {@link ElementType}
 *
 * @author balugaq
 * @since 1.0
 */
@Getter
public class OreMeltedType extends MeltedType implements Meltable {
    private final ElementType elementType;
    private final NamespacedKey identifier;
    private final Component name;
    private final Component meltedName;
    protected OreMeltedType(ElementType elementType) {
        this.elementType = elementType;
        this.identifier = KeyUtil.customKey("ore_melted_type_" + elementType.name().toLowerCase());
        this.name = IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "ore_melted_type_name." + elementType.name().toLowerCase());
        this.meltedName = IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "ore_melted_type_melted_name." + elementType.name().toLowerCase());
    }

    public static OreMeltedType of(ElementType elementType) {
        return new OreMeltedType(elementType);
    }

    @Override
    public MeltedType getMeltedType(ItemStack itemStack) {
        if (IndustrialRevivalItem.getByItem(itemStack) instanceof ElementItem elementItem) {
            return of(elementItem.getElementType());
        }
        return null;
    }

    @Override
    public @Range(from = 0, to = Smeltery.MAX_FUEL_CAPACITY) int getMeltingPoint(ItemStack itemStack) {
        return getFuelUse(itemStack);
    }

    @Override
    public int getFuelUse(ItemStack itemStack) {
        if (IndustrialRevivalItem.getByItem(itemStack) instanceof ElementItem elementItem) {
            return (int) elementItem.getElementType().getMeltingPoint();
        }
        return 0;
    }

    @Override
    public TextColor getColor() {
        return TextColor.color(ElementUtils.getAtomicColor(getElementType()));
    }
}
