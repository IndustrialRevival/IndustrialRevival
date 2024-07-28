package org.irmc.industrialrevival.core.data;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.mapper.BlockDataMapper;
import org.irmc.industrialrevival.core.data.mapper.GuideSettingsMapper;
import org.irmc.industrialrevival.core.data.mapper.ResearchStatusMapper;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed interface IDataManager permits MysqlDataManager, SqliteDataManager {
    void close();

    @Nullable GuideSettings getGuideSettings(@NotNull String playerName);

    void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings);

    void handleBlockPlacing(Location loc, String machineId);

    void handleBlockBreaking(Location loc);

    /**
     * Get the research status of a player as a JsonObject.
     *
     * @param playerName the player name
     * @return the research status as a JsonObject, or an empty JsonObject if the player has no research status
     */
    @NotNull YamlConfiguration getResearchStatus(String playerName);

    void saveResearchStatus(String playerName, YamlConfiguration researchStatus);

    @NotNull YamlConfiguration getBlockData(@NotNull Location location);

    void updateBlockData(@NotNull Location location, @NotNull BlockRecord r);

    List<BlockRecord> getAllBlockRecords();

    ItemStack getMenuItem(Location location, int slot);

    default Configuration newMybatisConfiguration(Environment environment) {
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(GuideSettingsMapper.class);
        configuration.addMapper(ResearchStatusMapper.class);
        configuration.addMapper(BlockDataMapper.class);

        configuration.getTypeHandlerRegistry().register(Class.class, new ClassTypeHandler());

        return configuration;
    }

    class ClassTypeHandler extends BaseTypeHandler<Class<?>> {
        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, Class<?> parameter, JdbcType jdbcType)
                throws SQLException {
            ps.setString(i, parameter.getName());
        }

        @Override
        public Class<?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
            try {
                return rs.getString(columnName) == null ? null : Class.forName(rs.getString(columnName));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Class<?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            try {
                return rs.getString(columnIndex) == null ? null : Class.forName(rs.getString(columnIndex));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Class<?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            try {
                return cs.getString(columnIndex) == null ? null : Class.forName(cs.getString(columnIndex));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}