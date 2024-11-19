package org.irmc.industrialrevival.api.pipe;

import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PipeNetwork {
    private final Map<Location, Pipe> pipes = new HashMap<>();
    private final PipeServer pipeServer;
    private final Pipe serverPipe;
    public PipeNetwork(PipeServer pipeServer) {
        this.pipeServer = pipeServer;
        this.serverPipe = pipeServer.getPipe();
    }

    public void searchPipes(Location from) {
        Pipe pipe = PipeRecorder.getPipe(from);

        if (pipe == null) {
            return;
        }

        for (PipeFace face : pipe.getPipeFaces().getFaces()) {
            Pipe relativePipe = pipe.getRelative(face);

            if (relativePipe != null && !pipes.containsKey(relativePipe.getLocation())) {
                pipes.put(relativePipe.getLocation(), relativePipe);
            }

            if (relativePipe != null) {
                searchPipes(relativePipe.getLocation());
            }
        }
    }
}
