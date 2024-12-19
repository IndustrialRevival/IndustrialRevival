package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"deprecation", "unused"})
@UtilityClass
public class ClipboardUtil {
    public static void send(@NotNull Player player, String text) {
        send(player, "信息: ", text);
    }

    public static void send(@NotNull Player player, String display, String text) {
        send(player, display, "点击复制到剪贴板", text);
    }

    public static void send(@NotNull Player player, String display, String hover, String text) {
        TextComponent msg = new TextComponent(display);
        msg.setUnderlined(true);
        msg.setItalic(true);
        msg.setColor(ChatColor.GRAY);
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text));
        player.spigot().sendMessage(msg);
    }
}
