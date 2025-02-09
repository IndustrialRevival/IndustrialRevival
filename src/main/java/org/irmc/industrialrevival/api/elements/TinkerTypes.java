package org.irmc.industrialrevival.api.elements;

import org.irmc.industrialrevival.utils.KeyUtil;

/**
 * Built-in Tinker types.
 *
 * @author balugaq
 * @since 1.0
 */
public class TinkerTypes {
    public static final int INGOT_LEVEL = 144;
    public static final TinkerType ORE = new IRTinkerType(KeyUtil.customKey("tinker_ore"), (int) INGOT_LEVEL);
    public static final TinkerType NUGGET = new IRTinkerType(KeyUtil.customKey("tinker_nugget"), (int) INGOT_LEVEL / 9);
    public static final TinkerType INGOT = new IRTinkerType(KeyUtil.customKey("tinker_ingot"), (int) INGOT_LEVEL);
    public static final TinkerType BLOCK = new IRTinkerType(KeyUtil.customKey("tinker_block"), (int) INGOT_LEVEL * 9);
    public static final TinkerType BUCKET = new IRTinkerType(KeyUtil.customKey("tinker_bucket"), (int) INGOT_LEVEL * 9);
    public static final TinkerType GEM = new IRTinkerType(KeyUtil.customKey("tinker_gem"), (int) INGOT_LEVEL * 3);
    public static final TinkerType PICKAXE_HEAD = new IRTinkerType(KeyUtil.customKey("tinker_pickaxe_head"), (int) INGOT_LEVEL * 3);
    public static final TinkerType SHOVEL_HEAD = new IRTinkerType(KeyUtil.customKey("tinker_shovel_head"), (int) INGOT_LEVEL * 3);
    public static final TinkerType AXE_HEAD = new IRTinkerType(KeyUtil.customKey("tinker_axe_head"), (int) INGOT_LEVEL * 3);
    public static final TinkerType HOE_HEAD = new IRTinkerType(KeyUtil.customKey("tinker_hoe_head"), (int) INGOT_LEVEL * 3);
    public static final TinkerType SWORD_HEAD = new IRTinkerType(KeyUtil.customKey("tinker_sword_head"), (int) INGOT_LEVEL * 3);
    public static final TinkerType ROD = new IRTinkerType(KeyUtil.customKey("tinker_tool_rod"), (int) INGOT_LEVEL * 3);
    public static final TinkerType PLATE = new IRTinkerType(KeyUtil.customKey("tinker_plate"), (int) INGOT_LEVEL * 9);
}
