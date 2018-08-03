package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.NewsService;
import com.gmail.herman.uladzimir.service.impl.NewsServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.NEWS_ID;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_ADMIN_NEWS_PAGE;

public class AdminDeleteNewsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminDeleteNewsCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        NewsService newsService = new NewsServiceImpl();

        try {
            newsService.deleteById
                    (Integer.parseInt(requestWrapper.getRequestParameter(NEWS_ID)));
            route.setResponsePath(REDIRECT_TO_ADMIN_NEWS_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}