package com.epam.herman.uladzimir.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class {@link ConnectionPool} is used for connecting and managing
 * database connections between users. This class is a singleton.
 *
 * @author Uladzimir Herman
 * @see ConnectionConfigurator
 * @see PooledConnection
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionConfigurator configuration =
            ConnectionConfigurator.getInstance();
    private static volatile ConnectionPool instance;
    private BlockingQueue<PooledConnection> freeConnections;
    private BlockingQueue<PooledConnection> busyConnections;

    private ConnectionPool() {

        try {
            freeConnections = new ArrayBlockingQueue<>(configuration.getPoolSize());
            busyConnections = new ArrayBlockingQueue<>(configuration.getPoolSize());
            Class.forName(configuration.getDriverName());
            PooledConnection connection;

            for (int i = 0; i < configuration.getPoolSize(); i++) {
                connection = new PooledConnection(DriverManager.getConnection
                        (configuration.getUrl(), configuration.getUser(), configuration.getPassword()));
                freeConnections.add(connection);
            }

            LOGGER.info("Successful creation connection pool");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Exception occurred when creating queue: ", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Exception occurred when loading SQL driver: ", e);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when getting connection: ", e);
        }

    }

    public static ConnectionPool getInstance() {

        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }

        return instance;
    }

    /**
     * Getting connection from connection pool
     *
     * @return particular connection from connection pool
     */
    public PooledConnection takeConnection() {
        PooledConnection connection = null;

        try {
            connection = freeConnections.take();
            busyConnections.add(connection);
            LOGGER.info("Successful taking connection");
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occurred when taking connection from pool: ", e);
            Thread.currentThread().interrupt();
        }

        return connection;
    }

    /**
     * Returning connection into connection pool
     *
     * @param connection connection for returning
     */
    public void returnConnection(PooledConnection connection) {
        freeConnections.add(connection);
        busyConnections.remove(connection);
        LOGGER.info("Successful returning connection");
    }

    /**
     * Closing connection pool
     */
    public void closePool() {

        try {
            closeConnections(busyConnections);
            closeConnections(freeConnections);
            LOGGER.info("Successful closing connection pool");
            instance = null;
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when closing connection pool: ", e);
        }

    }

    /**
     * Closing all connections in queue
     *
     * @param queue connections queue
     * @throws SQLException if any error occurred when closing connections
     */
    private void closeConnections(BlockingQueue<PooledConnection> queue)
            throws SQLException {
        PooledConnection connection;

        while ((connection = queue.poll()) != null) {

            if (!connection.getAutoCommit()) {
                connection.commit();
            }

            connection.reallyClose();
        }

    }

}