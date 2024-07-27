package org.irmc.industrialrevival.core.data.object;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class BlockMenuItem {
    private String world;
    private int x;
    private int y;
    private int z;
    private int slot;
    private String itemJson;
    private Class<?> itemClass;

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public ItemStack getItemStack() {
        return new Gson().fromJson(itemJson, (Class<? extends ItemStack>) itemClass);
    }
}
