package com.gmail.herman.uladzimir.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class {@link ConnectionPoolConfiguration} is used
 * for getting configuration data about database from property file.
 *
 * @author Uladzimir Herman
 */
public class ConnectionPoolConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolConfiguration.class);
    private static final ConnectionPoolConfiguration connectionPoolConfiguration = new ConnectionPoolConfiguration();
    private static final String DB_CONFIGURATION_FILE = "database";

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPoolConfiguration() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_CONFIGURATION_FILE);
            driverName = resourceBundle.getString("jdbc.driver");
            url = resourceBundle.getString("jdbc.url");
            user = resourceBundle.getString("jdbc.user");
            password = resourceBundle.getString("jdbc.password");
            poolSize = Integer.parseInt(resourceBundle.getString("jdbc.pool.size"));
            LOGGER.info("Successful initialization");
        } catch (MissingResourceException e) {
            LOGGER.error("An error occurred during work with configuration file: ", e);
        }
    }

    public static ConnectionPoolConfiguration getInstance() {
        return connectionPoolConfiguration;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

}