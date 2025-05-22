package org.irmc.industrialrevival.implementation.items.register;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.irmc.industrialrevival.api.elements.registry.ChemicalFormulas;
import org.irmc.industrialrevival.api.recipes.methods.ChemicalMethod;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.items.chemistry.Solution;
import org.irmc.pigeonlib.items.CustomItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChemicalCompoundSetup {
    public static final Map<String, Solution> solutions = new HashMap<>();

    public static void setup() {
        ChemicalCompounds.onLoad(() -> {
            IndustrialRevival.getInstance().getFoliaLibImpl().runAsync(_ -> {
                ChemicalCompound.ALL_CHEMICALS.forEach(chemicalCompound -> {
                    var item = new Solution()
                            .addon(IndustrialRevival.getInstance())
                            .id("CHEMICAL_COMPOUND_" + chemicalCompound.asKey())
                            .icon(new CustomItemStack(
                                    Material.GLASS_BOTTLE,
                                    Component.empty().append(Component.translatable("chemistry.solution." + chemicalCompound.getName()).append(
                                            Component.translatable("chemistry.solution.bottle")))
                            ))
                            .cast(Solution.class);
                    // Rewrite this
                    item.registerReactable();
                    item.register();
                    item.bind(chemicalCompound);
                    // Rewrite this
                    solutions.put(chemicalCompound.getName(), item);
                });

                IRRegistry.getInstance().getChemicalFormulas().values().forEach(formula ->
                        formula.getOutput().keySet().forEach(compound ->
                                solutions.get(compound.getName()).recipe(new ChemicalMethod(formula))
                        )
                );
            });
        });
        ChemicalCompounds.onLoad(ChemicalFormulas::setup);

        ChemicalCompounds.load();
    }
}
