package org.irmc.industrialrevival.api.pipe;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class PipeFaces {
    private final Set<PipeFace> faces = new HashSet<>();

    public PipeFaces() {

    }

    public PipeFaces(List<PipeFace> faces) {
        this.faces.addAll(faces);
    }

    public boolean isUp() {
        return faces.contains(PipeFace.UP);
    }

    public boolean isDown() {
        return faces.contains(PipeFace.DOWN);
    }

    public boolean isNorth() {
        return faces.contains(PipeFace.NORTH);
    }

    public boolean isSouth() {
        return faces.contains(PipeFace.SOUTH);
    }

    public boolean isWest() {
        return faces.contains(PipeFace.WEST);
    }

    public boolean isEast() {
        return faces.contains(PipeFace.EAST);
    }
}
