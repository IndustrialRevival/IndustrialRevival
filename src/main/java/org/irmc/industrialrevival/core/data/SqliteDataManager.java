package org.irmc.industrialrevival.core.data;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.irmc.industrialrevival.core.data.mapper.SqliteGuideSettingsMapper;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.core.utils.Constants;
import org.jetbrains.annotations.NotNull;

public class SqliteDataManager implements IDataManager {
    private final File databaseFile = new File(Constants.STORAGE_FOLDER, "database.db");

    private SqlSession session;

    public SqliteDataManager() throws SQLException {
        if (!Constants.STORAGE_FOLDER.exists()) {
            Constants.STORAGE_FOLDER.mkdirs();
        }

        if (!databaseFile.exists()) {
            try {
                databaseFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        connect("", "", "");
    }

    @Override
    public void connect(String url, String username, String password) throws SQLException {
        DataSource dataSource = new UnpooledDataSource("org.sqlite.JDBC", getUrl(), "", "");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Configuration configuration = newMybatisConfiguration(environment);
        configuration.addMapper(SqliteGuideSettingsMapper.class);
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
        return session.getMapper(SqliteGuideSettingsMapper.class).get(playerName);
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        session.getMapper(SqliteGuideSettingsMapper.class).save(playerName, settings);
    }

    private String getUrl() {
        return "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }

    private void createTables() throws SQLException {
        try (PreparedStatement statement = session.getConnection()
                .prepareStatement("CREATE TABLE IF NOT EXISTS guide_settings (" + "    username TEXT NOT NULL,"
                        + "    fireWorksEnabled INTEGER NOT NULL,"
                        + "    learningAnimationEnabled INTEGER NOT NULL,"
                        + "    language TEXT NOT NULL);")) {
            statement.execute();
        }
    }
}
