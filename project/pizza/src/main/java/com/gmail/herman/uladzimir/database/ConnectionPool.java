package com.gmail.herman.uladzimir.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class {@link ConnectionPool} is used
 * for connecting and managing database connection between users.
 *
 * @author Uladzimir Herman
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool;
    private static AtomicBoolean connectionExist = new AtomicBoolean(false);
    private static final ConnectionPoolConfiguration POOL_CONFIGURATION = ConnectionPoolConfiguration.getInstance();
    private static ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool() {
        connectionQueue = new ArrayBlockingQueue<>(POOL_CONFIGURATION.getPoolSize());

        try {
            Class.forName(POOL_CONFIGURATION.getDriverName());
            Connection connection;

            for (int i = 0; i < POOL_CONFIGURATION.getPoolSize(); i++) {
                connection = DriverManager.getConnection(POOL_CONFIGURATION.getUrl(),
                        POOL_CONFIGURATION.getUser(), POOL_CONFIGURATION.getPassword());
                connectionQueue.put(connection);
            }

        } catch (ClassNotFoundException e) {
            LOGGER.error("An error occurred during loading SQL driver: ", e);
        } catch (SQLException e) {
            LOGGER.error("An error occurred during getting connection: ", e);
        } catch (InterruptedException e) {
            LOGGER.error("An error occurred during adding connection in pool: ", e);
        }

    }

    public static ConnectionPool getInstance() {
        if (!connectionExist.get()) {
            lock.lock();
            if (connectionPool == null) {
                connectionPool = new ConnectionPool();
                connectionExist.set(true);
            }
            lock.unlock();
        }
        return connectionPool;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't get connection from pool: ", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't close connection: ", e);
            Thread.currentThread().interrupt();
        }
    }

    public void closePool() {
        try {
            for (int i = 0; i < POOL_CONFIGURATION.getPoolSize(); i++) {
                connectionQueue.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Couldn't close connection pool: ", e);
            Thread.currentThread().interrupt();
        }
    }

}