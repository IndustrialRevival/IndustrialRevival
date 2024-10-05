package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.utils.MenuUtil;

// TODO
public class ManualGrind extends BasicMachine {
    private static final int MENU_SIZE = 27;
    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        new MachineMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                setSize(MENU_SIZE);
                handleMenuDrawer(new MatrixMenuDrawer(MENU_SIZE)
                        .addLine("IIIDDDOOO")
                        .addLine("I ID DO O")
                        .addLine("IIIDDDOOO")
                        .addExplain("I", MenuUtil.INPUT_BORDER)
                        .addExplain("D", MenuUtil.CLICKER_BORDER)
                        .addExplain("O", MenuUtil.OUTPUT_BORDER)
                );
            }
            @Override
            public int[] getSlotsByItemFlow(ItemFlow itemFlow, ItemStack itemStack) {
                return new int[0];
            }
        };
    }
}
