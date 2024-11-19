package org.irmc.industrialrevival.api.helpers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemStackHelper {
    public static final String NULL_ITEM_NAME = "null";
    public static boolean isItemNull(@Nullable ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == null;
    }

    public static boolean isItemNonnull(@Nullable ItemStack itemStack) {
        return itemStack != null && itemStack.getType() != null;
    }

    @NotNull
    public static String getDisplayName(@NotNull ItemStack itemStack) {
        if (isItemNull(itemStack)) {
            return NULL_ITEM_NAME;
        }

        Material material = itemStack.getType();
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            String displayName = meta.getDisplayName();
            if (displayName != null) {
                return displayName;
            }

            Component component = meta.displayName();
            if (component != null) {
                if (component instanceof TextComponent tc) {
                    return tc.content();
                } else {
                    return component.toString();
                }
            }
        } else {
            if (material == Material.PLAYER_HEAD || material == Material.PLAYER_WALL_HEAD) {
                if (meta instanceof SkullMeta skullMeta) {
                    String displayName = skullMeta.getDisplayName();
                    if (displayName != null) {
                        return displayName;
                    }

                    if (skullMeta.hasOwner()) {
                        String owner = skullMeta.getOwner();
                        if (owner != null) {
                            return owner;
                        }
                    }

                    OfflinePlayer player = skullMeta.getOwningPlayer();
                    if (player != null) {
                        String name = player.getName();
                        if (name != null) {
                            return name;
                        }
                    }
                }
            }
        }

        return material.name();
    }
}
