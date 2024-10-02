package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.irmc.industrialrevival.api.items.ArmorSet;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.enums.ArmorProtectionType;

public class ArmorCheckTask implements Consumer<WrappedTask> {
    private static final Map<String, List<ArmorProtectionType>> protection = new HashMap<>();

    private final int checkInterval; // 1 sec

    public ArmorCheckTask(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    @Override
    public void accept(WrappedTask wrappedTask) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("industrialrevival.bypass.radiation")) {
                continue;
            }

            ArmorSet previousArmorSet = null;

            boolean check1, check2, check3;
            check1 = check2 = check3 = false;

            for (int i = 0; i < p.getEquipment().getArmorContents().length; i++) {
                ItemStack armor = p.getEquipment().getArmorContents()[i];
                if (armor == null) {
                    continue;
                }

                IndustrialRevivalItem irItem = IndustrialRevivalItem.getByItem(armor);
                if (irItem instanceof ArmorSet.ArmorPiece piece) {
                    ArmorSet parent = piece.getParent();
                    for (PotionEffect effect : piece.getPotionEffects()) {
                        effect.withDuration(checkInterval / 20);
                        p.addPotionEffect(effect);
                    }

                    if (parent.isProtectWhenFullSet()) {
                        if (previousArmorSet != parent) {
                            if (i > 0) {
                                if (previousArmorSet != null && previousArmorSet.isProtectWhenFullSet()) {
                                    //reset protection for the previous armor set
                                    protection.put(p.getName(), List.of());
                                }
                            }
                            previousArmorSet = parent;
                        } else {
                            switch (i) {
                                case 1 -> check1 = true;
                                case 2 -> check2 = true;
                                case 3 -> check3 = true;
                            }

                            if (check1 && check2 && check3) {
                                List<ArmorProtectionType> protectionTypes = protection.getOrDefault(p.getName(), List.of());
                                protectionTypes.addAll(parent.getProtectionTypes());
                                protection.put(p.getName(), protectionTypes);
                            }
                        }
                    } else {
                        List<ArmorProtectionType> protectionTypes = protection.getOrDefault(p.getName(), List.of());
                        protectionTypes.addAll(parent.getProtectionTypes());
                        protection.put(p.getName(), protectionTypes);
                    }
                }
            }
        }
    }

    public List<ArmorProtectionType> getProtections(Player player) {
        return protection.getOrDefault(player.getName(), List.of());
    }
}
