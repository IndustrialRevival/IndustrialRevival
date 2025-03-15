package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.RecipeTypeLike;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.CleanedItemGetter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Abstract class for all IndustrialRevival machines.
 */
public abstract class AbstractMachine extends IndustrialRevivalItem implements RecipeTypeLike {
    protected final MachineRecipes machineRecipes = new MachineRecipes();
    @Getter
    private RecipeType recipeType = null;
    private ItemStack recipeTypeIcon = null;

    public AbstractMachine addRecipe(int processTime, int energy, ItemStack[] consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, consume, produce);
        return this;
    }

    public AbstractMachine addRecipe(int processTime, int energy, ItemStack[] consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Arrays.asList(consume), Collections.singletonList(produce));
        return this;
    }

    public AbstractMachine addRecipe(int processTime, int energy, ItemStack consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Collections.singletonList(consume), Collections.singletonList(produce));
        return this;
    }

    public AbstractMachine addRecipe(int processTime, int energy, ItemStack consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energy, Collections.singletonList(consume), Arrays.asList(produce));
        return this;
    }

    public AbstractMachine addRecipe(MachineRecipe recipe) {
        checkRegistered();
        machineRecipes.addRecipe(recipe);
        return this;
    }

    public Map<ItemStackReference, Integer> findInputByOutput(ItemStack output) {
        MachineRecipe foundRecipe = null;
        for (MachineRecipe recipe : machineRecipes.getRecipes()) {
            if (recipe.getOutputs().size() == 1 && recipe.getOutputs().containsKey(output)) {
                foundRecipe = recipe;
                break;
            }
        }

        if (foundRecipe == null) {
            for (MachineRecipe recipe : machineRecipes.getRecipes()) {
                if (recipe.getOutputs().containsKey(output)) {
                    foundRecipe = recipe;
                    break;
                }
            }
        }

        if (foundRecipe == null) {
            return null;
        }

        return foundRecipe.getInputs();
    }

    @Override
    public void postRegister() {
        super.postRegister();
    }

    public ItemStack getRecipeTypeIcon() {
        if (recipeTypeIcon == null) {
            return CleanedItemGetter.getCleanedItem(getItem().getItemStack());
        }

        return CleanedItemGetter.getCleanedItem(recipeTypeIcon);
    }

    public AbstractMachine setRecipeTypeIcon(ItemStack recipeTypeIcon) {
        checkRegistered();
        this.recipeTypeIcon = recipeTypeIcon;
        this.recipeType = new RecipeType(getAddon(), getId(), getRecipeTypeIcon());
        return this;
    }

    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        if (recipeTypeIcon == null) {
            this.recipeTypeIcon = CleanedItemGetter.getCleanedItem(getIcon());
            this.recipeType = new RecipeType(getAddon(), getId(), getRecipeTypeIcon());
        }
    }
}
