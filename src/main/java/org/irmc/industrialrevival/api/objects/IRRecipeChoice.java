package org.irmc.industrialrevival.api.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

public class IRRecipeChoice extends RecipeChoice.ExactChoice {
    public IRRecipeChoice(@NotNull ItemStack stack) {
        this(List.of(stack));
    }

    public IRRecipeChoice(ItemStack... stacks) {
        this(Arrays.asList(stacks));
    }

    public IRRecipeChoice(@NotNull List<ItemStack> choices) {
        super(choices);
    }

    public boolean test(@NotNull ItemStack t) {
        Iterator<ItemStack> var2 = getChoices().iterator();

        ItemStack match;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            match = var2.next();
        } while (!ItemUtils.isItemSimilar(t, match, true, true));

        return true;
    }
}
