package org.irmc.industrialrevival.api.items;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Unusable;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockBreakHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockUseHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityKillHandler;
import org.irmc.industrialrevival.api.items.handlers.ToolUseHandler;
import org.irmc.industrialrevival.api.items.handlers.WeaponUseHandler;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnusableItem extends IndustrialRevivalItem implements Unusable {

    public UnusableItem(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe) {
        super(group, itemStack, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandlers(
                (BlockPlaceHandler) event -> event.setCancelled(true),
                (BlockBreakHandler) event -> event.setCancelled(true),
                (BlockUseHandler) event -> event.setCancelled(true),
                (EntityKillHandler) (event, killer, item) -> event.setCancelled(true),
                (ToolUseHandler) (event, tool, drops) -> event.setCancelled(true),
                (WeaponUseHandler) (event, player, item) -> event.setCancelled(true));
    }
}
