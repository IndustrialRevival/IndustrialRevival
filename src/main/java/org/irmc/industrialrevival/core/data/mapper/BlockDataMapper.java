package org.irmc.industrialrevival.core.data.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.object.BlockMenuItem;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.jetbrains.annotations.Nullable;

@Mapper
public interface BlockDataMapper {
    @Insert(
            "INSERT INTO block_record (world, x, y, z, machineId, data) VALUES (#{loc.getWorld().getName()}, #{loc.getBlockX()}, #{loc.getBlockY()}, #{loc.getBlockZ()}, #{machineId}, NULL)")
    void blockPlacing(@Param("loc") Location loc, String machineId);

    @Update(
            "DELETE FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND machineId = #{machineId}")
    void blockRemoving(@Param("loc") Location loc, String machineId);

    @Nullable @Select(
            "SELECT machineId FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockId(@Param("loc") Location loc);

    @Nullable @Select(
            "SELECT data FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockData(@Param("loc") Location loc);

    @Select("SELECT * FROM block_record")
    List<BlockRecord> getAllBlockRecords();

    @Update(
            "UPDATE block_record SET data = #{data} WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    void saveBlockData(@Param("loc") Location loc, String data);

    @Insert(
            "INSERT INTO menu_items (world, x, y, z, slot, itemJson, itemClass) VALUES (#{loc.getWorld().getName()}, #{loc.getBlockX()}, #{loc.getBlockY()}, #{loc.getBlockZ()}, #{slot}, #{itemJson}, #{itemClass})")
    void insertMenuItem(@Param("loc") Location loc, int slot, String itemJson, Class<? extends ItemStack> itemClass);

    @Update(
            "UPDATE menu_items SET itemJson = #{itemJson} WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND slot = #{slot} AND itemClass = #{itemClass}")
    void updateMenuItem(@Param("loc") Location loc, int slot, String itemJson, Class<? extends ItemStack> itemClass);

    @Delete(
            "DELETE FROM menu_items WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND slot = #{slot}")
    void deleteMenuItem(@Param("loc") Location loc, int slot);

    @Delete(
            "DELETE FROM menu_items WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    void deleteMenuItems(@Param("loc") Location loc);

    @Select(
            "SELECT * FROM menu_items WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND slot = #{slot} LIMIT 1")
    BlockMenuItem getMenuItem(@Param("loc") Location loc, int slot);
}
