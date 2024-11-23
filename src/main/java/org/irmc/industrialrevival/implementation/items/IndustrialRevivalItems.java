package org.irmc.industrialrevival.implementation.items;

import org.bukkit.Material;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;

public class IndustrialRevivalItems {
    // Debug items
    public static final IndustrialRevivalItemStack EMPTY = new IndustrialRevivalItemStack("EMPTY", Material.STRUCTURE_VOID);
    public static final IndustrialRevivalItemStack CONTAINER = new IndustrialRevivalItemStack("CONTAINER", Material.GLASS);
    public static final IndustrialRevivalItemStack DEBUG_HEAD = new IndustrialRevivalItemStack("DEBUG_HEAD", Material.PLAYER_HEAD);
    public static final IndustrialRevivalItemStack DEBUGGER = new IndustrialRevivalItemStack("DEBUGGER", Material.CLOCK);

    // Multi-block items
    public static final IndustrialRevivalItemStack BLAST_FURNACE = new IndustrialRevivalItemStack("BLAST_FURNACE", Material.BLAST_FURNACE);
    public static final IndustrialRevivalItemStack COKE_OVEN = new IndustrialRevivalItemStack("COKE_OVEN", Material.BRICKS);
    public static final IndustrialRevivalItemStack EARTH_FURNACE = new IndustrialRevivalItemStack("EARTH_FURNACE", Material.PACKED_MUD);
    public static final IndustrialRevivalItemStack WOODEN_PRESS = new IndustrialRevivalItemStack("WOODEN_PRESS", Material.PISTON);
    public static final IndustrialRevivalItemStack RESEARCH_TABLE = new IndustrialRevivalItemStack("RESEARCH_TABLE", Material.CRAFTING_TABLE);
    public static final IndustrialRevivalItemStack PROFESSIONAL_LABORATORY = new IndustrialRevivalItemStack("PROFESSIONAL_LABORATORY", Material.LECTERN);
}
