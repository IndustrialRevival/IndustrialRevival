package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.melt.MeltedObject;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.multiblock.BlastSmeltery;

import java.util.List;

/**
 * @author baluagq
 */
@Getter
public class MeltMethod implements ProduceMethod {
    private final List<MeltedObject> inputs;
    private final List<MeltedObject> outputs;

    public MeltMethod(List<MeltedObject> inputs, List<MeltedObject> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public MeltMethod(MeltedObject input, MeltedObject output) {
        this.inputs = List.of(input);
        this.outputs = List.of(output);
    }

    public static MeltMethod of(MeltedObject input, MeltedObject output) {
        return new MeltMethod(input, output);
    }

    public static MeltMethod of(List<MeltedObject> inputs, List<MeltedObject> outputs) {
        return new MeltMethod(inputs, outputs);
    }

    @Override
    public RecipeType getRecipeType() {
        return BlastSmeltery.RECIPE_TYPE;
    }

    @Override
    public ItemStack[] getIngredients() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack[] getOutput() {
        return new ItemStack[0];
    }
}
