package org.irmc.industrialrevival.api.multiblock;

import lombok.Getter;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.jetbrains.annotations.NotNull;

// todo: 实现右键中心方块，交互多方块
// todo: 多方块注册到registry，玩家交互方块时，for each check 注册的多方块，match时判断是否match多个
@SuppressWarnings("unused")
public class MultiBlock extends IndustrialRevivalItem implements Keyed {
    private @Getter NamespacedKey key;
    private @Getter Structure structure;
    private @Getter int[] center;
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
    public MultiBlock setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public MultiBlock addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public MultiBlock setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public MultiBlock setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public MultiBlock setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public MultiBlock addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    @Override
    public MultiBlock setEnchantable(boolean enchantable) {
        super.setEnchantable(enchantable);
        return this;
    }

    @Override
    public MultiBlock setDisenchantable(boolean disenchantable) {
        super.setDisenchantable(disenchantable);
        return this;
    }

    @Override
    public MultiBlock setHideInGuide(boolean hideInGuide) {
        super.setHideInGuide(hideInGuide);
        return this;
    }
}
