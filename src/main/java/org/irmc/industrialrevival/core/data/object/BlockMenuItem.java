package org.irmc.industrialrevival.core.data.object;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

@Deprecated
public record BlockMenuItem(String world, int x, int y, int z, int slot, String itemJson, Class<?> itemClass) {
    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public ItemStack getItemStack() {
        return new Gson().fromJson(itemJson, (Class<? extends ItemStack>) itemClass);
    }
}
