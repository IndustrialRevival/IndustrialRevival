package org.irmc.industrialrevival.api.machines.process;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MachineMenu;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MachineProcessor<T extends IOperation> {
    private final Map<Location, T> ALL_MACHINES = new ConcurrentHashMap<>();
    private @Getter final ProcessorHolder<T> holder;
    private @Getter @Setter ItemStack progressBarItem;

    public MachineProcessor(ProcessorHolder<T> holder) {
        this.holder = holder;
    }

    public void startProcess(Location location, T operation) {
        ALL_MACHINES.put(location, operation);
    }

    public void startProcess(Block block, T operation) {
        ALL_MACHINES.put(block.getLocation(), operation);
    }

    public void startProcess(MachineMenu menu, T operation) {
        ALL_MACHINES.put(menu.getLocation(), operation);
    }

    public boolean stopProcess(Location location) {
        T op = ALL_MACHINES.remove(location);
        if (op != null) {
            if (!op.isDone()) {
                op.onCancel();
            }
            return true;
        }
        return false;
    }

    public boolean stopProcess(Block block) {
        return stopProcess(block.getLocation());
    }

    public boolean stopProcess(MachineMenu menu) {
        return stopProcess(menu.getLocation());
    }

    public T getProcess(Location location) {
        return ALL_MACHINES.get(location);
    }

    public T getProcess(Block block) {
        return ALL_MACHINES.get(block.getLocation());
    }

    public T getProcess(MachineMenu menu) {
        return ALL_MACHINES.get(menu.getLocation());
    }

    public void updateProgressBar(MachineMenu menu, int slot, T operation) {
        menu.setProgressItem(slot, operation.getRemainingDuration(), operation.getTotalDuration(), getProgressBarItem());
    }
}
