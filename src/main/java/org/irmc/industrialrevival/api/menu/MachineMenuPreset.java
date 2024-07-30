package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

@Getter
public class MachineMenuPreset extends SimpleMenu {
    private final String id;

    private boolean locked;

    public MachineMenuPreset(String id, Component title) {
        super(title);

        this.id = id;
        this.locked = false;
    }

    public void register() {
        this.locked = true;

        IndustrialRevival.getInstance().getRegistry().getMenuPresets().put(this.id, this);
    }

    public void setItem(int slot, ItemStack itemStack) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set item of locked machine menu!");
        }

        super.setItem(slot, itemStack);
    }

    public void setItem(int slot, ItemStack itemStack, ClickHandler clickHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set item of locked machine menu!");
        }

        super.setItem(slot, itemStack, clickHandler);
    }

    public void setTitle(Component title) {
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

    public void setClickHandler(int slot, ClickHandler clickHandler) {
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
}
