package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.irmc.industrialrevival.api.objects.events.ir.IndustrialRevivalFinalizedEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PostSetupTask implements Consumer<WrappedTask> {
    private static final List<Runnable> postSetupTasks = new ArrayList<>();

    static {
        PostSetupTask.addPostSetupTask(() -> Bukkit.getPluginManager().callEvent(new IndustrialRevivalFinalizedEvent()));
    }

    @Getter
    private boolean death;

    public static void addPostSetupTask(Runnable task) {
        postSetupTasks.add(task);
    }

    @Override
    public void accept(WrappedTask wrappedTask) {
        if (death) {
            return;
        }

        death = true;
        for (var task : postSetupTasks) {
            try {
                task.run();
            } catch (Exception e) {
                IRDock.getPlugin().getLogger().warning("Failed to run post setup task: " + task);
                e.printStackTrace();
            }
        }
    }
}
