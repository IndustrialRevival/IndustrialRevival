package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.recipes.MobDropMethod;

import java.util.HashSet;
import java.util.Set;

@Builder
public class DropFromMobItem extends IndustrialRevivalItem implements MobDropItem {
    @Getter
    private final Set<MobDropMethod> dropMethods = new HashSet<>();

    @Override
    public MobDropMethod[] getDropMethods() {
        return dropMethods.toArray(new MobDropMethod[0]);
    }

    public DropFromMobItem addDropMethod(MobDropMethod dropMethod) {
        checkRegistered();
        dropMethods.add(dropMethod);
        return this;
    }
}
