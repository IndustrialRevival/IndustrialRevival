package org.irmc.industrialrevival.api.elements;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;

public abstract class MeltedType {
    public abstract NamespacedKey getIdentifier();
    public abstract Component getName();
    public abstract Component getMeltedName();
    public abstract TextColor getColor();
}
