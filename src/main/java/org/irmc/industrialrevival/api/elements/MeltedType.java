package org.irmc.industrialrevival.api.elements;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;

public abstract class MeltedType {
    public abstract NamespacedKey getIdentifier();
    public abstract Component getName();
    public abstract Component getMeltedName();
}
