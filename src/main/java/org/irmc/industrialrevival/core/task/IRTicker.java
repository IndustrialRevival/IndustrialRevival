package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IRTicker implements Consumer<WrappedTask> {
    private final Supplier<Map<Location, IRBlockData>> blockDataSupplier =
            IndustrialRevival.getInstance().getBlockDataService()::getBlockDataMap;

    @Override
    public void accept(WrappedTask wrappedTask) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
    }
}
