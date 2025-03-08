package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import lombok.Setter;
import org.irmc.industrialrevival.api.recipes.MeltMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A tank serves for {@link Smeltery}
 * Used to store melted objects and fuel
 */
@Getter
public class MeltedTank implements Cloneable {
    private final @NotNull List<MeltedObject> meltedObjects;
    @Setter
    private int capacity;
    @Setter
    private int fuels;

    public MeltedTank() {
        this.meltedObjects = new ArrayList<>();
        this.capacity = Smeltery.MAX_FUEL_CAPACITY;
        this.fuels = 0;
    }

    public MeltedTank(@NotNull List<MeltedObject> meltedObjects, int capacity, int fuels) {
        this.meltedObjects = meltedObjects;
        this.capacity = capacity;
        this.fuels = fuels;
    }

    public boolean isFull() {
        int hold = 0;
        for (MeltedObject meltedObject : meltedObjects) {
            hold += meltedObject.getAmount();
        }

        return hold >= capacity;
    }

    public boolean isEmpty() {
        int hold = 0;
        for (MeltedObject meltedObject : meltedObjects) {
            hold += meltedObject.getAmount();
        }

        return hold == 0;
    }

    public boolean hasFuel() {
        return fuels > 0;
    }

    public void addFuel(int fuel) {
        this.fuels += fuel;
    }

    public void removeFuel(int fuel) {
        this.fuels -= fuel;
    }

    public void addMelted(@NotNull MeltedObject meltedObject) {
        for (MeltedObject obj : meltedObjects) {
            if (obj.getType().getIdentifier().equals(meltedObject.getType().getIdentifier())) {
                // check capacity
                if (obj.getAmount() + meltedObject.getAmount() > capacity) {
                    return;
                }
                obj.setAmount(obj.getAmount() + meltedObject.getAmount());
                return;
            }
        }

        // new object, add it
        meltedObjects.add(meltedObject);
    }

    public void addMelted(MeltedObject @NotNull ... meltedObject) {
        for (MeltedObject obj : meltedObject) {
            addMelted(obj);
        }
    }

    public void removeMelted(@NotNull MeltedObject meltedObject) {
        for (MeltedObject obj : meltedObjects) {
            if (obj.getType().getName().equals(meltedObject.getType().getName())) {
                obj.setAmount(obj.getAmount() - meltedObject.getAmount());
                if (obj.getAmount() <= 0) {
                    meltedObjects.remove(obj);
                }
                return;
            }
        }
    }

    public void removeMelted(MeltedObject @NotNull ... meltedObject) {
        for (MeltedObject obj : meltedObject) {
            removeMelted(obj);
        }
    }

    public void clear() {
        meltedObjects.clear();
    }

    public void addAll(@NotNull MeltedTank tank) {
        for (MeltedObject meltedObject : tank.getMeltedObjects()) {
            addMelted(meltedObject);
        }
        setCapacity(tank.getCapacity());
        setFuels(tank.getFuels());
    }

    public void removeAll(@NotNull MeltedTank tank) {
        for (MeltedObject meltedObject : tank.getMeltedObjects()) {
            removeMelted(meltedObject);
        }
        setCapacity(tank.getCapacity());
        setFuels(tank.getFuels());
    }

    public void merge(@NotNull MeltedTank tank) {
        addAll(tank);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MeltedTank meltedTank) {
            return equals(meltedTank);
        }

        return false;
    }

    public boolean equals(@NotNull MeltedTank tank) {
        if (this.capacity != tank.getCapacity()) {
            return false;
        }
        if (this.fuels != tank.getFuels()) {
            return false;
        }
        if (this.meltedObjects.size() != tank.getMeltedObjects().size()) {
            return false;
        }
        for (MeltedObject meltedObject : meltedObjects) {
            boolean found = false;
            for (MeltedObject obj : tank.getMeltedObjects()) {
                if (obj.getType().getName().equals(meltedObject.getType().getName())) {
                    if (obj.getAmount() != meltedObject.getAmount()) {
                        return false;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public boolean matchRecipe(@NotNull MeltMethod meltMethod) {
        List<MeltedObject> inputs = meltMethod.getInputs();
        if (inputs.size() > meltedObjects.size()) {
            return false;
        }
        int consumeFuel = 0;
        for (MeltedObject input : inputs) {
            consumeFuel += input.getAmount();
        }

        if (consumeFuel > fuels) {
            return false;
        }

        int size = meltedObjects.size();
        for (int i = 0; i < size; i++) {
            boolean found = true;
            for (int j = 0; j < inputs.size(); j++) {
                if (i + j < size) {
                    if (!meltedObjects.get(i + j).getType().equals(inputs.get(j).getType())) {
                        found = false;
                        break;
                    } else {
                        j++;
                    }
                }
            }

            if (found) {
                return true;
            }
        }

        return false;
    }

    public void consume(@NotNull MeltMethod meltMethod) {
        List<MeltedObject> inputs = meltMethod.getInputs();
        for (MeltedObject input : inputs) {
            removeMelted(input);
            fuels -= input.getAmount();
        }
    }

    public void produce(@NotNull MeltMethod meltMethod) {
        List<MeltedObject> outputs = meltMethod.getOutputs();
        for (MeltedObject output : outputs) {
            addMelted(output);
        }
    }

    public void performRecipe(@NotNull MeltMethod meltMethod) {
        performRecipe(meltMethod, false);
    }
    public void performRecipe(@NotNull MeltMethod meltMethod, boolean force) {
        if (!force && !matchRecipe(meltMethod)) {
            return;
        }
        consume(meltMethod);
        produce(meltMethod);
    }

    @Nullable
    public MeltedObject getBottomObject() {
        if (meltedObjects.isEmpty()) {
            return null;
        }
        return meltedObjects.get(0);
    }

    @Nullable
    public MeltedObject getTopObject() {
        if (meltedObjects.isEmpty()) {
            return null;
        }
        return meltedObjects.get(meltedObjects.size() - 1);
    }

    @Override
    public @NotNull MeltedTank clone() {
        return new MeltedTank(new ArrayList<>(meltedObjects), capacity, fuels);
    }
}
