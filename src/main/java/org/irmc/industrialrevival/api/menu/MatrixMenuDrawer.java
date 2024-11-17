package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides an easier way to create a menu by using a matrix of characters and items.
 * It allows adding click handlers to the items, which can be used to create interactive menus.
 * Use the {@link MachineMenuPreset#addMenuDrawer(MatrixMenuDrawer)} method within
 * the {@link MachineMenuPreset#init()} method to process the menu drawer configuration.
 * <p>
 * Example usage:
 * <pre>
 * MatrixMenuDrawer drawer = new MatrixMenuDrawer(45)
 *     .addLine("BBBBBBBBB")
 *     .addLine("IIIIBOOOO")
 *     .addLine("IiiISOooO")
 *     .addLine("IIIIBOOOO")
 *     .addLine("BBBBBBBBB")
 *     .addExplain('B', MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
 *     .addExplain('I', MenuUtil.INPUT_BORDER)
 *     .addExplain('O', MenuUtil.OUTPUT_BORDER);
 *
 * new MachineMenuPreset() {
 *     public void init() {
 *         addMenuDrawer(drawer);
 *     }
 * }
 * </pre>
 *
 * In this example, a 45-size matrix is created with 5 lines of characters. When calling
 * {@link MachineMenuPreset#addMenuDrawer(MatrixMenuDrawer)} in the {@link MachineMenuPreset#init()}
 * method, characters 'B', 'I', 'O' are replaced with corresponding item stacks, and click handlers
 * are added to the menu. Note that if an item stack is considered a background item by
 * {@link MenuUtil#isBackground(ItemStack)}, its click handler will be overridden by the default click handler.
 *
 * @author balugaq
 * @author lijinhong11
 */
@SuppressWarnings("unused")
@Getter
public class MatrixMenuDrawer implements Cloneable {
    private final int size;
    private final Map<Character, ItemStack> charMap = new HashMap<>();
    private final Map<Character, SimpleMenu.ClickHandler> clickHandlerMap = new HashMap<>();
    private final List<String> matrix = new ArrayList<>();

    public MatrixMenuDrawer(@Range(from = 1, to = 54) int size) {
        this.size = size;
    }
    public MatrixMenuDrawer addLine(@NotNull String line) {
        matrix.add(line);
        return this;
    }

    public MatrixMenuDrawer addExplain(char c, @NotNull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    public MatrixMenuDrawer addExplain(char c, @NotNull ItemStack itemStack, @NotNull SimpleMenu.ClickHandler clickHandler) {
        charMap.put(c, itemStack);
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    public MatrixMenuDrawer addBackground(char c, @NotNull SimpleMenu.ClickHandler clickHandler) {
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    public MatrixMenuDrawer addExplain(@NotNull String c, @NotNull ItemStack itemStack) {
        charMap.put(c.charAt(0), new ItemStack(itemStack));
        return this;
    }

    public MatrixMenuDrawer addExplain(@NotNull String c, @NotNull ItemStack itemStack, @NotNull SimpleMenu.ClickHandler clickHandler) {
        charMap.put(c.charAt(0), itemStack);
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }

    public MatrixMenuDrawer addBackground(@NotNull String c, @NotNull SimpleMenu.ClickHandler clickHandler) {
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }

    public int[] getCharPositions(@NotNull String s) {
        return getCharPositions(s.charAt(0));
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

    public MatrixMenuDrawer clone() {
        MatrixMenuDrawer drawer = new MatrixMenuDrawer(size);
        drawer.charMap.putAll(charMap);
        drawer.clickHandlerMap.putAll(clickHandlerMap);
        drawer.matrix.addAll(matrix);
        return drawer;
    }
}
