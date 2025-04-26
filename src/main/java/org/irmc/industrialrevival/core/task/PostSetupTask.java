package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.api.objects.events.ir.IndustrialRevivalFinalizedEvent;
import org.irmc.industrialrevival.api.objects.events.ir.TickDoneEvent;
import org.irmc.industrialrevival.api.objects.events.ir.TickStartEvent;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PostSetupTask implements Consumer<WrappedTask> {
    private static final List<Runnable> postSetupTasks = new ArrayList<>();
    static {
        PostSetupTask.addPostSetupTask(() -> Bukkit.getPluginManager().callEvent(new IndustrialRevivalFinalizedEvent()));
    }

    @Getter
    private boolean death;

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
                IndustrialRevival.getInstance().getLogger().warning("Failed to run post setup task: " + task);
                e.printStackTrace();
            }
        }
    }

    public static void addPostSetupTask(Runnable task) {
        postSetupTasks.add(task);
    }
}
