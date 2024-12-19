package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import org.bukkit.Material;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.MeltMethod;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is an instance of a smeltery.
 * Should use it like this:
 * Smeltery smeltery = new Smeltery();
 * smeltery.tick(); // to update the smeltery
 * smeltery.getTank().getContents(); // to get the contents of the tank
 * smeltery.getRecipes(); // to get the list of recipes
 * smeltery.clone(); // to create a copy of the smeltery
 */
@Getter
public class Smeltery implements Cloneable {
    private final List<MeltMethod> recipes;
    private final MeltedTank tank;
    public Smeltery() {
        this.tank = new MeltedTank();
        this.recipes = new ArrayList<>();
        // todo: ^ IndustrialRevival.getInstance().getRegistry().getMeltMethods();
    }
    public Smeltery(MeltedTank tank) {
        this.tank = tank;
        this.recipes = new ArrayList<>();
        // todo: ^ same as above
    }
    public Smeltery(MeltedTank tank, List<MeltMethod> recipes) {
        this.tank = tank;
        this.recipes = recipes;
    }

    public Smeltery clone() {
        return new Smeltery(tank.clone(), new ArrayList<>(recipes));
    }

    protected void tick() {
        for (MeltMethod recipe : recipes) {
            tank.performRecipe(recipe);
        }
    }
}
