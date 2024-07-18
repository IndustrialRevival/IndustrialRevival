package org.irmc.industrialrevival.core.registry;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalOre;
import org.irmc.industrialrevival.api.items.Research;

@Getter
public class IRRegistry {
  public Map<NamespacedKey, ItemGroup> itemGroups;
  public Map<NamespacedKey, Research> researches;
  public Map<String, IndustrialRevivalItem> items;
  public List<IndustrialRevivalOre> ores;
}
