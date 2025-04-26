package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Getter
public class DropFromBlockItem extends IndustrialRevivalItem implements BlockDropItem {
    private final Set<BlockDropMethod> blockDropMethods = new HashSet<>();

    @Override
    public BlockDropMethod[] getDropMethods() {
        return blockDropMethods.toArray(new BlockDropMethod[0]);
    }

    public DropFromBlockItem addDropMethod(@NotNull BlockDropMethod blockDropMethod) {
        checkRegistered();
        blockDropMethods.add(blockDropMethod);
        return this;
    }
}
