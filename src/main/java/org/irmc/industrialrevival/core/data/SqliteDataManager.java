package org.irmc.industrialrevival.core.data;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.data.mapper.GuideSettingsMapper;
import org.irmc.industrialrevival.core.guide.GuideSettings;

import javax.sql.DataSource;
import java.io.File;

public class SqliteDataManager implements IDataManager {
    private final File storageDir = new File(IndustrialRevival.getInstance().getDataFolder().getParentFile().getParentFile(), "irstorage");
    private final File databaseFile = new File(storageDir, "database.db");

    private SqlSession session;

    public SqliteDataManager() {
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        if (!databaseFile.exists()) {
            try {
                databaseFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connect(String url, String username, String password) {
        DataSource dataSource = new UnpooledDataSource("org.sqlite.JDBC", getUrl(), "", "");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(GuideSettingsMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        session = sqlSessionFactory.openSession();
    }

    @Override
    public void close() {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public GuideSettings getGuideSettings(String playerName) {
        return session.getMapper(GuideSettingsMapper.class).get(playerName);
    }

    @Override
    public void saveGuideSettings(String playerName, GuideSettings settings) {
        session.getMapper(GuideSettingsMapper.class).save(playerName, settings);
    }

    private String getUrl() {
        return "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }
}
