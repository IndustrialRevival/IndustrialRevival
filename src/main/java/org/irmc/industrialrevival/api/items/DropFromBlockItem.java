package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.recipes.BlockDropMethod;

import java.util.HashSet;
import java.util.Set;

@Builder
public class DropFromBlockItem extends IndustrialRevivalItem implements BlockDropItem {
    @Getter
    private final Set<BlockDropMethod> blockDropMethods = new HashSet<>();

    @Override
    public BlockDropMethod[] getDropMethods() {
        return blockDropMethods.toArray(new BlockDropMethod[0]);
    }

    public DropFromBlockItem addDropMethod(BlockDropMethod blockDropMethod) {
        checkRegistered();
        blockDropMethods.add(blockDropMethod);
        return this;
    }
}
