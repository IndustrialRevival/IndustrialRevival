package org.irmc.industrialrevival.api.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public interface IRInventoryHolder extends InventoryHolder {
  @Override
  @NotNull Inventory getInventory();
}
