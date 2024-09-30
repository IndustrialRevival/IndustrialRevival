package org.irmc.industrialrevival.api.machines.process;

import java.util.Map;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;

public class MachineOperation implements IOperation {
    private @Getter final Map<ItemStack, Integer> inputStacks;
    private @Getter final Map<ItemStack, Integer> outputStacks;
    private final int duration;
    private int currentProgress = 0;

    public MachineOperation(Map<ItemStack, Integer> inputStacks, Map<ItemStack, Integer> outputStacks, int duration) {
        this.inputStacks = inputStacks;
        this.outputStacks = outputStacks;
        this.duration = duration;
    }

    public MachineOperation(MachineRecipe recipe) {
        this(recipe.inputs(), recipe.outputs(), recipe.processTime());
    }

    @Override
    public void tick() {
        addProgress(1);
    }

    @Override
    public void addProgress(int progress) {
        currentProgress += progress;
    }

    @Override
    public int getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public int getTotalProgress() {
        return duration;
    }
}
