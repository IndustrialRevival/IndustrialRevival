package org.irmc.industrialrevival.api.elements.melt;

import lombok.Getter;
import lombok.Setter;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.recipes.MeltMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A tank serves for {@link Smeltery}
 * Used to store melted objects and fuel
 * @author balugaq
 * @see Smeltery
 */
@Getter
public class MeltedTank implements Cloneable {
    private final @NotNull List<MeltedObject> meltedObjects;
    @Setter
    private int capacity;
    @Setter
    private int fuels;

    /**
     * Create a new empty tank
     */
    public MeltedTank() {
        this.meltedObjects = new ArrayList<>();
        this.capacity = Smeltery.MAX_FUEL_CAPACITY;
        this.fuels = 0;
    }

    /**
     * Create a new tank with initial melted objects and fuel
     * @param meltedObjects initial melted objects
     * @param capacity capacity of the tank
     * @param fuels initial fuel amount
     */
    public MeltedTank(@NotNull List<MeltedObject> meltedObjects, int capacity, int fuels) {
        this.meltedObjects = meltedObjects;
        this.capacity = capacity;
        this.fuels = fuels;
    }

    /**
     * Check if the tank is full
     * @return true if the tank is full, false otherwise
     */
    public boolean isFull() {
        int hold = 0;
        for (MeltedObject meltedObject : meltedObjects) {
            hold += meltedObject.getAmount();
        }

        return hold >= capacity;
    }

    /**
     * Check if the tank is empty
     * @return true if the tank is empty, false otherwise
     */
    public boolean isEmpty() {
        int hold = 0;
        for (MeltedObject meltedObject : meltedObjects) {
            hold += meltedObject.getAmount();
        }

        return hold == 0;
    }

    /**
     * Check if the tank has fuel
     * @return true if the tank has fuel, false otherwise
     */
    public boolean hasFuel() {
        return fuels > 0;
    }

    /**
     * Add fuel to the tank
     * @param fuel amount of fuel to add
     */
    public void addFuel(int fuel) {
        this.fuels += fuel;
    }

    /**
     * Remove fuel from the tank
     * @param fuel amount of fuel to remove
     */
    public void removeFuel(int fuel) {
        this.fuels -= fuel;
    }

    /**
     * Add a melted object to the tank
     * @param meltedObject the melted object to add
     */
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

    /**
     * Add a melted object to the tank
     * @param meltedObject the melted object to add
     */
    public void addMelted(MeltedObject @NotNull ... meltedObject) {
        for (MeltedObject obj : meltedObject) {
            addMelted(obj);
        }
    }

    /**
     * Remove a melted object from the tank
     * @param meltedObject the melted object to remove
     */
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

    /**
     * Remove a melted object from the tank
     * @param meltedObject the melted object to remove
     */
    public void removeMelted(MeltedObject @NotNull ... meltedObject) {
        for (MeltedObject obj : meltedObject) {
            removeMelted(obj);
        }
    }

    /**
     * Clear the tank
     * @apiNote Be careful when using this method, it will remove all melted objects and fuel!
     */
    public void clear() {
        meltedObjects.clear();
    }

    /**
     * Add all melted objects and fuel from another tank to this tank
     * @param tank the tank to add from
     */
    public void addAll(@NotNull MeltedTank tank) {
        for (MeltedObject meltedObject : tank.getMeltedObjects()) {
            addMelted(meltedObject);
        }
        setCapacity(tank.getCapacity());
        setFuels(tank.getFuels());
    }

    /**
     * Remove all melted objects and fuel from another tank from this tank
     * @param tank the tank to remove from
     */
    public void removeAll(@NotNull MeltedTank tank) {
        for (MeltedObject meltedObject : tank.getMeltedObjects()) {
            removeMelted(meltedObject);
        }
        setCapacity(tank.getCapacity());
        setFuels(tank.getFuels());
    }

    /**
     * Merge another tank into this tank
     * @param tank the tank to merge into this tank
     */
    public void merge(@NotNull MeltedTank tank) {
        addAll(tank);
    }

    /**
     * Check if this tank is equal to another tank
     * @param obj the object to compare to
     * @return true if the two tanks are equal, false otherwise
     */
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

    /**
     * Check if this tank is equal to another tank
     * @param tank the tank to compare to
     * @return true if the two tanks are equal, false otherwise
     */
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

    /**
     * Check if this tank can perform the given recipe
     * @param meltMethod the recipe to check
     * @return true if the tank can perform the recipe, false otherwise
     */
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

    /**
     * Consume the given inputs
     * @param meltMethod the recipe to get inputs
     */
    public void consume(@NotNull MeltMethod meltMethod) {
        List<MeltedObject> inputs = meltMethod.getInputs();
        for (MeltedObject input : inputs) {
            removeMelted(input);
            fuels -= input.getAmount();
        }
    }

    /**
     * Produce the given products
     * @param meltMethod the recipe to get products
     */
    public void produce(@NotNull MeltMethod meltMethod) {
        List<MeltedObject> outputs = meltMethod.getOutputs();
        for (MeltedObject output : outputs) {
            addMelted(output);
        }
    }

    /**
     * Perform the given recipe
     * @param meltMethod the recipe to perform
     */
    public void performRecipe(@NotNull MeltMethod meltMethod) {
        performRecipe(meltMethod, false);
    }

    /**
     * Perform the given recipe
     * @param meltMethod the recipe to perform
     * @param force true to force the recipe, false to check if the tank can perform the recipe
     */
    public void performRecipe(@NotNull MeltMethod meltMethod, boolean force) {
        if (!force && !matchRecipe(meltMethod)) {
            return;
        }
        consume(meltMethod);
        produce(meltMethod);
    }

    /**
     * Get the bottom object in the tank (the first object)
     * @return the bottom object in the tank, or null if the tank is empty
     */
    @Nullable
    public MeltedObject getBottomObject() {
        if (meltedObjects.isEmpty()) {
            return null;
        }
        return meltedObjects.get(0);
    }

    /**
     * Get the top object in the tank (the last object)
     * @return the top object in the tank, or null if the tank is empty
     */
    @Nullable
    public MeltedObject getTopObject() {
        if (meltedObjects.isEmpty()) {
            return null;
        }
        return meltedObjects.get(meltedObjects.size() - 1);
    }

    /**
     * Clone the tank
     * @return a new tank with the same content as this tank
     */
    @Override
    public @NotNull MeltedTank clone() {
        return new MeltedTank(new ArrayList<>(meltedObjects), capacity, fuels);
    }
}
