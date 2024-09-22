package org.irmc.industrialrevival.core.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

class ConnectionPool {
    private final String url;
    private final String user;
    private final String password;

    private final int maxPoolSize;

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections;
    private final Lock lock;

    public ConnectionPool(String url, String user, String password) throws SQLException {
        connectionPool = new ArrayList<>();
        usedConnections = new ArrayList<>();
        lock = new ReentrantLock();

        this.url = url;
        this.user = user;
        this.password = password;
        int maxPoolSize = IndustrialRevival.getInstance().getConfig().getInt("storage.max-connections");
        if (maxPoolSize <= 5) {
            IndustrialRevival.getInstance()
                    .getLogger()
                    .warning("Invalid connection pool size, it should be greater than 5, using default value of 10");
            maxPoolSize = 10;
        }
        this.maxPoolSize = maxPoolSize;

        initializeConnections(url, user, password);
    }

    private void initializeConnections(String url, String user, String password) throws SQLException {
        for (int i = 0; i < maxPoolSize / 2; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connectionPool.add(connection);
        }
    }

    public Connection getConnection() throws SQLException {
        lock.lock();
        try {
            if (connectionPool.isEmpty()) {
                if (usedConnections.size() < maxPoolSize) {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    usedConnections.add(connection);
                    return connection;
                } else {
                    throw new SQLException("Maximum pool size reached, no available connections");
                }
            } else {
                Connection connection = connectionPool.remove(connectionPool.size() - 1);
                usedConnections.add(connection);
                return connection;
            }
        } finally {
            lock.unlock();
        }
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        try {
            if (usedConnections.remove(connection)) {
                connectionPool.add(connection);
            }
        } finally {
            lock.unlock();
        }
    }

    public void closeAllConnections() {
        lock.lock();
        try {
            for (Connection connection : connectionPool) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            for (Connection connection : usedConnections) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionPool.clear();
            usedConnections.clear();
        } finally {
            lock.unlock();
        }
    }
}
