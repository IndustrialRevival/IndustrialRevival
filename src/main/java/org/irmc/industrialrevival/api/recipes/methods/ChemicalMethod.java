package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.chemistry.OperationTable;

/**
 * @author balugaq
 */
@Getter
public class ChemicalMethod implements ProduceMethod {
    private final ChemicalFormula formula;

    public ChemicalMethod(ChemicalFormula formula) {
        this.formula = formula;
    }

    @Override
    public RecipeType getRecipeType() {
        return OperationTable.getRecipeType();
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
