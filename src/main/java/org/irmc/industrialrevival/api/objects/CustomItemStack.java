package org.irmc.industrialrevival.api.objects;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Deprecated
@Warning(reason = "Moving to org.irmc.pigeonLib")
@ApiStatus.ScheduledForRemoval(inVersion = "1.0")
public class CustomItemStack extends ItemStack {
    public CustomItemStack(ItemStack item, Consumer<ItemMeta> meta) {
        super(item);
        ItemMeta im = getItemMeta();
        meta.accept(im);
        setItemMeta(im);
    }

    public CustomItemStack(ItemStack item) {
        super(item);
    }

    public CustomItemStack(Material type) {
        super(type);
    }

    public CustomItemStack(Material type, Consumer<ItemMeta> meta) {
        this(new ItemStack(type), meta);
    }

    public CustomItemStack(ItemStack item, String name, String... lore) {
        this(item, im -> {
            if (name != null) {
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                im.setLore(lines);
            }
        });
    }

    public CustomItemStack(ItemStack item, Color color, String name, String... lore) {
        this(item, im -> {
            if (name != null) {
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                im.setLore(lines);
            }

            if (im instanceof LeatherArmorMeta) {
                ((LeatherArmorMeta) im).setColor(color);
            } else if (im instanceof PotionMeta) {
                ((PotionMeta) im).setColor(color);
            }
        });
    }

    public CustomItemStack(Material material, Component name) {
        this(material, name, new ArrayList<>());
    }

    public CustomItemStack(Material type, String name, String... lore) {
        this(new ItemStack(type), name, lore);
    }

    public CustomItemStack(Material type, String name, List<String> lore) {
        this(new ItemStack(type), name, lore.toArray(new String[0]));
    }

    public CustomItemStack(ItemStack item, int amount) {
        super(item);
        setAmount(amount);
    }

    public CustomItemStack(ItemStack item, Component name, Component... lore) {
        this(item, im -> {
            im.displayName(name);

            if (lore.length > 0) {
                List<Component> lines = new ArrayList<>();

                Collections.addAll(lines, lore);
                im.lore(lines);
            }
        });
    }

    public CustomItemStack(Material type, Component name, List<Component> lore) {
        this(new ItemStack(type), im -> {
            im.displayName(name);

            if (!lore.isEmpty()) {
                im.lore(lore);
            }
        });
    }

    public CustomItemStack addFlags(ItemFlag... flags) {
        ItemMeta im = getItemMeta();
        im.addItemFlags(flags);
        setItemMeta(im);

        return this;
    }

    public CustomItemStack modifyLore(Consumer<List<Component>> consumer) {
        List<Component> lore = getItemMeta().lore();
        if (lore == null) {
            lore = new ArrayList<>();
            consumer.accept(lore);
        }

        getItemMeta().lore(lore);
        return this;
    }

    public CustomItemStack setCustomModel(int data) {
        ItemMeta im = getItemMeta();
        im.setCustomModelData(data == 0 ? null : data);
        setItemMeta(im);

        return this;
    }

    public <T, Z> CustomItemStack setPDCData(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        editMeta(m -> m.getPersistentDataContainer().set(key, type, value));
        return this;
    }

    public ItemStack toPureItemStack() {
        ItemStack pure = new ItemStack(this.getType(), this.getAmount());
        pure.setItemMeta(this.getItemMeta());
        return pure;
    }
}
