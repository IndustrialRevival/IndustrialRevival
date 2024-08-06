package org.irmc.industrialrevival.api.machines.process;

import org.irmc.industrialrevival.api.items.attributes.ItemAttribute;

import javax.annotation.Nonnull;

public interface ProcessorHolder<T extends MachineOperation> extends ItemAttribute {
    @Nonnull MachineProcessor<T> getProcessor();
}
