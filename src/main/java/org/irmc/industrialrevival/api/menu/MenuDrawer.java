package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.pigeonlib.items.ItemUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MenuDrawer {
    private final int size;
    private final Map<Character, ItemStack> charMap = new HashMap<>();
    private final List<String> matrix = new ArrayList<>();

    public MenuDrawer(int size) {
        this.size = size;
    }

    public MenuDrawer addLine(String line) {
        matrix.add(line);
        return this;
    }

    public MenuDrawer addExplain(char c, @Nonnull ItemStack itemStack) {
        charMap.put(c, new ItemStack(itemStack));
        return this;
    }

    public MenuDrawer addExplain(String c, @Nonnull ItemStack itemStack) {
        charMap.put(c.charAt(0), new ItemStack(itemStack));
        return this;
    }
}
