package org.irmc.industrialrevival.core.translation;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.KeyUtil;

public class ItemTranslator {
    public static final NamespacedKey TRANSLATE_KEY = KeyUtil.customKey("ir_translate_key");
    public static void setup() {
        IRDock.getPlugin().getProtocolManager().addPacketListener(new PacketAdapter(
                IRDock.getPlugin(),
                ListenerPriority.NORMAL,
                PacketType.Play.Server.WINDOW_ITEMS
        ) {
            @Override
            public void onPacketSending(PacketEvent event) {
                Debug.log("Translating 1");
                // open inventory
                PacketContainer packet = event.getPacket();
                Debug.log("Type: " + event.getPacketType().name());
                Debug.log("modifiers: " + packet.getModifier());
                Debug.log("array: " + packet.getItemArrayModifier());
                Debug.log("slots:" + packet.getItemSlots());
                Debug.log("items: "+ packet.getItemModifier());
                if  (packet.getItemArrayModifier().size() > 0) {
                    Debug.log("Translating 2");
                    ItemStack[] items = packet.getItemArrayModifier().read(0);

                    for (int i = 0; i < items.length; i++) {
                        Debug.log("Translating 3");
                        if (translatable(items[i])) {
                            items[i] = translateItem(items[i]);
                        }
                    }

                    packet.getItemArrayModifier().write(0, items);
                 }
            }
        });
    }

    public static boolean translatable(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }

        return DataUtil.hasPDC(itemStack.getItemMeta(), TRANSLATE_KEY);
    }

    public static ItemStack translateItem(ItemStack itemStack) {
        final ItemStack clone = itemStack.clone();
        final String translate_key = DataUtil.getPDC(clone.getItemMeta(), TRANSLATE_KEY, PersistentDataType.STRING);
        final ItemMeta meta = clone.getItemMeta();
        meta.displayName(IRDock.getPlugin().getLanguageManager().getItemName(translate_key));
        meta.lore(IRDock.getPlugin().getLanguageManager().getItemLore(translate_key));
        clone.setItemMeta(meta);
        return clone;
    }
}
