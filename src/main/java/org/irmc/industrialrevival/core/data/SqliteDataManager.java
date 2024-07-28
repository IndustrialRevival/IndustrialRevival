package org.irmc.industrialrevival.core.data;

import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.mapper.BlockDataMapper;
import org.irmc.industrialrevival.core.data.mapper.GuideSettingsMapper;
import org.irmc.industrialrevival.core.data.mapper.ResearchStatusMapper;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.core.utils.Constants;
import org.jetbrains.annotations.NotNull;

public final class SqliteDataManager implements IDataManager {
    private final File databaseFile = new File(Constants.STORAGE_FOLDER, "database.db");

    private SqlSession session;

    public SqliteDataManager() throws SQLException {
        if (!Constants.STORAGE_FOLDER.exists()) {
            Constants.STORAGE_FOLDER.mkdirs();
        }

        if (!databaseFile.exists()) {
            try {
                if (!databaseFile.createNewFile()) {
                    throw new SQLException("Failed to create database file");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        connect();
    }

    private void connect() throws SQLException {
        DataSource dataSource = new UnpooledDataSource("org.sqlite.JDBC", getUrl(), null, null);
        dataSource.getConnection();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Configuration configuration = newMybatisConfiguration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        session = sqlSessionFactory.openSession(true);

        createTables();
    }

    @Override
    public void close() {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public GuideSettings getGuideSettings(@NotNull String playerName) {
        return session.getMapper(GuideSettingsMapper.class).get(playerName);
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        session.getMapper(GuideSettingsMapper.class).save(playerName, settings);
    }

    @Override
    public @NotNull YamlConfiguration getResearchStatus(String playerName) {
        String yaml = session.getMapper(ResearchStatusMapper.class).getResearchStatus(playerName);
        if (yaml == null) {
            return new YamlConfiguration();
        } else {
            yaml = new String(Base64.getDecoder().decode(yaml));
            return YamlConfiguration.loadConfiguration(new StringReader(yaml));
        }
    }

    @Override
    public void saveResearchStatus(String playerName, YamlConfiguration researchStatus) {
        String yaml = researchStatus.saveToString();
        String b64 = Base64.getEncoder().encodeToString(yaml.getBytes());
        session.getMapper(ResearchStatusMapper.class).insertResearchStatus(playerName, b64);
    }

    @Override
    public @NotNull YamlConfiguration getBlockData(@NotNull Location location) {
        String str = session.getMapper(BlockDataMapper.class).getBlockData(location);
        if (str == null) {
            return new YamlConfiguration();
        } else {
            return YamlConfiguration.loadConfiguration(new StringReader(str));
        }
    }

    @Override
    public void handleBlockPlacing(Location loc, String machineId) {
        session.getMapper(BlockDataMapper.class).blockPlacing(loc, machineId);
    }

    @Override
    public void handleBlockBreaking(Location loc) {
        BlockDataMapper mapper = session.getMapper(BlockDataMapper.class);
        String id = mapper.getBlockId(loc);
        mapper.blockRemoving(loc, id);
        mapper.deleteMenuItems(loc);
    }

    @Override
    public void updateBlockData(@NotNull Location location, @NotNull BlockRecord blockRecord) {
        BlockDataMapper mapper = session.getMapper(BlockDataMapper.class);
        if (!getBlockData(location).getKeys(true).isEmpty()) {
            mapper.saveBlockData(location, blockRecord.getData());
        }
    }

    @Override
    public List<BlockRecord> getAllBlockRecords() {
        return session.getMapper(BlockDataMapper.class).getAllBlockRecords();
    }

    @Override
    public ItemStack getMenuItem(Location location, int slot) {
        return session.getMapper(BlockDataMapper.class)
                .getMenuItem(location, slot)
                .getItemStack();
    }

    private String getUrl() {
        return "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }

    private void createTables() throws SQLException {
        try (Connection conn = session.getConnection()) {
            conn.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS guide_settings (username TEXT NOT NULL,fireWorksEnabled BOOLEAN NOT NULL,learningAnimationEnabled BOOLEAN NOT NULL,language TEXT NOT NULL);")
                    .execute();

            conn.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS research_status (username TEXT NOT NULL, researchStatus TEXT NOT NULL)")
                    .execute();

            conn.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS block_record (world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, machineId TEXT NOT NULL, data TEXT DEFAULT NULL, PRIMARY KEY (world, x, y, z))")
                    .execute();

            conn.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS menu_items(world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, slot INT NOT NULL, itemJson TEXT NOT NULL, itemClass TEXT NOT NULL, PRIMARY KEY (world, x, y, z));")
                    .execute();
        }
    }
}
