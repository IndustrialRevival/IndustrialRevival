package org.irmc.industrialrevival.api.multiblock;

import lombok.Getter;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

// todo: structure display in guide
@Getter
@SuppressWarnings("unused")
public abstract class MultiBlock extends IndustrialRevivalItem implements Keyed {
    private final NamespacedKey key;
    private Structure structure;
    private int[] center;

    public MultiBlock(NamespacedKey key) {
        this.key = key;
    }

    public boolean matchStructure(@NotNull Location location) {
        return structure.isValid(location);
    }

    public MultiBlock setStructure(@NotNull Structure structure) {
        checkRegistered();
        this.structure = structure;
        this.center = structure.getCenter();
        return this;
    }

    @Override
    public MultiBlock setAddon(@NotNull IndustrialRevivalAddon addon) {
        super.setAddon(addon);
        return this;
    }

    @Override
    public MultiBlock addItemGroup(@NotNull ItemGroup group) {
        super.addItemGroup(group);
        return this;
    }

    @Override
    public MultiBlock recipe(@NotNull IndustrialRevivalItem.ProduceMethodGetter handler) {
        super.recipe(handler);
        return this;
    }

    @Override
    public MultiBlock setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public MultiBlock setDisabledInWorld(@NotNull World world, boolean disabled, boolean saveToConfig) {
        super.setDisabledInWorld(world, disabled, saveToConfig);
        return this;
    }

    @Override
    public MultiBlock setDisabled(boolean disabled, boolean saveToConfig) {
        super.setDisabled(disabled, saveToConfig);
        return this;
    }

    @Override
    public MultiBlock addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public MultiBlock setEnchantable(boolean enchantable, boolean saveToConfig) {
        super.setEnchantable(enchantable, saveToConfig);
        return this;
    }

    @Override
    public MultiBlock setDisenchantable(boolean disenchantable, boolean saveToConfig) {
        super.setDisenchantable(disenchantable, saveToConfig);
        return this;
    }

    @Override
    public MultiBlock setHideInGuide(boolean hideInGuide, boolean saveToConfig) {
        super.setHideInGuide(hideInGuide, saveToConfig);
        return this;
    }

    public abstract void onInteract(@NotNull PlayerInteractEvent event);
}
