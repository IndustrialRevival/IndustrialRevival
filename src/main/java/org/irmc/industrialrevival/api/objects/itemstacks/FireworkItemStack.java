package org.irmc.industrialrevival.api.objects.itemstacks;

import com.google.common.collect.ImmutableList;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.jetbrains.annotations.NotNull;

public class FireworkItemStack extends CustomItemStack {
    public FireworkItemStack(int amount, boolean flicker, boolean trail, @NotNull ImmutableList<Color> colors, @NotNull ImmutableList<Color> fadeColors, @NotNull FireworkEffect.Type type) {
        super(Material.FIREWORK_STAR, amount, (itemMeta -> {
            if (itemMeta instanceof FireworkEffectMeta fem) {
                FireworkEffect.Builder builder = FireworkEffect.builder();
                builder.flicker(flicker);
                builder.trail(trail);
                for (Color color : colors) {
                    builder.withColor(color);
                }
                for (Color color : fadeColors) {
                    builder.withFade(color);
                }
                builder.with(type);
                fem.setEffect(builder.build());
            }
        }));
    }

    public FireworkItemStack(int amount, boolean flicker, boolean trail, @NotNull Color color, @NotNull Color fadeColor, @NotNull FireworkEffect.Type type) {
        this(amount, flicker, trail, ImmutableList.of(color), ImmutableList.of(fadeColor), type);
    }

    public FireworkItemStack(int amount, boolean flicker, boolean trail, @NotNull Color color, @NotNull FireworkEffect.Type type) {
        this(amount, flicker, trail, ImmutableList.of(color), ImmutableList.of(), type);
    }

    public FireworkItemStack(int amount, boolean flicker, boolean trail, @NotNull FireworkEffect.Type type) {
        this(amount, flicker, trail, ImmutableList.of(), ImmutableList.of(), type);
    }

    public FireworkItemStack(int amount, @NotNull FireworkEffect effect) {
        this(amount, effect.hasFlicker(), effect.hasTrail(), ImmutableList.copyOf(effect.getColors()), ImmutableList.copyOf(effect.getFadeColors()), effect.getType());
    }

    public FireworkItemStack(int amount, @NotNull Color color) {
        this(amount, false, false, ImmutableList.of(color), ImmutableList.of(), FireworkEffect.Type.BALL);
    }

    public FireworkItemStack(@NotNull Color color) {
        this(1, color);
    }
}
