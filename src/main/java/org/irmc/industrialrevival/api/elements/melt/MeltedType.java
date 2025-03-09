package org.irmc.industrialrevival.api.elements.melt;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public abstract class MeltedType {
    @NotNull
    public abstract NamespacedKey getIdentifier();
    @NotNull
    public abstract Component getName();
    @NotNull
    public abstract Component getMeltedName();
    @NotNull
    public abstract TextColor getColor();
}
