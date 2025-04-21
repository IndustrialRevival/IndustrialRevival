package org.irmc.industrialrevival.implementation.items.debug;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Container extends IndustrialRevivalItem {
    private static final MatrixMenuDrawer drawer = new MatrixMenuDrawer(54);
    @Override
    public void preRegister() throws Exception {
        super.preRegister();

        new MachineMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                withMenuDrawer(drawer);
            }

            public int[] getSlotsByItemFlow(@NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
                if (itemFlow == ItemFlow.INSERT) {
                    return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
                } else {
                    return new int[] {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
                }
            }
        };
    }
}
