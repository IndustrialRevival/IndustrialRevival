package org.irmc.industrialrevival.api.pipe;

import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

@Deprecated(forRemoval = true)
@Getter
public class Pipe {
    private final PipeFaces pipeFaces = new PipeFaces();
    private final Location location;
    public Pipe(@NotNull Location location) {
        this.location = location;
    }

    public Pipe getRelative(PipeFace pipeFace) {
        return PipeRecorder.getPipe(getRelativeLocation(pipeFace));
    }

    public Location getRelativeLocation(PipeFace pipeFace) {
        return switch (pipeFace) {
            case UP -> location.clone().add(0, 1, 0);
            case DOWN -> location.clone().add(0, -1, 0);
            case NORTH -> location.clone().add(0, 0, 1);
            case SOUTH -> location.clone().add(0, 0, -1);
            case EAST -> location.clone().add(1, 0, 0);
            case WEST -> location.clone().add(-1, 0, 0);
        };
    }
}
