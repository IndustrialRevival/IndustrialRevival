package org.irmc.industrialrevival.implementation.items.machines.display;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.InventoryBlock;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.HeadItem;

public class ItemDisplayer extends IndustrialRevivalItem implements InventoryBlock {
    private static final ItemStack heightPlus = new CustomItemStack(HeadItem.createByUrl("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7"),
            Component.empty(), Component.empty());

    private static final ItemStack heightMinus = new CustomItemStack(HeadItem.createByUrl("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b"),
            Component.empty(), Component.empty());

    private static final ItemStack info = new CustomItemStack(HeadItem.createByUrl("http://textures.minecraft.net/texture/fa2afa7bb063ac1ff3bbe08d2c558a7df2e2bacdf15dac2a64662dc40f8fdbad"), Component.empty(), Component.empty());

    private final int twoDisplaySpacing = getItemSetting().getInt("two_display_spacing", 2);
    private final int defaultDistanceFromMachine = getItemSetting().getInt("default_distance_from_machine", 2);
    private final int maxDistanceFromMachine = getItemSetting().getInt("max_distance_from_machine", 10);

    private TextDisplay nameDisplay;
    private ItemDisplay itemDisplay;

    public ItemDisplayer() {
        createMenu(this, preset -> {
            MatrixMenuDrawer drawer = new MatrixMenuDrawer(27)
                    .addLine("BBBBIBBBB")
                    .addLine("BSBN NBPB")
                    .addLine("BBBBBBBBB")
                    .addExplain('B', MenuUtil.BACKGROUND)
                    .addExplain('N', MenuUtil.INPUT_BORDER)
                    .addExplain('S', heightMinus, (_, _, _, menu, _) -> {
                        MachineMenu menu1 = (MachineMenu) menu;
                        ItemDisplayer.this.minusHeight(menu1.getLocation());
                        return false;
                    })
                    .addExplain('P', heightPlus, (_, _, _, menu, _) -> {
                        MachineMenu menu1 = (MachineMenu) menu;
                        ItemDisplayer.this.plusHeight(menu1.getLocation());
                        return false;
                    })
                    .addExplain('I', info, ClickHandler.DEFAULT);

            preset.withMenuDrawer(drawer);
            preset.setClickHandler(13, (_, _, _, menu, _) -> {
                ItemStack itemStack = menu.getItem(13);
                if (itemStack != null) {
                    itemDisplay.setItemStack(itemStack);
                    nameDisplay.text(itemStack.displayName());
                }

                return true;
            });
        });

        addItemHandlers((BlockPlaceHandler) this::placeBlock);

        setId("item_displayer");
        setIcon(new ItemStack(Material.QUARTZ_SLAB));
        addItemGroup(IRItemGroups.ELECTRIC_MACHINES);
        setEnchantable(false, true);
        setDisenchantable(false, true);
        setWikiText("Item-Displayer");
        setAddon(IndustrialRevival.getInstance());
    }

    private void minusHeight(Location loc) {
        if (getDistanceFromMachine(loc) > 1) {
            int newHeight = getDistanceFromMachine(loc) - 1;
            Location newLoc = loc.clone().add(0, newHeight, 0);
            Location nameLoc = newLoc.clone().add(0, twoDisplaySpacing, 0);
            itemDisplay.teleport(newLoc);
            nameDisplay.teleport(nameLoc);
        }
    }

    private void plusHeight(Location loc) {
        if (getDistanceFromMachine(loc) < maxDistanceFromMachine) {
            int newHeight = getDistanceFromMachine(loc) + 1;
            Location newLoc = loc.clone().add(0, newHeight, 0);
            Location nameLoc = newLoc.clone().add(0, twoDisplaySpacing, 0);
            itemDisplay.teleport(newLoc);
            nameDisplay.teleport(nameLoc);
        }
    }

    private int getDistanceFromMachine(Location loc) {
        IRBlockData data = DataUtil.getBlockData(loc);
        if (data == null) {
            return defaultDistanceFromMachine;
        } else {
            return data.getConfig().getInt("distance_from_machine", defaultDistanceFromMachine);
        }
    }

    private void placeBlock(IRBlockPlaceEvent e) {
        Block self = e.getOriginalEvent().getBlockPlaced();
        Location selfLoc = self.getLocation();
        Location itemLoc = selfLoc.clone().add(0, defaultDistanceFromMachine, 0);
        Location nameLoc = itemLoc.clone().add(0, twoDisplaySpacing, 0);
        World world = selfLoc.getWorld();
        itemDisplay = world.spawn(itemLoc, ItemDisplay.class);
        nameDisplay = world.spawn(nameLoc, TextDisplay.class);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{13};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[0];
    }
}
