package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for advanced text formatting and color manipulation in Minecraft.
 * <p>
 * Provides methods for creating gradient text, generating random colors, and handling
 * string formatting placeholders. Includes both true-random and seed-controlled pseudorandom
 * color generation systems.
 *
 * @author Final_ROOT, balugaq
 */
@UtilityClass
public class TextUtil {
    /**
     * Default neutral text color (light gray)
     */
    public static final String COLOR_NORMAL = "§x§8§8§f§f§f§f";
    /**
     * Highlight color for stressed text (orange-red)
     */
    public static final String COLOR_STRESS = "§x§f§f§f§f§8§8";
    /**
     * Action/button highlight color (red)
     */
    public static final String COLOR_ACTION = "§x§f§f§8§8§0§0";
    /**
     * Color for initiative/active elements (cyan)
     */
    public static final String COLOR_INITIATIVE = "§x§0§0§8§8§f§f";
    /**
     * Color for passive elements (teal)
     */
    public static final String COLOR_PASSIVE = "§x§0§0§f§f§8§8";
    /**
     * Number display color (pink)
     */
    public static final String COLOR_NUMBER = "§x§f§f§8§8§f§f";
    /**
     * Positive status color (green)
     */
    public static final String COLOR_POSITIVE = "§x§8§8§f§f§8§8";
    /**
     * Negative status color (dark red)
     */
    public static final String COLOR_NEGATIVE = "§x§f§f§8§8§8§8";
    /**
     * Concealed text color (dark gray)
     */
    public static final String COLOR_CONCEAL = "§x§8§8§8§8§8§8";
    /**
     * Input field color (blue)
     */
    public static final String COLOR_INPUT = "§9";
    /**
     * Output field color (gold)
     */
    public static final String COLOR_OUTPUT = "§6";
    /**
     * Predefined white color constant
     */
    public static final Color WHITE_COLOR = Color.fromRGB(255, 255, 255);
    /**
     * Pattern matching {number} style placeholders
     */
    private static final Pattern braceLike = Pattern.compile("\\{\\d+}");
    /**
     * Pattern matching %-prefixed format specifiers
     */
    private static final Pattern percentageLike = Pattern.compile("%[dsfFeEgGcbBhHoxX\\+\\-0 ]");
    private static long COUNT = 0;

