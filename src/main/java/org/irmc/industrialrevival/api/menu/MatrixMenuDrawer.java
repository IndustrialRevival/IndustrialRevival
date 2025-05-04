package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// todo: add item flow control
/**
 * Provides a matrix-based menu creation system for defining inventory layouts using character mappings.
 * Allows creation of interactive menus by associating characters with items and click handlers.
 *
 * <p>Typical usage involves defining menu structure with character matrices, mapping characters to
 * inventory items, and attaching click handlers. The drawer can then be applied to a MachineMenuPreset
 * during initialization.</p>
 *
 * <h3>Example Usage:</h3>
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
 * </pre>
 *
 * @version 1.2
 * @author balugaq
 * @author lijinhong11
 * @see MachineMenuPreset
 * @see ClickHandler
 */
@SuppressWarnings("unused")
@Getter
public class MatrixMenuDrawer implements Cloneable {
    private final int size;
    private final Map<Character, ItemStack> charMap = new HashMap<>();
    private final Map<Character, ClickHandler> clickHandlerMap = new HashMap<>();
    private final List<String> matrix = new ArrayList<>();

    /**
     * Constructs a MatrixMenuDrawer with specified inventory size
     * @param size Valid inventory size (1-54 slots)
     */
    public MatrixMenuDrawer(@Range(from = 1, to = 54) int size) {
        this.size = size;
    }

    /**
     * Adds a line to the menu matrix
     * @param line String representing menu row (should match inventory row length)
     * @return This drawer instance for chaining
     * @throws IllegalArgumentException If line length doesn't match expected row size
     */
    public MatrixMenuDrawer addLine(@NotNull String line) {
        if (line.length() > 9 || line.isEmpty()) {
            throw new IllegalArgumentException("Line length should be between 1 (included) and 9 (included)");
        }

        matrix.add(line);
        return this;
    }


    /**
     * Maps character to menu item without click handler
     * @param c Matrix character to define
     * @param itemStack Item to display at character positions
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addExplain(char c, @NotNull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param itemStack Item to display at character positions
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(char c, @NotNull ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        charMap.put(c, itemStack);
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    /**
     * Maps character to background item with click handler
     * @param c Matrix character to define
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addBackground(char c, @NotNull ClickHandler clickHandler) {
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param itemStack Item to display at character positions
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(@NotNull String c, @NotNull ItemStack itemStack) {
        charMap.put(c.charAt(0), new ItemStack(itemStack));
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param itemStack Item to display at character positions
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(@NotNull String c, @NotNull ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        charMap.put(c.charAt(0), itemStack);
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }

    /**
     * Maps character to background item with click handler
     * @param c Matrix character to define
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addBackground(@NotNull String c, @NotNull ClickHandler clickHandler) {
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }

    /**
     * Finds all inventory slots containing specified character
     * @param s String to locate, only first character is used
     * @return Array of slot indexes (0-based) where character appears
     */
    public int[] getCharPositions(@NotNull String s) {
        return getCharPositions(s.charAt(0));
    }

    /**
     * Finds all inventory slots containing specified character
     * @param c Character to locate
     * @return Array of slot indexes (0-based) where character appears
     */
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

    /**
     * Finds the inventory slot containing specified character
     * @param c Character to locate
     * @return Slot index (0-based) where character appears, or -1 if not found
     */
    public int findFirst(char c) {
        for (int i = 0; i < matrix.size(); i++) {
            String line = matrix.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == c) {
                    return i * 9 + j;
                }
            }
        }
        return -1;
    }

    /**
     * Finds the inventory slot containing specified character
     * @param s String to locate, only first character is used
     * @return Slot index (0-based) where character appears, or -1 if not found
     */
    public int findFirst(@NotNull String s) {
        return findFirst(s.charAt(0));
    }

    /**
     * Creates a deep copy of the drawer configuration
     * @return New MatrixMenuDrawer instance with copied mappings and matrix
     */
    @Override
    public MatrixMenuDrawer clone() {
        MatrixMenuDrawer drawer = new MatrixMenuDrawer(size);
        drawer.charMap.putAll(charMap);
        drawer.clickHandlerMap.putAll(clickHandlerMap);
        drawer.matrix.addAll(matrix);
        return drawer;
    }

    /**
     * Maps character to menu item without click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param itemStack Item to display at character positions
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addExplain(char c, @Nullable String comment, @NotNull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param itemStack Item to display at character positions
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(char c, @Nullable String comment, @NotNull ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        charMap.put(c, itemStack);
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    /**
     * Maps character to background item with click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addBackground(char c, @Nullable String comment, @NotNull ClickHandler clickHandler) {
        clickHandlerMap.put(c, clickHandler);
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param itemStack Item to display at character positions
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(@NotNull String c, @Nullable String comment, @NotNull ItemStack itemStack) {
        charMap.put(c.charAt(0), new ItemStack(itemStack));
        return this;
    }

    /**
     * Maps character to menu item with click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param itemStack Item to display at character positions
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     * @apiNote Background items (as per MenuUtil.isBackground()) will override handler
     */
    public MatrixMenuDrawer addExplain(@NotNull String c, @Nullable String comment, @NotNull ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        charMap.put(c.charAt(0), itemStack);
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }

    /**
     * Maps character to background item with click handler
     * @param c Matrix character to define
     * @param comment hard coded comment for reviewers
     * @param clickHandler Click handler for the item
     * @return This drawer instance for chaining
     */
    public MatrixMenuDrawer addBackground(@NotNull String c, @Nullable String comment, @NotNull ClickHandler clickHandler) {
        clickHandlerMap.put(c.charAt(0), clickHandler);
        return this;
    }
}
