package org.irmc.industrialrevival.api.machines.process;

public interface IOperation {
    void tick();

    void addProgress(int progress);

    int getCurrentProgress();

    int getTotalProgress();

    default int getRemainingDuration() {
        return getTotalProgress() - getCurrentProgress();
    }

    default boolean isDone() {
        return getCurrentProgress() >= getTotalProgress();
    }

    default void onCancel() {}
}
