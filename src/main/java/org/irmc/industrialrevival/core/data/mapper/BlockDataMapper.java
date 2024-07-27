package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.bukkit.Location;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mapper
public interface BlockDataMapper {
    @Insert(
            "INSERT INTO block_record (world, x, y, z, machine_id, data) VALUES (#{loc.getWorld().getName()}, #{loc.getBlockX()}, #{loc.getBlockY()}, #{loc.getBlockZ()}, #{machineId}, NULL)")
    void blockPlacing(@Param("loc") Location loc, String machineId);

    @Update(
            "DELETE FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND machine_id = #{machineId}")
    void blockRemoving(@Param("loc") Location loc, String machineId);

    @Nullable @Select(
            "SELECT machine_id FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockId(@Param("loc") Location loc);

    @Nullable @Select(
            "SELECT data FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockData(@Param("loc") Location loc);

    @Select("SELECT * FROM block_record")
    List<BlockRecord> getAllBlockRecords();

    @Update("UPDATE block_record SET data = #{data} WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    void saveBlockData(@Param("loc") Location loc, String data);

    // TODO machine menu data
}
