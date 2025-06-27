package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.menu.handlers.MenuCloseHandler;
import org.irmc.industrialrevival.api.menu.handlers.MenuOpenHandler;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MachineMenuPreset extends SimpleMenu {
    private final List<MatrixMenuDrawer> drawers;
    private final NamespacedKey id;

    private boolean locked;

    public MachineMenuPreset(@NotNull NamespacedKey id, @NotNull String title) {
        this(id, LegacyComponentSerializer.legacyAmpersand().deserialize(title));
    }

    public MachineMenuPreset(@NotNull NamespacedKey id, @NotNull Component title) {
        super(title);

        this.id = id;
        this.locked = false;
        this.drawers = new ArrayList<>();
        init();
    }

    /**
     * Called when the menu be registered to the registry.
     * Override this method to initialize the menu BACKGROUND items.
     * We suggest to use {@link #withMenuDrawer(MatrixMenuDrawer)}
     */
    public void init() {

    }

    /**
     * Called when a machine data be loaded.
     * Override this method to initialize the menu items based on the machine data.
     *
     * @param block The block of the machine
     * @param menu  The menu of the machine, null if the machine has no menu.
     */
    public void newInstance(@NotNull Block block, @Nullable MachineMenu menu) {

    }

    public void register() {
        if (IRDock.getPlugin().getRegistry().getMenuPresets().containsKey(this.id)) {
            throw new IllegalStateException("Already registered menu preset with id " + this.id);
        }

        this.locked = true;

        IRDock.getPlugin().getRegistry().getMenuPresets().put(this.id, this);
    }

    public void setItem(@Range(from = 0, to = 53) int slot, @Nullable ItemStack itemStack) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set item of locked machine menu!");
        }

        super.setItem(slot, itemStack);
    }

    public void setItem(@Range(from = 0, to = 53) int slot, @Nullable ItemStack itemStack, @NotNull ClickHandler clickHandler) {
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

    public void setSize(@Range(from = 0, to = 53) int size) {
        if (this.locked) {
            throw new IllegalStateException("Cannot set size of locked machine menu!");
        }

        super.setSize(size);
    }

    public void setClickHandler(@Range(from = 0, to = 53) int slot, @NotNull ClickHandler clickHandler) {
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

    public int[] getSlotsByItemFlow(@NotNull ItemFlow itemFlow) {
        return getSlotsByItemFlow(itemFlow, null);
    }

    public int[] getSlotsByItemFlow(@NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
        return new int[0];
    }

    public MachineMenuPreset withMenuDrawer(@NotNull MatrixMenuDrawer drawer) {
        this.drawers.add(drawer);
        return this;
    }
}
