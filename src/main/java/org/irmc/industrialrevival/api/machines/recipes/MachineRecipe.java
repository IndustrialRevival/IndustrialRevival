package org.irmc.industrialrevival.api.machines.recipes;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class MachineRecipe {
    private @Getter
    final int processTime;
    private @Getter
    final int energyCost;
    private @Getter
    final Map<ItemStack, Integer> inputs;
    private @Getter
    final Map<ItemStack, Integer> outputs;

    public MachineRecipe(
            int processTime, int energyCost, Map<ItemStack, Integer> inputs, Map<ItemStack, Integer> outputs) {
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public boolean isMatch(Map<ItemStack, Integer> items) {
        for (ItemStack item : inputs.keySet()) {
            if (!items.containsKey(item) || items.get(item) < inputs.get(item)) {
                return false;
            }
        }
        return true;
    }
}
