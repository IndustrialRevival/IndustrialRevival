package org.irmc.industrialrevival.api.machines.recipes;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.ItemStackReference;

import javax.annotation.Nonnull;

@Getter
@SuppressWarnings("unused")
public class MachineRecipe {
    private final int processTime;
    private final int energyCost;
    private final Map<ItemStackReference, Integer> inputs;
    private final Map<ItemStackReference, Integer> outputs;

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull Map<ItemStack, Integer> inputs,
            @Nonnull Map<ItemStack, Integer> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(inputs);
        this.outputs = toMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull List<ItemStack> inputs,
            @Nonnull List<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(inputs);
        this.outputs = toMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull Iterable<ItemStack> inputs,
            @Nonnull Iterable<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(inputs);
        this.outputs = toMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull Collection<ItemStack> inputs,
            @Nonnull Collection<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(inputs);
        this.outputs = toMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull ItemStack input,
            @Nonnull ItemStack output
    ) {
        Preconditions.checkArgument(input != null, "input cannot be null");
        Preconditions.checkArgument(output != null, "output cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(input);
        this.outputs = toMap(output);
    }

    public MachineRecipe(
            int processTime,
            int energyCost,
            @Nonnull ItemStackReference input,
            @Nonnull ItemStackReference output
    ) {
        Preconditions.checkArgument(input != null, "input cannot be null");
        Preconditions.checkArgument(output != null, "output cannot be null");
        this.processTime = processTime;
        this.energyCost = energyCost;
        this.inputs = toMap(input);
        this.outputs = toMap(output);
    }

    public boolean isMatch(Map<ItemStack, Integer> items){
        for (ItemStackReference itemStackReference : inputs.keySet()) {
            if (itemStackReference.getReferenceType() == ItemStackReference.ReferenceType.DICTIONARY) {
                boolean found = false;
                for (ItemStack incoming : items.keySet()) {
                    if (itemStackReference.itemsMatch(incoming)) {
                        if (items.get(incoming) < inputs.get(itemStackReference)) {
                            return false;
                        }

                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            } else {
                for (ItemStack incoming : items.keySet()) {
                    if (itemStackReference.itemsMatch(incoming)) {
                        if (items.get(incoming) < inputs.get(itemStackReference)) {
                            return false;
                        }

                        break;
                    }
                }
            }
        }
        return true;
    }
    private static Map<ItemStackReference, Integer> toMap(Map<ItemStack, Integer> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items.keySet()) {
            map.put(new ItemStackReference(itemStack), items.get(itemStack));
        }
        return map;
    }
    private static Map<ItemStackReference, Integer> toMap(List<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toMap(Iterable<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toMap(Collection<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toMap(ItemStack itemStack) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        return map;
    }

    private static Map<ItemStackReference, Integer> toMap(ItemStackReference itemStackReference) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        map.put(itemStackReference, 1);
        return map;
    }
}