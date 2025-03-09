package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.irmc.industrialrevival.api.elements.melt.MeltedObject;

import java.util.List;

@Getter
public class MeltMethod implements ProduceMethod {
    private final List<MeltedObject> inputs;
    private final List<MeltedObject> outputs;
    public MeltMethod(List<MeltedObject> inputs, List<MeltedObject> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }
}
