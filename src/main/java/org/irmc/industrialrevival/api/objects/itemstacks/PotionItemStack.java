package org.irmc.industrialrevival.api.objects.itemstacks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

public class PotionItemStack extends CustomItemStack {
    public PotionItemStack(PotionMeta paramPotionMeta) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                PotionType potionType = paramPotionMeta.getBasePotionType();
                potionMeta.setBasePotionType(potionType);
                for (PotionEffect effect : paramPotionMeta.getCustomEffects()) {
                    potionMeta.addCustomEffect(effect, true);
                }

                Color color = paramPotionMeta.getColor();
                potionMeta.setColor(color);
            }
        }));
    }

    public PotionItemStack(PotionType potionType, Color color, PotionEffect... effects) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                potionMeta.setBasePotionType(potionType);
                for (PotionEffect effect : effects) {
                    potionMeta.addCustomEffect(effect, true);
                }
                potionMeta.setColor(color);
            }
        }));
    }

    public PotionItemStack(PotionType potionType, PotionEffect... effects) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                potionMeta.setBasePotionType(potionType);
                for (PotionEffect effect : effects) {
                    potionMeta.addCustomEffect(effect, true);
                }
            }
        }));
    }

    public PotionItemStack(Color color, PotionEffect... effects) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                for (PotionEffect effect : effects) {
                    potionMeta.addCustomEffect(effect, true);
                }
                potionMeta.setColor(color);
            }
        }));
    }

    public PotionItemStack(PotionEffect... effects) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                for (PotionEffect effect : effects) {
                    potionMeta.addCustomEffect(effect, true);
                }
            }
        }));
    }

    public PotionItemStack(PotionType potionType) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                potionMeta.setBasePotionType(potionType);
            }
        }));
    }

    public PotionItemStack(Color color) {
        super(Material.POTION, (itemMeta -> {
            if (itemMeta instanceof PotionMeta potionMeta) {
                potionMeta.setColor(color);
            }
        }));
    }
}
