package org.irmc.industrialrevival.api.elements.melt_types;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.MeltedType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;

@Getter
public class OreMeltedType extends MeltedType {
    private final NamespacedKey identifier;
    private final Component name;
    private final Component meltedName;
    protected OreMeltedType(ElementType elementType) {
        this.identifier = KeyUtil.customKey("ore_melted_type_" + elementType.name().toLowerCase());
        // Didn't find a better way to get the name or melted name for ore melted types
        // todo: Should change getItemName to getText in the future
        this.name = IndustrialRevival.getInstance().getLanguageManager().getItemName("ore_melted_type_name_" + elementType.name().toLowerCase());
        this.meltedName = IndustrialRevival.getInstance().getLanguageManager().getItemName("ore_melted_type_melted_name_" + elementType.name().toLowerCase());
    }

    public static OreMeltedType of(ElementType elementType) {
        return new OreMeltedType(elementType);
    }
}
