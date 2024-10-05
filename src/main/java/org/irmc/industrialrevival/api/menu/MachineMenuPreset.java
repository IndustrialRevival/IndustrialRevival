package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

import javax.annotation.NotNull;
import javax.annotation.Nullable;

@Getter
public class MachineMenuPreset extends SimpleMenu {
    private final String id;

    private boolean locked;

    public MachineMenuPreset(@NotNull String id, @NotNull Component title) {
        super(title);

        this.id = id;
        this.locked = false;
    }

    protected void addMenuDrawer(@NotNull MenuDrawer drawer) {
        int i = 0, j = 0;
        for (String line : drawer.getMatrix()) {
            for (char slotSymbol : line.toCharArray()) {
                if (drawer.getCharMap().containsKey(slotSymbol)) {
                    setItem(i*9+j, drawer.getCharMap().get(slotSymbol));
                }
                j += 1;
            }
            i += 1;
        }
    }

    public void init() {

    }

    public void newInstance(@NotNull Block block, @Nullable MachineMenu menu) {

    }

    public void register() {
        if (IndustrialRevival.getInstance().getRegistry().getMenuPresets().containsKey(this.id)) {
            throw new IllegalStateException("Already registered menu preset with id " + this.id);
        }

        this.locked = true;

        IndustrialRevival.getInstance().getRegistry().getMenuPresets().put(this.id, this);
    }

    public void setItem(int slot, @Nullable ItemStack itemStack) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set item of locked machine menu!");
        }

        super.setItem(slot, itemStack);
    }

    public void setItem(int slot, ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set item of locked machine menu!");
        }

        super.setItem(slot, itemStack, clickHandler);
    }

    public void setTitle(@NotNull Component title) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set title of locked machine menu!");
        }

        super.setTitle(title);
    }

    public void setSize(int size) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set size of locked machine menu!");
        }

        super.setSize(size);
    }

    public void setClickHandler(int slot, @NotNull ClickHandler clickHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set click handler of locked machine menu!");
        }

        super.setClickHandler(slot, clickHandler);
    }

    public void setCloseHandler(@NotNull MenuCloseHandler closeHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set close handler of locked machine menu!");
        }

        super.setCloseHandler(closeHandler);
    }

    public void setOpenHandler(@NotNull MenuOpenHandler openHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set open handler of locked machine menu!");
        }

        super.setOpenHandler(openHandler);
    }

    public int[] getSlotsByItemFlow(ItemFlow itemFlow) {
        return getSlotsByItemFlow(itemFlow, null);
    }

    public int[] getSlotsByItemFlow(ItemFlow itemFlow, ItemStack itemStack) {
        return new int[0];
    }
}
