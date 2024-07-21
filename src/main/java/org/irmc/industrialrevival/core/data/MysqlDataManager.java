package org.irmc.industrialrevival.core.data;

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
import org.bukkit.configuration.file.FileConfiguration;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.data.mapper.MySQLGuideSettingsMapper;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;

public class MysqlDataManager implements IDataManager {

    private SqlSession session;

    public MysqlDataManager() throws SQLException {
        FileConfiguration config = IndustrialRevival.getInstance().getConfig();
        String host = config.getString("storage.mysql.host");
        int port = config.getInt("storage.mysql.port");
        String url = host + ":" + port;
        String username = config.getString("storage.mysql.username");
        String password = config.getString("storage.mysql.password");
        connect(url, username, password);
    }

    @Override
    public void connect(String url, String username, String password) throws SQLException {
        DataSource dataSource = new UnpooledDataSource("com.mysql.cj.jdbc.Driver", getUrl(url), username, password);
        dataSource.setLoginTimeout(5000);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Configuration configuration = newMybatisConfiguration(environment);
        configuration.addMapper(MySQLGuideSettingsMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        session = sqlSessionFactory.openSession(true);

        createTables();
    }

    @Override
    public void close() {
        session.close();
    }

    @Override
    public GuideSettings getGuideSettings(@NotNull String playerName) {
        return session.getMapper(MySQLGuideSettingsMapper.class).get(playerName);
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        session.getMapper(MySQLGuideSettingsMapper.class).save(playerName, settings);
    }

    private String getUrl(String url) {
        return "jdbc:mysql://" + url + "/industrialrevival";
    }

    private void createTables() throws SQLException {
        try (PreparedStatement statement = session.getConnection()
                .prepareStatement("CREATE TABLE IF NOT EXISTS guide_settings (" + "    username TEXT NOT NULL,"
                        + "    fireWorksEnabled BOOLEAN NOT NULL,"
                        + "    learningAnimationEnabled BOOLEAN NOT NULL,"
                        + "    language TEXT NOT NULL);")) {
            statement.execute();
        }
    }
}
