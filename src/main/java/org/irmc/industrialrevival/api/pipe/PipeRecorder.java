package org.irmc.industrialrevival.api.pipe;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Deprecated(forRemoval = true)
@UtilityClass
public class PipeRecorder {
    // temp solution, will be replaced by database or file storage
    private static final Map<Location, Pipe> pipes = new HashMap<>();

    public static boolean hasPipe(@NotNull Location location) {
        return getPipe(location) != null;
    }

    public static Pipe getPipe(@NotNull Location location) {
        return pipes.get(location);
    }

    public static void addPipe(@NotNull Location location, @NotNull Pipe pipe) {
        pipes.put(location, pipe);
    }

    public static void removePipe(@NotNull Location location) {
        pipes.remove(location);
    }

    public static void save() {
        // todo
        return;
    }
}
