package org.irmc.industrialrevival.api.recipes;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.utils.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class RecipeContents {
    private static final Map<NamespacedKey, List<RecipeContent>> recipeContents = new HashMap<>();

    public static void addRecipeContent(NamespacedKey itemId, RecipeContent content) {
        Debug.log(itemId.toString() + ": " + content);
        var e = recipeContents.get(itemId);
        if (e != null) {
            e.add(content);
        } else {
            List<RecipeContent> list = new ArrayList<>();
            list.add(content);
            recipeContents.put(itemId, list);
        }
    }

    @NotNull
    public static List<RecipeContent> getRecipeContents(NamespacedKey itemId) {
        return recipeContents.getOrDefault(itemId, new ArrayList<>()).stream()
                .filter(i -> i.recipeType() != RecipeType.NULL)
                .toList();
    }

    @Unmodifiable
    public static Map<NamespacedKey, List<RecipeContent>> getRecipeContents() {
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