    /**
     * Applies gradient coloring to a string using linear color interpolation.
     * <p>
     * Handles string formatting placeholders ({@code {n}} and {@code %s}) by preserving
     * their formatting while applying color gradients to the surrounding text.
     *
     * @param string    The input string to colorize
     * @param colorList List of colors defining the gradient
     * @return Gradient-colored string with preserved formatting placeholders
     * @apiNote Automatically pads 1-character strings to enable gradient rendering.
     * Modifies input string length if less than 2 characters.
     * @see #colorRandomString(String)
     * @see #colorPseudorandomString(String)
     */
    @Nonnull
    public static String colorString(@Nonnull String string, @Nonnull List<Color> colorList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (string.isEmpty()) {
            string += " ";
        }
        if (string.length() == 1) {
            string += " ";
        }

        for (int i = 0, length = string.length() - 1; i <= length; i++) {
            double p = ((double) i) / length * (colorList.size() - 1);
            Color color1 = colorList.get((int) Math.floor(p));
            Color color2 = colorList.get((int) Math.ceil(p));
            int blue = (int) (color1.getBlue() * (1 - p + Math.floor(p)) + color2.getBlue() * (p - Math.floor(p)));
            int green = (int) (color1.getGreen() * (1 - p + Math.floor(p)) + color2.getGreen() * (p - Math.floor(p)));
            int red = (int) (color1.getRed() * (1 - p + Math.floor(p)) + color2.getRed() * (p - Math.floor(p)));
            stringBuilder.append("§x")
                    .append("§").append(TextUtil.codeColor(red / 16))
                    .append("§").append(TextUtil.codeColor(red % 16))
                    .append("§").append(TextUtil.codeColor(green / 16))
                    .append("§").append(TextUtil.codeColor(green % 16))
                    .append("§").append(TextUtil.codeColor(blue / 16))
                    .append("§").append(TextUtil.codeColor(blue % 16));
            if (i < length) {
                char character = string.charAt(i);
                if (character == '{') {
                    // When it matches like "{\d}", which is also a placeholder for string formatting
                    Matcher matcher = braceLike.matcher(string.substring(i));
                    if (matcher.find()) {
                        String placeholder = matcher.group();
                        int index = Integer.parseInt(placeholder.substring(1, placeholder.length() - 1));
                        stringBuilder.append(String.format("{%d}", index));
                        i += placeholder.length() - 1;
                        continue;
                    }
                }

                // or it matches like "%s", "%d", etc. which is also a placeholder for string formatting
                if (character == '%' && i + 1 < length && Character.isLetter(string.charAt(i + 1))) {
                    Matcher matcher = percentageLike.matcher(string.substring(i));
                    if (matcher.find()) {
                        String placeholder = matcher.group();
                        stringBuilder.append(placeholder);
                        i += placeholder.length() - 1;
                        continue;
                    }
                }
            } else {
                stringBuilder.append(string.charAt(i));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Generates gradient text using completely random colors.
     * <p>
     * Color selection intensity follows an inverse-square probability distribution.
     *
     * @param string Input string to colorize
     * @return Gradient-colored string with random hues
     * @apiNote Uses {@link Math#random()} for color generation. For seed-controlled
     * randomness, use {@link #colorPseudorandomString(String)}.
     */
    @Nonnull
    public static String colorRandomString(@Nonnull String string) {
        List<Color> colorList = new ArrayList<>();
        double r = 1;
        while (1 / r >= Math.random() && r * r <= string.length()) {
            int red = (int) ((Math.random() * 8 + 8) * 15 + Math.random() * 12 + 4);
            int green = (int) ((Math.random() * 8 + 8) * 15 + Math.random() * 12 + 4);
            int blue = (int) ((Math.random() * 8 + 8) * 15 + Math.random() * 12 + 4);
            colorList.add(Color.fromRGB(red, green, blue));
            r++;
        }
        return TextUtil.colorString(string, colorList);
    }

    /**
     * Generates gradient text using pseudorandom colors based on string hash and server name.
     * <p>
     * Provides consistent color results for identical input strings across server restarts.
     *
     * @param string Input string to colorize
     * @return Gradient-colored string with deterministic random hues
     * @apiNote Seed combines string hash and server name. For custom seeds, use
     * {@link #colorPseudorandomString(String, long)}.
     */
    @Nonnull
    public static String colorPseudorandomString(@Nonnull String string) {
        List<Color> colorList = new ArrayList<>();
        double r = 1;
        Random random = new Random(string.hashCode() / 2 + IRDock.getPlugin().getServer().getName().hashCode() / 2);
        while (1 / r >= random.nextDouble() && r * r <= string.length()) {
            int red = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            int green = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            int blue = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            colorList.add(Color.fromRGB(red, green, blue));
            r++;
        }

        return TextUtil.colorString(string, colorList);
    }

    /**
     * Seed-controlled version of {@link #colorPseudorandomString(String)}.
     *
     * @param seed Custom seed value for color generation
     */
    @Nonnull
    public static String colorPseudorandomString(@Nonnull String string, long seed) {
        List<Color> colorList = new ArrayList<>();
        double r = 1;
        Random random = new Random(string.hashCode() / 2 + seed / 2);
        while (1 / r >= random.nextDouble() && r * r <= string.length()) {
            int red = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            int green = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            int blue = (int) ((random.nextDouble() * 8 + 8) * 15 + random.nextDouble() * 12 + 4);
            colorList.add(Color.fromRGB(red, green, blue));
            r++;
        }
        return TextUtil.colorString(string, colorList);
    }

    /**
     * Generates a random Minecraft color code using true randomness.
     *
     * @return Color code in format §x§R1§R2§G1§G2§B1§B2
     * @apiNote Each color component ranges from 8-F (dark to light)
     */
    @Nonnull
    public static String getRandomColor() {
        return "§x" +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8)) +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8)) +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8)) +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8)) +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8)) +
                "§" + (TextUtil.codeColor((int) (Math.random() * 8) + 8));
    }

    /**
     * Generates a pseudorandom color code using cumulative seed counter.
     *
     * @param seed Seed modifier added to internal counter
     * @return Reproducible color code based on cumulative seed state
     */
    @Nonnull
    public static String getPseudorandomColor(long seed) {
        COUNT += seed;
        Random random = new Random(COUNT);
        return "§x" +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8)) +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8)) +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8)) +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8)) +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8)) +
                "§" + (TextUtil.codeColor(random.nextInt(8) + 8));
    }

    /**
     * Converts RGB color to Minecraft §x format code.
     *
     * @param color Bukkit Color object to convert
     * @return §x-formatted color code string
     */
    @Nonnull
    public static String toTextCode(@Nonnull Color color) {
        return "§x" + "§" + TextUtil.codeColor(color.getRed() / 16) + "§" + TextUtil.codeColor(color.getRed() % 16) + "§" + TextUtil.codeColor(color.getGreen() / 16) + "§" + TextUtil.codeColor(color.getGreen() % 16) + "§" + TextUtil.codeColor(color.getBlue() / 16) + "§" + TextUtil.codeColor(color.getBlue() % 16);
    }

    /**
     * Converts 0-15 integer to hexadecimal character code.
     *
     * @param c Integer value (0-15)
     * @return Single-character hex representation
     * @apiNote Used internally for color code construction. Returns "0" for invalid inputs.
     */
    @Nonnull
    public static String codeColor(int c) {
        if (c < 10 && c >= 0) {
            return String.valueOf(c);
        }
        return switch (c) {
            case 10 -> "a";
            case 11 -> "b";
            case 12 -> "c";
            case 13 -> "d";
            case 14 -> "e";
            case 15 -> "f";
            default -> "0";
        };
    }

    /**
     * Creates a deep copy of a Color object.
     *
     * @param color Original color to clone
     * @return New Color instance with identical RGB values
     */
    @Nonnull
    public static Color cloneColor(@Nonnull Color color) {
        return Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Generates interpolated color array between specified colors.
     *
     * @param size   Number of output colors
     * @param colors Array of source colors for interpolation
     * @return Array of colors linearly interpolated between source points
     * @apiNote Returns single-color array when size=1. Returns empty array for invalid inputs.
     * @see #disperse(int, List)
     */
    @Nonnull
    public static Color[] disperse(int size, @Nonnull Color... colors) {
        if (size == 1 && colors.length > 0) {
            return new Color[]{TextUtil.cloneColor(colors[0])};
        } else if (size == 0 || colors.length == 0) {
            return new Color[0];
        }
        Color[] result = new Color[size--];
        for (int i = 0; i <= size; i++) {
            double p = ((double) i) / size * (colors.length - 1);

            int r = (int) (colors[(int) Math.floor(p)].getRed() * (1 - p + Math.floor(p)) + colors[(int) Math.ceil(p)].getRed() * (p - Math.floor(p)));
            int g = (int) (colors[(int) Math.floor(p)].getGreen() * (1 - p + Math.floor(p)) + colors[(int) Math.ceil(p)].getGreen() * (p - Math.floor(p)));
            int b = (int) (colors[(int) Math.floor(p)].getBlue() * (1 - p + Math.floor(p)) + colors[(int) Math.ceil(p)].getBlue() * (p - Math.floor(p)));

            result[i] = Color.fromRGB(r, g, b);
        }
        return result;
    }

    /**
     * List-based overload of {@link #disperse(int, Color...)}.
     */
    @Nonnull
    public static Color[] disperse(int size, @Nonnull List<Color> colorList) {
        if (size == 1 && !colorList.isEmpty()) {
            return new Color[]{TextUtil.cloneColor(colorList.get(0))};
        } else if (size == 0 || colorList.isEmpty()) {
            return new Color[0];
        }
        Color[] result = new Color[size--];
        for (int i = 0; i <= size; i++) {
            double p = ((double) i) / size * (colorList.size() - 1);

            int r = (int) (colorList.get((int) Math.floor(p)).getRed() * (1 - p + Math.floor(p)) + colorList.get((int) Math.ceil(p)).getRed() * (p - Math.floor(p)));
            int g = (int) (colorList.get((int) Math.floor(p)).getGreen() * (1 - p + Math.floor(p)) + colorList.get((int) Math.ceil(p)).getGreen() * (p - Math.floor(p)));
            int b = (int) (colorList.get((int) Math.floor(p)).getBlue() * (1 - p + Math.floor(p)) + colorList.get((int) Math.ceil(p)).getBlue() * (p - Math.floor(p)));

            result[i] = Color.fromRGB(r, g, b);
        }
        return result;
    }

    public static String onlyUpperFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        if (str.length() == 1) {
            return str.toUpperCase();
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    @SuppressWarnings("deprecation")
    public static <T> List<T> crop(List<T> list, int maxLines, Function<String, T> adapter) {
        var result = new ArrayList<T>();
        for (T obj : list) {
            if (result.size() >= maxLines) {
                result.add(adapter.apply("" + ChatColor.BLUE + (list.size() - maxLines) + " more lines..."));
                result.add(adapter.apply(" "));
                break;
            }
            result.add(obj);
        }

        return result;
    }

    public static String getBooleanText(boolean bool) {
        return bool ? COLOR_POSITIVE + "Yes" : COLOR_NEGATIVE + "No";
    }

    @Contract("null -> null; !null -> !null")
    public static String upperFirstLetterOnly(String name) {
        if (name == null) {
            return null;
        }

        if (name.isEmpty()) {
            return name;
        }

        if (name.length() == 1) {
            return name.toUpperCase();
        }

        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
