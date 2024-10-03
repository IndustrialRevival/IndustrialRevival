package org.irmc.industrialrevival.api.recipes;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

@UtilityClass
public class RecipeContents {
    private static final Map<String, List<RecipeContent>> recipeContents = new HashMap<>();

    public static void addRecipeContent(String itemId, RecipeContent content) {
        recipeContents.computeIfAbsent(itemId, k -> new ArrayList<>()).add(content);
    }

    @NotNull public static List<RecipeContent> getRecipeContents(String itemId) {
        return recipeContents.getOrDefault(itemId, new ArrayList<>()).stream()
                .filter(i -> i.recipeType() != RecipeType.NULL)
                .toList();
    }

    @Unmodifiable
    public static Map<String, List<RecipeContent>> getRecipeContents() {
        return Object2ObjectMaps.unmodifiable(new Object2ObjectArrayMap<>(recipeContents));
    }

    @Unmodifiable
    public static List<RecipeContent> getRecipeContentsByRecipeType(RecipeType recipeType) {
        return recipeContents.values().stream()
                .flatMap(List::stream)
                .filter(i -> i.recipeType() == recipeType)
                .toList();
    }
}
