package org.irmc.industrialrevival.core.data;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
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
import org.jetbrains.annotations.NotNull;

public non-sealed class AbstractDataManager implements IDataManager {
    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public AbstractDataManager(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private SqlSession getSession() throws SQLException {
        DataSource dataSource = new UnpooledDataSource(driver, url, username, password);
        dataSource.setLoginTimeout(5);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Configuration configuration = newMybatisConfiguration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory.openSession();
    }

    @Override
    public void handleBlockPlacing(Location loc, String machineId) {
        try {
            SqlSession session = getSession();
            session.getMapper(BlockDataMapper.class).blockPlacing(loc, machineId);
            session.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleBlockBreaking(Location loc) {
        try {
            SqlSession session = getSession();
            BlockDataMapper mapper = session.getMapper(BlockDataMapper.class);
            String id = mapper.getBlockId(loc);
            mapper.blockRemoving(loc, id);
            mapper.deleteMenuItems(loc);
            session.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getBlockData(@NotNull Location location) {
        String b64;
        try {
            b64 = getSession().getMapper(BlockDataMapper.class).getBlockData(location);
        } catch (SQLException e) {
            return new YamlConfiguration();
        }

        if (b64 == null) {
            return new YamlConfiguration();
        } else {
            return YamlConfiguration.loadConfiguration(new StringReader(b64));
        }
    }

    @Override
    public void updateBlockData(@NotNull Location location, @NotNull BlockRecord record) {
        try {
            SqlSession session = getSession();
            BlockDataMapper mapper = session.getMapper(BlockDataMapper.class);
            if (!getBlockData(location).getKeys(true).isEmpty()) {
                mapper.saveBlockData(location, record.getData());
            }
            session.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlockRecord> getAllBlockRecords() {
        try {
            return getSession().getMapper(BlockDataMapper.class).getAllBlockRecords();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack getMenuItem(Location location, int slot) {
        try {
            return getSession()
                    .getMapper(BlockDataMapper.class)
                    .getMenuItem(location, slot)
                    .getItemStack();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GuideSettings getGuideSettings(@NotNull String playerName) {
        try {
            return getSession().getMapper(GuideSettingsMapper.class).get(playerName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        try {
            SqlSession session = getSession();
            session.getMapper(GuideSettingsMapper.class).save(playerName, settings);
            session.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getResearchStatus(String playerName) {
        String yaml;
        try {
            SqlSession session = getSession();
            yaml = session.getMapper(ResearchStatusMapper.class).getResearchStatus(playerName);
            session.commit();
        } catch (SQLException e) {
            return new YamlConfiguration();
        }

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
        try {
            SqlSession session = getSession();
            session.getMapper(ResearchStatusMapper.class).insertResearchStatus(playerName, b64);
            session.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTables() {
        int retryCount = 5;
        while (retryCount > 0) {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS guide_settings (username TEXT NOT NULL, fireWorksEnabled BOOLEAN NOT NULL, learningAnimationEnabled BOOLEAN NOT NULL, language TEXT NOT NULL);")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS research_status (username TEXT NOT NULL, researchStatus TEXT NOT NULL);")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS block_record (world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, machineId TEXT NOT NULL, data TEXT DEFAULT NULL, PRIMARY KEY (world, x, y, z));")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS menu_items (world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, slot INT NOT NULL, itemJson TEXT NOT NULL, itemClass TEXT NOT NULL, PRIMARY KEY (world, x, y, z));")
                        .execute();
                return;
            } catch (SQLException e) {
                if (e.getMessage().contains("SQLITE_BUSY")) {
                    retryCount--;
                    try {
                        Thread.sleep(1000); // 等待1秒后重试
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Failed to create tables after multiple attempts.");
    }
}
