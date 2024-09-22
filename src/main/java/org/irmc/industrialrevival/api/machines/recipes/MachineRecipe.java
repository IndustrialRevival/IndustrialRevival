package org.irmc.industrialrevival.api.machines.recipes;

import java.util.Map;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public record MachineRecipe(
        int processTime, int energyCost, Map<ItemStack, Integer> inputs, Map<ItemStack, Integer> outputs) {

    public boolean isMatch(Map<ItemStack, Integer> items) {
        for (ItemStack item : inputs.keySet()) {
            if (!items.containsKey(item) || items.get(item) < inputs.get(item)) {
                return false;
            }
        }
        return true;
    }
}
