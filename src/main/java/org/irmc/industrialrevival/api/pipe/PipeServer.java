package org.irmc.industrialrevival.api.pipe;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

@Deprecated(forRemoval = true)
public interface PipeServer {
    @NotNull Pipe getPipe();

    @NotNull PipeFlowType getType();

    @NotNull
    default Location getPipeLocation() {
        return getPipe().getLocation();
    }

    @NotNull List<ItemStack> getContents();

    boolean isWhitelist();

    default boolean isBlacklist() {
        return !isWhitelist();
    }

    boolean compatibleDictionary();

    @Nullable
    Location getConnectedContainerLocation();
}
