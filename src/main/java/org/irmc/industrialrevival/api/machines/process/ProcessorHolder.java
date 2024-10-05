package org.irmc.industrialrevival.api.machines.process;

import org.irmc.industrialrevival.api.items.attributes.ItemAttribute;
import org.jetbrains.annotations.NotNull;

public interface ProcessorHolder<T extends IOperation> extends ItemAttribute {
    @NotNull
    MachineProcessor<T> getProcessor();
}
