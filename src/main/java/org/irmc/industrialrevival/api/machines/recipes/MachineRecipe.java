package org.irmc.industrialrevival.api.machines.recipes;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.ItemStackReference;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@SuppressWarnings("unused")
public class MachineRecipe {
    private final int processTime;
    private final int energy;
    private final Map<ItemStackReference, Integer> inputs;
    private final Map<ItemStack, Integer> outputs;

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull Map<ItemStack, Integer> inputs,
            @Nonnull Map<ItemStack, Integer> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(inputs);
        this.outputs = outputs;
    }

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull List<ItemStack> inputs,
            @Nonnull List<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(inputs);
        this.outputs = toStackMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull Iterable<ItemStack> inputs,
            @Nonnull Iterable<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(inputs);
        this.outputs = toStackMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull Collection<ItemStack> inputs,
            @Nonnull Collection<ItemStack> outputs
    ) {
        Preconditions.checkArgument(inputs != null, "inputs cannot be null");
        Preconditions.checkArgument(outputs != null, "outputs cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(inputs);
        this.outputs = toStackMap(outputs);
    }

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull ItemStack input,
            @Nonnull ItemStack output
    ) {
        Preconditions.checkArgument(input != null, "input cannot be null");
        Preconditions.checkArgument(output != null, "output cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(input);
        this.outputs = toStackMap(output);
    }

    public MachineRecipe(
            int processTime,
            int energy,
            @Nonnull ItemStackReference input,
            @Nonnull ItemStackReference output
    ) {
        Preconditions.checkArgument(input != null, "input cannot be null");
        Preconditions.checkArgument(output != null, "output cannot be null");
        this.processTime = processTime;
        this.energy = energy;
        this.inputs = toRefMap(input);
        this.outputs = toStackMap(output);
    }

    private static Map<ItemStackReference, Integer> toRefMap(Map<ItemStack, Integer> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items.keySet()) {
            map.put(new ItemStackReference(itemStack), items.get(itemStack));
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toRefMap(List<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toRefMap(Iterable<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toRefMap(Collection<ItemStack> items) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStackReference, Integer> toRefMap(ItemStack itemStack) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        map.put(new ItemStackReference(itemStack), itemStack.getAmount());
        return map;
    }

    private static Map<ItemStackReference, Integer> toRefMap(ItemStackReference itemStackReference) {
        Map<ItemStackReference, Integer> map = new HashMap<>();
        map.put(itemStackReference, 1);
        return map;
    }

    private static Map<ItemStack, Integer> toStackMap(List<ItemStack> items) {
        Map<ItemStack, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStack(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStack, Integer> toStackMap(Iterable<ItemStack> items) {
        Map<ItemStack, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStack(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStack, Integer> toStackMap(Collection<ItemStack> items) {
        Map<ItemStack, Integer> map = new HashMap<>();
        for (ItemStack itemStack : items) {
            map.put(new ItemStack(itemStack), itemStack.getAmount());
        }
        return map;
    }

    private static Map<ItemStack, Integer> toStackMap(ItemStackReference itemStackReference) {
        Map<ItemStack, Integer> map = new HashMap<>();
        map.put(new ItemStack(itemStackReference.getItemStack()), 1);
        return map;
    }

    private static Map<ItemStack, Integer> toStackMap(ItemStack itemStack) {
        Map<ItemStack, Integer> map = new HashMap<>();
        map.put(new ItemStack(itemStack), itemStack.getAmount());
        return map;
    }

    public boolean isMatch(Map<ItemStack, Integer> items) {
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

}