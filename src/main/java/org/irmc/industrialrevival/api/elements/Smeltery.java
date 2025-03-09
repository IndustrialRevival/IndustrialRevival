package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.melt.MeltedTank;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.TankFuel;
import org.irmc.industrialrevival.api.recipes.MeltMethod;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class is an instance of a smeltery.
 * Should use it like this:
 * Smeltery smeltery = new Smeltery();
 * smeltery.tick(); // to update the smeltery / fuse alloys
 * smeltery.getTank().getContents(); // to get the contents of the tank
 * smeltery.getRecipes(); // to get the list of recipes
 * smeltery.clone(); // to create a copy of the smeltery
 */
@Getter
public class Smeltery implements Cloneable {
    public static final Map<Material, Integer> fuelsMap = new HashMap<>();
    static {
        fuelsMap.put(Material.LAVA_BUCKET, 10000);
    }
    public static final int MAX_FUEL_CAPACITY = 40000;
    private final List<MeltMethod> recipes;
    private final MeltedTank tank;
    public Smeltery() {
        this.tank = new MeltedTank();
        this.recipes = IndustrialRevival.getInstance().getRegistry().getMeltMethods().stream().toList();
    }
    public Smeltery(MeltedTank tank) {
        this.tank = tank;
        this.recipes = IndustrialRevival.getInstance().getRegistry().getMeltMethods().stream().toList();
    }
    public Smeltery(MeltedTank tank, List<MeltMethod> recipes) {
        this.tank = tank;
        this.recipes = recipes;
    }

    public @NotNull Smeltery clone() {
        return new Smeltery(tank.clone(), new ArrayList<>(recipes));
    }

    public void tick() {
        for (MeltMethod recipe : recipes) {
            tank.performRecipe(recipe);
        }
    }

    public static boolean isFuel(@NotNull ItemStack itemStack) {
        for (Material material : fuelsMap.keySet()) {
            if (ItemUtils.isItemSimilar(itemStack, new ItemStack(material))) {
                return true;
            }
        }

        return IndustrialRevivalItem.getByItem(itemStack) instanceof TankFuel;
    }

    public static int getFuelAmount(@NotNull ItemStack itemStack) {
        for (Map.Entry<Material, Integer> entry : fuelsMap.entrySet()) {
            if (ItemUtils.isItemSimilar(itemStack, new ItemStack(entry.getKey()))) {
                return entry.getValue();
            }
        }

        if (IndustrialRevivalItem.getByItem(itemStack) instanceof TankFuel tankFuel) {
            return tankFuel.getFuelAmount();
        }

        return 0;
    }
}
