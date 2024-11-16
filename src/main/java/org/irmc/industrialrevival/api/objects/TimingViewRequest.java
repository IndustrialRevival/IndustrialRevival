package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
public class TimingViewRequest {
    private final CommandSender requester;
    private final long timestamp;

    public TimingViewRequest(CommandSender requester) {
        this.requester = requester;
        this.timestamp = System.currentTimeMillis();
    }
}
