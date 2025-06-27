package org.irmc.industrialrevival.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

/**
 * This utility class provides debugging and logging functionalities for the IndustrialRevival plugin.
 * It includes methods to log messages, warnings, errors, and debug information,
 * as well as to send messages to players.
 *
 * @author balugaq
 */
@SuppressWarnings({"unused", "deprecation"})
public class Debug {
    private static final JavaPlugin PLUGIN = IRDock.getPlugin();
    private static final String DEBUG_PREFIX = "[Debug] ";
    private static final boolean DEBUGGING = IRDock.getPlugin().getConfig().getBoolean("debug", false);

    /**
     * Logs debug information for multiple objects.
     *
     * @param objects The objects to log.
     */
    public static void debug(Object @Nullable ... objects) {
        if (objects == null) {
            debug("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objects) {
            if (obj == null) {
                sb.append("null");
            } else {
                sb.append(obj);
            }
        }
        debug(sb.toString());
    }

    /**
     * Logs debug information for a single object.
     *
     * @param object The object to log.
     */
    public static void debug(@Nullable Object object) {
        if (object == null) {
            debug("null");
            return;
        }
        debug(object.toString());
    }

    /**
     * Logs debug information for multiple messages.
     *
     * @param messages The messages to log.
     */
    public static void debug(String @Nullable ... messages) {
        if (messages == null) {
            debug("null");
            return;
        }
        for (String message : messages) {
            if (message == null) {
                debug("null");
                continue;
            }
            debug(message);
        }
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to log.
     */
    public static void debug(String message) {
        if (DEBUGGING) {
            log(DEBUG_PREFIX + message);
        }
    }

    /**
     * Sends a message to a player for multiple objects.
     *
     * @param player  The player to send the message to.
     * @param objects The objects to include in the message.
     */
    public static void sendMessage(@Nullable Player player, Object @Nullable ... objects) {
        if (player == null) {
            return;
        }

        if (objects == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objects) {
            if (obj == null) {
                sb.append("null");
            } else {
                sb.append(obj);
            }
        }
        sendMessage(player, sb.toString());
    }

    /**
     * Sends a message to a player for a single object.
     *
     * @param player The player to send the message to.
     * @param object The object to include in the message.
     */
    public static void sendMessage(@Nullable Player player, @Nullable Object object) {
        if (object == null) {
            sendMessage(player, "null");
            return;
        }
        sendMessage(player, object.toString());
    }

    /**
     * Sends multiple messages to a player.
     *
     * @param player   The player to send the messages to.
     * @param messages The messages to send.
     */
    public static void sendMessages(@Nullable Player player, String @Nullable ... messages) {
        if (player == null) {
            return;
        }

        if (messages == null) {
            return;
        }
        for (String message : messages) {
            sendMessage(player, message);
        }
    }

    /**
     * Sends a message to a player.
     *
     * @param player  The player to send the message to.
     * @param message The message to send.
     */
    public static void sendMessage(@Nullable Player player, @Nullable String message) {
        if (player == null) {
            return;
        }

        if (message == null) {
            return;
        }
        player.sendMessage("[" + PLUGIN.getLogger().getName() + "]" + message);
    }

    /**
     * Logs a manually generated stack trace.
     */
    public static void stackTraceManually() {
        try {
            throw new Error();
        } catch (Throwable e) {
            log(e);
        }
    }

    /**
     * Logs multiple objects.
     *
     * @param object The objects to log.
     */
    public static void log(Object @Nullable ... object) {
        if (object == null) {
            log("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : object) {
            if (obj == null) {
                sb.append("null");
            } else {
                sb.append(obj);
            }
        }

        log(sb.toString());
    }

    /**
     * Logs a single object.
     *
     * @param object The object to log.
     */
    public static void log(@Nullable Object object) {
        if (object == null) {
            log("null");
            return;
        }
        log(object.toString());
    }

    /**
     * Logs multiple messages.
     *
     * @param messages The messages to log.
     */
    public static void log(String @Nullable ... messages) {
        if (messages == null) {
            log("null");
            return;
        }
        for (String message : messages) {
            log(message);
        }
    }

    /**
     * Logs a message.
     *
     * @param message The message to log.
     */
    public static void log(@Nullable String message) {
        if (message == null) {
            log("null");
            return;
        }
        PLUGIN.getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Logs a throwable (e.g., an exception).
     *
     * @param e The throwable to log.
     */
    public static void log(@Nullable Throwable e) {
        if (e == null) {
            log("null");
            return;
        }
        e.printStackTrace();
    }

    /**
     * Logs an empty message.
     */
    public static void log() {
        log("");
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log.
     */
    public static void warning(@Nullable String message) {
        PLUGIN.getLogger().warning(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to log.
     */
    public static void error(@Nullable String message) {
        PLUGIN.getLogger().severe(message);
    }

    /**
     * Logs a severe error message.
     *
     * @param message The severe error message to log.
     */
    public static void severe(@Nullable String message) {
        error(message);
    }

    /**
     * Logs a warning for a single object.
     *
     * @param object The object to log as a warning.
     */
    public static void warning(@Nullable Object object) {
        if (object == null) {
            warning("null");
            return;
        }
        warning(object.toString());
    }

    /**
     * Logs an error for a single object.
     *
     * @param object The object to log as an error.
     */
    public static void error(@Nullable Object object) {
        if (object == null) {
            error("null");
            return;
        }
        error(object.toString());
    }

    /**
     * Logs an error for a throwable (e.g., an exception).
     * @param e The throwable to log as an error.
     */
    public static void error(@Nullable Throwable e) {
        if (e == null) {
            error("null");
            return;
        }
        error(e.getMessage());
        e.printStackTrace();
    }

    /**
     * Logs a severe error for a single object.
     *
     * @param object The object to log as a severe error.
     */
    public static void severe(@Nullable Object object) {
        if (object == null) {
            severe("null");
            return;
        }
        error(object.toString());
    }

    /**
     * Logs multiple warning messages.
     *
     * @param messages The warning messages to log.
     */
    public static void warning(@Nullable String @Nullable ... messages) {
        if (messages == null) {
            warning("null");
            return;
        }
        for (String message : messages) {
            if (message == null) {
                warning("null");
                continue;
            }
            warning(message);
        }
    }

    /**
     * Logs multiple error messages.
     *
     * @param messages The error messages to log.
     */
    public static void error(@Nullable String @Nullable ... messages) {
        if (messages == null) {
            error("null");
            return;
        }
        for (String message : messages) {
            if (message == null) {
                error("null");
                continue;
            }
            error(message);
        }
    }

    /**
     * Logs multiple severe error messages.
     *
     * @param messages The severe error messages to log.
     */
    public static void severe(@Nullable String @Nullable ... messages) {
        if (messages == null) {
            severe("null");
            return;
        }
        for (String message : messages) {
            if (message == null) {
                severe("null");
                continue;
            }
            severe(message);
        }
    }

    /**
     * Logs multiple warning objects.
     *
     * @param objects The objects to log as warnings.
     */
    public static void warning(@Nullable Object @Nullable ... objects) {
        if (objects == null) {
            warning("null");
            return;
        }
        for (Object object : objects) {
            if (object == null) {
                warning("null");
                continue;
            }
            warning(object);
        }
    }

    /**
     * Logs multiple error objects.
     *
     * @param objects The objects to log as errors.
     */
    public static void error(@Nullable Object @Nullable ... objects) {
        if (objects == null) {
            error("null");
            return;
        }
        for (Object object : objects) {
            if (object == null) {
                error("null");
                continue;
            }
            error(object);
        }
    }

    /**
     * Logs multiple severe error objects.
     *
     * @param objects The objects to log as severe errors.
     */
    public static void severe(@Nullable Object @Nullable ... objects) {
        if (objects == null) {
            severe("null");
            return;
        }
        for (Object object : objects) {
            if (object == null) {
                severe("null");
                continue;
            }
            severe(object);
        }
    }

    /**
     * Logs a message and a throwable.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     */
    public static void log(@Nullable String message, Throwable e) {
        log(message);
        log(e);
    }

    /**
     * Logs an object and a throwable.
     *
     * @param object The object to log.
     * @param e      The throwable to log.
     */
    public static void log(@Nullable Object object, Throwable e) {
        log(object);
        log(e);
    }

    /**
     * Logs a message, a throwable, and multiple objects.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void log(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        log(message, e);
        log(objects);
    }

    /**
     * Logs an object, a throwable, and multiple objects.
     *
     * @param object  The object to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void log(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        log(object, e);
        log(objects);
    }

    /**
     * Logs a message, a throwable, and multiple messages.
     *
     * @param message  The message to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void log(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        log(message, e);
        log(messages);
    }

    /**
     * Logs an object, a throwable, and multiple messages.
     *
     * @param object   The object to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void log(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        log(object, e);
        log(messages);
    }

    /**
     * Logs a fine-grained message.
     *
     * @param message The message to log.
     */
    public static void fine(@Nullable String message) {
        PLUGIN.getLogger().fine(message);
    }

    /**
     * Logs a fine-grained object.
     *
     * @param object The object to log.
     */
    public static void fine(@Nullable Object object) {
        if (object == null) {
            fine("null");
            return;
        }
        fine(object.toString());
    }

    /**
     * Logs multiple fine-grained messages.
     *
     * @param messages The messages to log.
     */
    public static void fine(@Nullable String @Nullable ... messages) {
        if (messages == null) {
            fine("null");
            return;
        }
        for (String message : messages) {
            if (message == null) {
                fine("null");
                continue;
            }
            fine(message);
        }
    }

    /**
     * Logs multiple fine-grained objects.
     *
     * @param objects The objects to log.
     */
    public static void fine(@Nullable Object @Nullable ... objects) {
        if (objects == null) {
            fine("null");
            return;
        }
        for (Object object : objects) {
            if (object == null) {
                fine("null");
                continue;
            }
            fine(object);
        }
    }

    /**
     * Logs a fine-grained message and a throwable.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     */
    public static void fine(@Nullable String message, Throwable e) {
        fine(message);
        log(e);
    }

    /**
     * Logs a fine-grained object and a throwable.
     *
     * @param object The object to log.
     * @param e      The throwable to log.
     */
    public static void fine(@Nullable Object object, Throwable e) {
        fine(object);
        log(e);
    }

    /**
     * Logs a fine-grained message, a throwable, and multiple objects.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void fine(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        fine(message, e);
        log(objects);
    }

    /**
     * Logs a fine-grained object, a throwable, and multiple objects.
     *
     * @param object  The object to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void fine(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        fine(object, e);
        log(objects);
    }

    /**
     * Logs a fine-grained message, a throwable, and multiple messages.
     *
     * @param message  The message to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void fine(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        fine(message, e);
        log(messages);
    }

    /**
     * Logs a fine-grained object, a throwable, and multiple messages.
     *
     * @param object   The object to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void fine(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        fine(object, e);
        log(messages);
    }

    /**
     * Logs a debug message and a throwable.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     */
    public static void debug(@Nullable String message, Throwable e) {
        debug(message);
        log(e);
    }

    /**
     * Logs a debug object and a throwable.
     *
     * @param object The object to log.
     * @param e      The throwable to log.
     */
    public static void debug(@Nullable Object object, Throwable e) {
        debug(object);
        log(e);
    }

    /**
     * Logs a debug message, a throwable, and multiple objects.
     *
     * @param message The message to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void debug(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        debug(message, e);
        log(objects);
    }

    /**
     * Logs a debug object, a throwable, and multiple objects.
     *
     * @param object  The object to log.
     * @param e       The throwable to log.
     * @param objects The objects to log.
     */
    public static void debug(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        debug(object, e);
        log(objects);
    }

    /**
     * Logs a debug message, a throwable, and multiple messages.
     *
     * @param message  The message to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void debug(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        debug(message, e);
        log(messages);
    }

    /**
     * Logs a debug object, a throwable, and multiple messages.
     *
     * @param object   The object to log.
     * @param e        The throwable to log.
     * @param messages The messages to log.
     */
    public static void debug(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        debug(object, e);
        log(messages);
    }

    /**
     * Logs a message at a specific log level.
     *
     * @param level   The log level.
     * @param message The message to log.
     */
    public static void log(@NotNull Level level, @Nullable String message) {
        PLUGIN.getLogger().log(level, message);
    }
}
