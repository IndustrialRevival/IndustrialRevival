package org.irmc.industrialrevival.api.pipe.servers;

import lombok.Getter;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.pipe.Pipe;
import org.irmc.industrialrevival.api.pipe.PipeFlowType;
import org.irmc.industrialrevival.api.pipe.PipeServer;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class Puller extends Pipe implements PipeServer {
    public Puller(@NotNull Location location) {
        super(location);
    }

    @Override
    public Pipe getPipe() {
        return this;
    }

    @Override
    public PipeFlowType getType() {
        return PipeFlowType.PULL;
    }
}
