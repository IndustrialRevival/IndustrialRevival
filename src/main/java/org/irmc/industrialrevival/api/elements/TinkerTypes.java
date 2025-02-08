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
}
