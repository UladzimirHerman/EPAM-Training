package com.gmail.herman.uladzimir.dao;

import java.util.ResourceBundle;

import static com.gmail.herman.uladzimir.dao.SQLElement.SQL_FILE_NAME;

/**
 * Class {@link SQLManager} is used for getting specific SQL-elements
 * from property-file. This class is a singleton.
 *
 * @author Uladzimir Herman
 */
public class SQLManager {

    private ResourceBundle resourceBundle;
    private static volatile SQLManager instance;

    private SQLManager() {
        resourceBundle = ResourceBundle.getBundle(SQL_FILE_NAME);
    }

    public static SQLManager getInstance() {
        if (instance == null) {
            synchronized (SQLManager.class) {
                if (instance == null) {
                    instance = new SQLManager();
                }
            }
        }
        return instance;
    }

    public String getSQL(String key) {
        return resourceBundle.getString(key);
    }

}