package org.irmc.industrialrevival.core.implemention.recipes;

import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeContents {
    private static final Map<String, List<RecipeContent>> recipeContents = new HashMap<>();

    public static void addRecipeContent(String itemId, RecipeContent content) {
        recipeContents.computeIfAbsent(itemId, k -> new ArrayList<>()).add(content);
    }

    @NotNull
    public static List<RecipeContent> getRecipeContents(String itemId) {
        return recipeContents.getOrDefault(itemId, new ArrayList<>()).stream().filter(i -> i.recipeType() != RecipeType.NULL).toList();
    }
}
