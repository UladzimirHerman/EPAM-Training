package com.epam.herman.uladzimir.database;

import com.epam.herman.uladzimir.manager.DatabaseManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;

import static com.epam.herman.uladzimir.database.DatabaseParameter.*;

/**
 * Class {@link ConnectionConfigurator} is used for connection configuration
 * from property file. This class is a singleton.
 *
 * @author Uladzimir Herman
 */
public class ConnectionConfigurator {

    private static final Logger LOGGER =
            LogManager.getLogger(ConnectionConfigurator.class);
    private static volatile ConnectionConfigurator instance;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionConfigurator() {

        try {
            driverName = DatabaseManager.getInstance().getProperty(DRIVER_NAME);
            url = DatabaseManager.getInstance().getProperty(URL);
            user = DatabaseManager.getInstance().getProperty(USER);
            password = DatabaseManager.getInstance().getProperty(PASSWORD);
            poolSize = Integer.parseInt(DatabaseManager.getInstance().getProperty(POOL_SIZE));
            LOGGER.info("Successful configuration");
        } catch (MissingResourceException e) {
            LOGGER.error
                    ("MissingResourceException occurred when working with property file: ", e);
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException occurred when parsing quantity of pools: ", e);
        }

    }

    public static ConnectionConfigurator getInstance() {

        if (instance == null) {
            synchronized (ConnectionConfigurator.class) {
                if (instance == null) {
                    instance = new ConnectionConfigurator();
                }
            }
        }

        return instance;
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