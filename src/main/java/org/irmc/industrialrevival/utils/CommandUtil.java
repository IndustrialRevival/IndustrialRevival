package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
public class CommandUtil {
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
