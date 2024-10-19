package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MatrixMenuDrawer {
    private final int size;
    private final Map<Character, ItemStack> charMap = new HashMap<>();
    private final Map<Character, SimpleMenu.ClickHandler> clickHandlerMap = new HashMap<>();
    private final List<String> matrix = new ArrayList<>();

    public MatrixMenuDrawer(int size) {
        this.size = size;
    }

    public MatrixMenuDrawer addLine(String line) {
        matrix.add(line);
        return this;
    }

    public MatrixMenuDrawer addExplain(char c, @Nonnull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    public MatrixMenuDrawer addExplain(char c, @Nonnull ItemStack itemStack, @Nonnull SimpleMenu.ClickHandler clickHandler) {
        charMap.put(c, itemStack);
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    public MatrixMenuDrawer addExplain(String c, @Nonnull ItemStack itemStack) {
        charMap.put(c.charAt(0), new ItemStack(itemStack));
        return this;
    }

    public MatrixMenuDrawer addExplain(String c, @Nonnull ItemStack itemStack, @Nonnull SimpleMenu.ClickHandler clickHandler) {
        charMap.put(c.charAt(0), itemStack);
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
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
