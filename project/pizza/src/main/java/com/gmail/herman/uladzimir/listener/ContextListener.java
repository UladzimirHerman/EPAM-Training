package com.gmail.herman.uladzimir.listener;

import com.gmail.herman.uladzimir.database.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class {@link ContextListener} downloads the necessary resources when
 * the application starts, and when the application finishes, it unloads the resources.
 *
 * @author Uladzimir Herman
 * @see ConnectionPool
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().closePool();
    }

}