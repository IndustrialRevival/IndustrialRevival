package org.irmc.industrialrevival.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

@SuppressWarnings({"unused", "deprecation"})
public class Debug {
    private static final JavaPlugin plugin = IndustrialRevival.getInstance();
    private static final String debugPrefix = "[Debug] ";
    // todo: make debug mode configurable
    private static boolean debug = false;

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

    public static void debug(@Nullable Object object) {
        if (object == null) {
            debug("null");
            return;
        }
        debug(object.toString());
    }

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

    public static void debug(String message) {
        if (debug) {
            log(debugPrefix + message);
        }
    }

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

    public static void sendMessage(@Nullable Player player, @Nullable Object object) {
        if (object == null) {
            sendMessage(player, "null");
            return;
        }
        sendMessage(player, object.toString());
    }

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

    public static void sendMessage(@Nullable Player player, @Nullable String message) {
        if (player == null) {
            return;
        }

        if (message == null) {
            return;
        }
        player.sendMessage("[" + plugin.getLogger().getName() + "]" + message);
    }

    public static void stackTraceManually() {
        try {
            throw new Error();
        } catch (Throwable e) {
            log(e);
        }
    }

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

    public static void log(@Nullable Object object) {
        if (object == null) {
            log("null");
            return;
        }
        log(object.toString());
    }

    public static void log(String @Nullable ... messages) {
        if (messages == null) {
            log("null");
            return;
        }
        for (String message : messages) {
            log(message);
        }
    }

    public static void log(@Nullable String message) {
        if (message == null) {
            log("null");
            return;
        }
        plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void log(@Nullable Throwable e) {
        if (e == null) {
            log("null");
            return;
        }
        e.printStackTrace();
    }

    public static void log() {
        log("");
    }

    public static void warning(@Nullable String message) {
        plugin.getLogger().warning(message);
    }

    public static void error(@Nullable String message) {
        plugin.getLogger().severe(message);
    }

    public static void severe(@Nullable String message) {
        error(message);
    }

    public static void warning(@Nullable Object object) {
        if (object == null) {
            warning("null");
            return;
        }
        warning(object.toString());
    }

    public static void error(@Nullable Object object) {
        if (object == null) {
            error("null");
            return;
        }
        error(object.toString());
    }

    public static void severe(@Nullable Object object) {
        if (object == null) {
            severe("null");
            return;
        }
        error(object.toString());
    }

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

    public static void log(@Nullable String message, Throwable e) {
        log(message);
        log(e);
    }

    public static void log(@Nullable Object object, Throwable e) {
        log(object);
        log(e);
    }

    public static void log(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        log(message, e);
        log(objects);
    }

    public static void log(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        log(object, e);
        log(objects);
    }

    public static void log(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        log(message, e);
        log(messages);
    }

    public static void log(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        log(object, e);
        log(messages);
    }

    public static void fine(@Nullable String message) {
        plugin.getLogger().fine(message);
    }

    public static void fine(@Nullable Object object) {
        if (object == null) {
            fine("null");
            return;
        }
        fine(object.toString());
    }

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

    public static void fine(@Nullable String message, Throwable e) {
        fine(message);
        log(e);
    }

    public static void fine(@Nullable Object object, Throwable e) {
        fine(object);
        log(e);
    }

    public static void fine(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        fine(message, e);
        log(objects);
    }

    public static void fine(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        fine(object, e);
        log(objects);
    }

    public static void fine(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        fine(message, e);
        log(messages);
    }

    public static void fine(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        fine(object, e);
        log(messages);
    }

    public static void debug(@Nullable String message, Throwable e) {
        debug(message);
        log(e);
    }

    public static void debug(@Nullable Object object, Throwable e) {
        debug(object);
        log(e);
    }

    public static void debug(@Nullable String message, @Nullable Throwable e, Object @Nullable ... objects) {
        debug(message, e);
        log(objects);
    }

    public static void debug(@Nullable Object object, @Nullable Throwable e, Object @Nullable ... objects) {
        debug(object, e);
        log(objects);
    }

    public static void debug(@Nullable String message, @Nullable Throwable e, String @Nullable ... messages) {
        debug(message, e);
        log(messages);
    }

    public static void debug(@Nullable Object object, @Nullable Throwable e, String @Nullable ... messages) {
        debug(object, e);
        log(messages);
    }

    public static void log(Level level, @Nullable String message) {
        plugin.getLogger().log(level, message);
    }
}
