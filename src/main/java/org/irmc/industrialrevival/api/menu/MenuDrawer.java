package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Range;

import javax.annotation.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MenuDrawer {
    private final int size;
    private final Map<Character, ItemStack> charMap = new HashMap<>();
    private final List<String> matrix = new ArrayList<>();

    public MenuDrawer(@Range(from = 0, to = 54) int size) {
        this.size = size;
    }

    public MenuDrawer addLine(String line) {
        matrix.add(line);
        return this;
    }

    public MenuDrawer addExplain(char c, @NotNull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    public MenuDrawer addExplain(String c, @NotNull ItemStack itemStack) {
        return addExplain(c.charAt(0), itemStack);
    }

    public int[] getCharPositions(char c) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            String line = matrix.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == c) {
                    positions.add(i * 9 + j);
                }
            }
        }

        int[] result = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            result[i] = positions.get(i);
        }

        return result;
    }

    public int[] getCharPositions(String c) {
        return getCharPositions(c.charAt(0));
    }
}
