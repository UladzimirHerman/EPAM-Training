package com.gmail.herman.uladzimir.manager;

import com.gmail.herman.uladzimir.database.DatabaseParameter;

import java.util.ResourceBundle;

import static com.gmail.herman.uladzimir.database.DatabaseParameter.DATABASE_FILE_NAME;

/**
 * Class {@link DatabaseManager} is used for getting database properties
 * from property file. This class is a singleton.
 *
 * @author Uladzimir Herman
 */
public class DatabaseManager {

    private ResourceBundle resourceBundle;
    private static volatile DatabaseManager instance;

    private DatabaseManager() {
        resourceBundle = ResourceBundle.getBundle(DATABASE_FILE_NAME);
    }

    public static DatabaseManager getInstance() {

        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }

        return instance;
    }

    /**
     * Getting property value by key
     *
     * @param key key for searching
     * @see DatabaseParameter
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}