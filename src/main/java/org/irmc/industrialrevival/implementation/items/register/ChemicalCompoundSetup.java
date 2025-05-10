package org.irmc.industrialrevival.implementation.items.register;

import com.tcoded.folialib.FoliaLib;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.recipes.methods.ChemicalMethod;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItemSetup;
import org.irmc.industrialrevival.implementation.items.chemistry.Solution;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.CustomItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChemicalCompoundSetup {
    public static final Map<ChemicalCompound, Solution> solutions = new HashMap<>();
    public static void setup() {
        IndustrialRevival.getInstance().getFoliaLibImpl().runAsync(_ -> {
            ChemicalCompound.ALL_CHEMICALS.forEach(chemicalCompound -> {
                var item = new Solution()
                        .addon(IndustrialRevival.getInstance())
                        .id("CHEMICAL_COMPOUND_" + chemicalCompound.getName())
                        .icon(new CustomItemStack(
                                Material.GLASS_BOTTLE,
                                Component.translatable("chemistry.solution." + chemicalCompound.getName()).append(
                                        Component.translatable("chemistry.solution.bottle"))
                        ))
                        .cast(Solution.class);
                item.registerReactable();
                item.register();
                item.bind(chemicalCompound);
                solutions.put(chemicalCompound, item);
            });

            IRRegistry.getInstance().getChemicalFormulas().values().forEach(formula ->
                    formula.getOutput().keySet().forEach(compound ->
                            solutions.get(compound).recipe(new ChemicalMethod(formula))
                    )
            );
        });
    }
}
