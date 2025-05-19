package org.irmc.industrialrevival.api.pipe;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.industrialrevival.api.pipe.servers.Puller;
import org.irmc.industrialrevival.api.pipe.servers.Pusher;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

@Deprecated(forRemoval = true)
@Getter
public class PipeRoot {
    private final Map<Location, Pipe> pipes = new HashMap<>();
    private final Set<Location> pullers = new HashSet<>();
    private final Set<Location> pushers = new HashSet<>();
    private final PipeServer pipeServer;
    private final Pipe serverPipe;

    public PipeRoot(PipeServer pipeServer) {
        this.pipeServer = pipeServer;
        this.serverPipe = pipeServer.getPipe();
    }

    public void searchPipes(Location from) {
        Stack<Location> stack = new Stack<>();
        stack.push(from);
        while (!stack.isEmpty()) {
            Location current = stack.pop();
            Pipe pipe = PipeRecorder.getPipe(current);

            if (pipe == null) {
                continue;
            }

            for (PipeFace face : pipe.getPipeFaces().getFaces()) {
                Pipe relativePipe = pipe.getRelative(face);

                if (relativePipe != null && !pipes.containsKey(relativePipe.getLocation())) {
                    pipes.put(relativePipe.getLocation(), relativePipe);
                }

                if (relativePipe instanceof PipeServer server) {
                    switch (server.getType()) {
                        case PULL -> pullers.add(relativePipe.getLocation());
                        case PUSH -> pushers.add(relativePipe.getLocation());
                    }
                }

                if (relativePipe != null) {
                    stack.push(relativePipe.getLocation());
                }
            }
        }
    }

    public Map<Location, Pipe> getPipes() {
        if (pipes != null && !pipes.isEmpty()) {
            return Collections.unmodifiableMap(pipes);
        } else {
            searchPipes(serverPipe.getLocation());
        }

        return Collections.unmodifiableMap(pipes);
    }

    public synchronized void tick() {
        for (Location pullerLocation : pullers) {
            Puller puller = (Puller) pipes.get(pullerLocation);

            MachineMenu menu = DataUtil.getMachineMenu(puller.getConnectedContainerLocation());
            if (menu == null) {
                continue;
            }

            List<ItemStack> items = puller.getContents();
            if (items == null || items.isEmpty()) {
                continue;
            }

            boolean isWhitelist = puller.isWhitelist();
            List<ItemStack> pulled = pullItems(menu, items, isWhitelist);

            for (Location pusherLocation : pushers) {
                Pusher pusher = (Pusher) pipes.get(pusherLocation);

                MachineMenu pushMenu = DataUtil.getMachineMenu(pusher.getConnectedContainerLocation());
                if (pushMenu == null) {
                    continue;
                }

                pushItems(pushMenu, pulled, isWhitelist);

                pulled.removeIf(itemStack -> itemStack == null || itemStack.getAmount() <= 0 || itemStack.getType() == Material.AIR || itemStack.getType() == null);

                if (pulled.isEmpty()) {
                    break;
                }
            }
        }
        this.pipes.clear();
    }

    @NotNull
    public List<ItemStack> pullItems(@NotNull MachineMenu menu, @NotNull List<ItemStack> contents, boolean isWhitelist) {
        List<ItemStack> result = new ArrayList<>();
        for (ItemStack target : contents) {
            Set<Integer> slots = new HashSet<>();
            int[] allSlots = menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW);
            int[] whitelistSlots = menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW, target);
            if (isWhitelist) {
                for (int slot : whitelistSlots) {
                    slots.add(slot);
                }
            } else {
                for (int slot : allSlots) {
                    for (int wlSlot : whitelistSlots) {
                        if (slot == wlSlot) {
                            break;
                        }
                    }

                    slots.add(slot);
                }
            }

            for (int slot : slots) {
                ItemStack stack = menu.getItem(slot);
                if (stack == null || stack.getType() == Material.AIR) {
                    continue;
                }

                if (ItemUtils.isItemSimilar(stack, target)) {
                    result.add(stack);
                }
            }
        }
        return result;
    }

    @NotNull
    public void pushItems(@NotNull MachineMenu menu, @NotNull List<ItemStack> contents, boolean isWhitelist) {
        for (ItemStack target : contents) {
            Set<Integer> slots = new HashSet<>();
            int[] allSlots = menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW);
            int[] whitelistSlots = menu.getPreset().getSlotsByItemFlow(ItemFlow.WITHDRAW, target);
            if (isWhitelist) {
                for (int slot : whitelistSlots) {
                    slots.add(slot);
                }
            } else {
                for (int slot : allSlots) {
                    for (int wlSlot : whitelistSlots) {
                        if (slot == wlSlot) {
                            break;
                        }
                    }

                    slots.add(slot);
                }
            }

            int[] pushableSlots = new int[slots.size()];
            int i = 0;
            for (int slot : slots) {
                pushableSlots[i] = slot;
                i++;
            }

            MenuUtil.pushItem(menu, contents, pushableSlots);
        }
    }
}
