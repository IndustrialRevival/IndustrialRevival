package org.irmc.industrialrevival.core.implemention.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeContents {
    private static final Map<String, List<RecipeContent>> recipeContents = new HashMap<>();

    public static void addRecipeContent(String itemId, RecipeContent content) {
        recipeContents.computeIfAbsent(itemId, k -> new ArrayList<>()).add(content);
    }

    public static List<RecipeContent> getRecipeContents(String itemId) {
        return recipeContents.get(itemId);
    }
}
