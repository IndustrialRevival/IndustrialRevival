package org.irmc.industrialrevival.api.machines.recipes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Machine recipes are unordered by default.
 */
@Getter
public class MachineRecipes {
    private final Set<MachineRecipe> recipes = new HashSet<>();

    public MachineRecipes() {}

    public MachineRecipe findNextRecipe(List<ItemStack> items) {
        Map<ItemStack, Integer> itemsMap = new HashMap<>();
        for (ItemStack item : items) {
            itemsMap.put(item, item.getAmount());
        }
        return findNextRecipe(itemsMap);
    }

    public MachineRecipe findNextRecipe(Map<ItemStack, Integer> items) {
        for (MachineRecipe recipe : recipes) {
            if (recipe.isMatch(items)) {
                return recipe;
            }
        }
        return null;
    }

    public void addAll(MachineRecipes recipes) {
        this.recipes.addAll(recipes.recipes);
    }

    public void addAll(List<MachineRecipe> recipes) {
        this.recipes.addAll(recipes);
    }

    public void addRecipe(MachineRecipe recipe) {
        recipes.add(recipe);
    }

    public void addRecipe(int processTime, int energyCost, List<ItemStack> inputs, List<ItemStack> outputs) {
        final Map<ItemStack, Integer> inputsMap = new HashMap<>();
        for (ItemStack input : inputs) {
            inputsMap.put(input, input.getAmount());
        }
        final Map<ItemStack, Integer> outputsMap = new HashMap<>();
        for (ItemStack output : outputs) {
            outputsMap.put(output, output.getAmount());
        }
        recipes.add(new MachineRecipe(processTime, energyCost, inputsMap, outputsMap));
    }
}
