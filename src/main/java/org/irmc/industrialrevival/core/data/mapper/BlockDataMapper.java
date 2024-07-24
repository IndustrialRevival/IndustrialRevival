package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

@Mapper
public interface BlockDataMapper {
    @Insert("INSERT INTO block_record (world, x, y, z, machine_id) VALUES (#{loc.getWorld().getName()}, #{loc.getBlockX()}, #{loc.getBlockY()}, #{loc.getBlockZ()}, #{machineId})")
    void blockPlacing(@Param("loc") Location loc, String machineId);

    @Update("DELETE FROM block_record WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()} AND machine_id = #{machineId}")
    void blockRemoving(@Param("loc") Location loc, String machineId);

    @Nullable
    @Select("SELECT machine_id FROM block_data WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockId(@Param("loc") Location loc);

    @Insert("INSERT INTO block_data (world, x, y, z, data) VALUES (#{loc.getWorld().getName()}, #{loc.getBlockX()}, #{loc.getBlockY()}, #{loc.getBlockZ()}, #{data})")
    void insertBlockData(@Param("loc") Location loc, String data);

    @Update("DELETE FROM block_data WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    void deleteBlockData(@Param("loc") Location loc);

    @Insert("UPDATE block_data SET data = #{data} WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    void updateBlockData(@Param("loc") Location loc, String data);

    @Nullable
    @Select("SELECT data FROM block_data WHERE world = #{loc.getWorld().getName()} AND x = #{loc.getBlockX()} AND y = #{loc.getBlockY()} AND z = #{loc.getBlockZ()}")
    String getBlockData(@Param("loc") Location loc);

    //TODO machine menu data
}
