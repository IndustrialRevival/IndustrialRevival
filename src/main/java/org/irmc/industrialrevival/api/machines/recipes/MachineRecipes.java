package org.irmc.industrialrevival.api.machines.recipes;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.pigeonlib.items.ItemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Machine recipes are unordered by default.
 *
 * @author balugaq
 */
@Getter
public class MachineRecipes {
    private final List<MachineRecipe> recipes = new ArrayList<>();

    public MachineRecipes() {
    }

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

    public void addRecipe(int processTime, int energyCost, ItemStack[] input, ItemStack[] output) {
        final Map<ItemStack, Integer> inputsMap = new HashMap<>();
        for (ItemStack inputItem : input) {
            inputsMap.put(ItemUtils.cloneItem(inputItem, 1), inputItem.getAmount());
        }
        final Map<ItemStack, Integer> outputsMap = new HashMap<>();
        for (ItemStack outputItem : output) {
            outputsMap.put(ItemUtils.cloneItem(outputItem, 1), outputItem.getAmount());
        }
        recipes.add(new MachineRecipe(processTime, energyCost, inputsMap, outputsMap));
    }

    public void addRecipe(int processTime, int energyCost, List<ItemStack> inputs, List<ItemStack> outputs) {
        final Map<ItemStack, Integer> inputsMap = new HashMap<>();
        for (ItemStack input : inputs) {
            inputsMap.put(ItemUtils.cloneItem(input, 1), input.getAmount());
        }
        final Map<ItemStack, Integer> outputsMap = new HashMap<>();
        for (ItemStack output : outputs) {
            outputsMap.put(ItemUtils.cloneItem(output, 1), output.getAmount());
        }
        recipes.add(new MachineRecipe(processTime, energyCost, inputsMap, outputsMap));
    }
}
