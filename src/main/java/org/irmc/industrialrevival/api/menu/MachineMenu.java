package org.irmc.industrialrevival.api.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.utils.LanguageManager;

public class MachineMenu extends SimpleMenu {
    private final String id;

    private boolean locked;

    public MachineMenu(String id, Component title) {
        super(title);

        this.id = id;
        this.locked = false;
    }

    public void register() {
        this.locked = true;
    }

    public String getId() {
        return this.id;
    }

    public boolean isLocked() {
        return this.locked;
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

    public void setTitle(String title) {
        setTitle(LanguageManager.parseToComponent(title));
    }

    public void setClickHandler(int slot, ClickHandler clickHandler) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set click handler of locked machine menu!");
        }

        super.setClickHandler(slot, clickHandler);
    }
}
