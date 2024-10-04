package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.CleanedItemGetter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Abstract class for all IndustrialRevival machines.
 */
public abstract class AbstractMachine extends IndustrialRevivalItem {
    protected final MachineRecipes machineRecipes = new MachineRecipes();
    @Getter
    private RecipeType recipeType = null;
    private ItemStack recipeTypeIcon = null;

    public void addRecipe(int processTime, int energyCost, ItemStack[] consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energyCost, consume, produce);
    }

    public void addRecipe(int processTime, int energyCost, ItemStack[] consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energyCost, Arrays.asList(consume), Collections.singletonList(produce));
    }

    public void addRecipe(int processTime, int energyCost, ItemStack consume, ItemStack produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energyCost, Collections.singletonList(consume), Collections.singletonList(produce));
    }

    public void addRecipe(int processTime, int energyCost, ItemStack consume, ItemStack[] produce) {
        checkRegistered();
        machineRecipes.addRecipe(processTime, energyCost, Collections.singletonList(consume), Arrays.asList(produce));
    }

    public void addRecipe(MachineRecipe recipe) {
        checkRegistered();
        machineRecipes.addRecipe(recipe);
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

    private ItemStack getRecipeTypeIcon() {
        if (recipeTypeIcon == null) {
            return CleanedItemGetter.getCleanedItem(getItem());
        }

        return CleanedItemGetter.getCleanedItem(recipeTypeIcon);
    }

    public AbstractMachine setRecipeTypeIcon(ItemStack recipeTypeIcon) {
        checkRegistered();
        this.recipeTypeIcon = recipeTypeIcon;
        this.recipeType = new RecipeType(new NamespacedKey(getAddon().getPlugin(), getId().toLowerCase()), getRecipeTypeIcon());
        return this;
    }

    @Override
    public IndustrialRevivalItem setItemStack(@Nonnull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        if (recipeTypeIcon == null) {
            this.recipeTypeIcon = CleanedItemGetter.getCleanedItem(itemStack);
            this.recipeType = new RecipeType(new NamespacedKey(getAddon().getPlugin(), getId().toLowerCase()), getRecipeTypeIcon());
        }
        return this;
    }
}
