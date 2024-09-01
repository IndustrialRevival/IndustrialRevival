package org.irmc.industrialrevival.api.machines.process;

import javax.annotation.Nonnull;
import org.irmc.industrialrevival.api.items.attributes.ItemAttribute;

public interface ProcessorHolder<T extends IOperation> extends ItemAttribute {
    @Nonnull
    MachineProcessor<T> getProcessor();
}
