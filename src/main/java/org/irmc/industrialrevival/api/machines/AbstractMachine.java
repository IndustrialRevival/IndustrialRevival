package org.irmc.industrialrevival.api.machines;

import javax.annotation.Nonnull;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.utils.CleanedItemGetter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for all IndustrialRevival machines.
 */
public abstract class AbstractMachine extends IndustrialRevivalItem {
    @Getter
    private final RecipeType recipeType;
    protected final MachineRecipes machineRecipes;

    public AbstractMachine(
            @Nonnull ItemGroup group,
            @Nonnull IndustrialRevivalItemStack itemStack,
            @Nonnull RecipeType recipeType,
            @Nonnull ItemStack[] recipe,
            @Nonnull ItemStack recipeItem,
            @Nonnull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe);
        this.machineRecipes = machineRecipes;
        this.recipeType = new RecipeType(new NamespacedKey(getAddon().getPlugin(), itemStack.getId().toLowerCase()), recipeItem);
    }

    public AbstractMachine(
            @Nonnull ItemGroup group,
            @Nonnull IndustrialRevivalItemStack itemStack,
            @Nonnull RecipeType recipeType,
            @Nonnull ItemStack[] recipe,
            @Nonnull MachineRecipes machineRecipes) {
        this(group, itemStack, recipeType, recipe, CleanedItemGetter.getCleanedItem(itemStack), machineRecipes);
    }

    public void addRecipe(int processTime, int energyCost, ItemStack[] consume, ItemStack[] produce) {
        machineRecipes.addRecipe(processTime, energyCost, consume, produce);
    }

    public void addRecipe(int processTime, int energyCost, ItemStack[] consume, ItemStack produce) {
        machineRecipes.addRecipe(processTime, energyCost, Arrays.asList(consume), Collections.singletonList(produce));
    }
    public void addRecipe(int processTime, int energyCost, ItemStack consume, ItemStack produce) {
        machineRecipes.addRecipe(processTime, energyCost, Collections.singletonList(consume), Collections.singletonList(produce));
    }

    public void addRecipe(int processTime, int energyCost, ItemStack consume, ItemStack[] produce) {
        machineRecipes.addRecipe(processTime, energyCost, Collections.singletonList(consume), Arrays.asList(produce));
    }
}
