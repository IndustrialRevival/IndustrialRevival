package org.irmc.industrialrevival.api.pipe.servers;

import lombok.Getter;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.pipe.Pipe;
import org.irmc.industrialrevival.api.pipe.PipeFlowType;
import org.irmc.industrialrevival.api.pipe.PipeServer;

@Getter
public class Pusher extends Pipe implements PipeServer {

    public Pusher(Location location) {
        super(location);
    }

    @Override
    public Pipe getPipe() {
        return this;
    }

    @Override
    public PipeFlowType getType() {
        return PipeFlowType.PUSH;
    }
}
