package org.irmc.industrialrevival.implementation.items.collection;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.Element;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class OreDusts {
    public static final IndustrialRevivalItem ALUMINUM_DUST = new OreDust(ElementType.ALUMINIUM,
            new ChemicalCompound(Component.text("Al"), Map.of(new Element(ElementType.ALUMINIUM), 1D))
    )
            .setAddon(IndustrialRevival.getInstance())
            .addItemGroup(IRItemGroups.ORES);

    public static class OreDust extends IndustrialRevivalItem implements ChemReactable {
        private final ChemicalCompound chemicalCompound;
        private final ElementType elementType;

        OreDust(ElementType elementType, ChemicalCompound chemicalCompound) {
            this.elementType = elementType;
            this.chemicalCompound = chemicalCompound;

            setAddon(IndustrialRevival.getInstance());
            addItemGroup(IRItemGroups.ORES);
        }

        @Override
        public @NotNull ChemicalCompound getChemicalCompound(@NotNull ItemStack itemStack) {
            return chemicalCompound;
        }

        @Override
        public int getMass(@NotNull ItemStack itemStack) {
            return elementType.getMassByRelativeAtomicMass(itemStack.getAmount() * 0.1);
        }

        @Override
        public @NotNull ReactResult react(@NotNull ItemStack item, @NotNull ReactCondition[] conditions, @NotNull ChemReactable... other) {
            //TODO: reaction
            if (other.length == 0) {
                //Default O2 reaction
                return ReactResult.FAILED;
            }

            return ReactResult.FAILED;
        }
    }
}