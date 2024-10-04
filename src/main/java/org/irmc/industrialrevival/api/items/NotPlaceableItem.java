package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public class NotPlaceableItem extends IndustrialRevivalItem implements NotPlaceable {}
