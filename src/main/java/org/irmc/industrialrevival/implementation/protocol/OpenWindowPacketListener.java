package org.irmc.industrialrevival.implementation.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class OpenWindowPacketListener extends PacketAdapter {
    public OpenWindowPacketListener() {
        super(IndustrialRevival.getInstance(), ListenerPriority.HIGH, PacketType.Play.Server.OPEN_WINDOW);
    }

    @Override
    public void onPacketReceiving(PacketEvent e) {
        Player p = e.getPlayer();
        if (p == null) {
            return;
        }

        if (p.getOpenInventory().getTopInventory().getHolder() instanceof MachineMenu mm) {
            IndustrialRevivalItem item = mm.getIRItem();
            if (item == null) {
                return;
            }

            final PacketContainer packet = e.getPacket();
            StructureModifier<WrappedChatComponent> modifier = packet.getChatComponents();
            WrappedChatComponent wrappedChatComponent = modifier.read(0);
            JSONComponentSerializer serializer = JSONComponentSerializer.json();
            Component component = serializer.deserialize(wrappedChatComponent.getJson());
            if (!item.getItemName().equals(component)) {
                return;
            }

            modifier.write(0, WrappedChatComponent.fromJson(

            ));
        }
    }
}
