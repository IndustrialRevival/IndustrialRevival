package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Utility class for parsing command-line arguments with flag syntax.
 * <p>
 * Supports both boolean flags (-flag) and value-carrying arguments.
 *
 * @author balugaq
 */
@UtilityClass
public class CommandUtil {
    /**
     * Checks for presence of a boolean flag.
     *
     * @param args Command arguments array
     * @param flag Flag to check (without '-' prefix)
     * @return True if flag exists in arguments
     */
    @ParametersAreNonnullByDefault
    public static boolean hasFlag(@Nonnull String[] args, @Nonnull String flag) {
        for (String arg : args) {
            if (arg.equals("-" + flag)) {
                return true;
            }
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean hasArgFlag(@Nonnull String[] args, @Nonnull String flag) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-" + flag)) {
                if (i + 1 < args.length) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Retrieves value associated with a flag.
     *
     * @param args Command arguments array
     * @param flag Flag to query (without '-' prefix)
     * @return Associated value or null if not found
     */
    @Nullable
    @ParametersAreNonnullByDefault
    public static String getArgFlag(@Nonnull String[] args, @Nonnull String flag) {
        if (!hasArgFlag(args, flag)) {
            return null;
        }

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-" + flag)) {
                if (i + 1 < args.length) {
                    return args[i + 1];
                }
            }
        }

        return null;
    }
}
