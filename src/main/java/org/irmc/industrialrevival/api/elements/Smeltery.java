package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.melt.MeltedTank;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.TankFuel;
import org.irmc.industrialrevival.api.recipes.methods.MeltMethod;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an instance of a smeltery.
 *
 * @author balugaq
 * @since 1.0
 */
@Getter
public class Smeltery implements Cloneable {
    public static final int MAX_FUEL = 4000;
    public static final int MAX_CAPACITY = 40000;
    private static final Map<Material, Integer> FUELS_MAP = new HashMap<>();

    static {
        FUELS_MAP.put(Material.LAVA_BUCKET, 1000);
    }

    private final List<MeltMethod> recipes;
    private final MeltedTank tank;

    public Smeltery() {
        this.tank = new MeltedTank();
        this.recipes = IRDock.getPlugin().getRegistry().getMeltMethods().stream().toList();
    }

    public Smeltery(MeltedTank tank) {
        this.tank = tank;
        this.recipes = IRDock.getPlugin().getRegistry().getMeltMethods().stream().toList();
    }

    public Smeltery(MeltedTank tank, List<MeltMethod> recipes) {
        this.tank = tank;
        this.recipes = recipes;
    }

    public static Map<Material, Integer> getFuelsMap() {
        return FUELS_MAP;
    }

    public static boolean isFuel(@NotNull ItemStack itemStack) {
        for (Material material : FUELS_MAP.keySet()) {
            if (ItemUtils.isItemSimilar(itemStack, new ItemStack(material))) {
                return true;
            }
        }

        return IndustrialRevivalItem.getByItem(itemStack) instanceof TankFuel;
    }

    public static int getFuelAmount(@NotNull ItemStack itemStack) {
        for (Map.Entry<Material, Integer> entry : FUELS_MAP.entrySet()) {
            if (ItemUtils.isItemSimilar(itemStack, new ItemStack(entry.getKey()))) {
                return entry.getValue();
            }
        }

        if (IndustrialRevivalItem.getByItem(itemStack) instanceof TankFuel tankFuel) {
            return tankFuel.getFuelAmount();
        }

        return 0;
    }

    public @NotNull Smeltery clone() {
        return new Smeltery(tank.clone(), new ArrayList<>(recipes));
    }

    public void tick() {
        for (MeltMethod recipe : recipes) {
            tank.performRecipe(recipe);
        }
    }
}
