package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.NewsService;
import com.epam.herman.uladzimir.service.impl.NewsServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.NEWS_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE;

/**
 * This class is used to delete news from the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminDeleteNewsCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminDeleteNewsCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        NewsService newsService = new NewsServiceImpl();

        try {
            newsService.deleteById
                    (Integer.parseInt(requestWrapper.getRequestParameter(NEWS_ID)));

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}