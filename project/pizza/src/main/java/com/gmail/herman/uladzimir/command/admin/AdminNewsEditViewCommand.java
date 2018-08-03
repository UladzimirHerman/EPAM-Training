package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.News;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.NewsService;
import com.gmail.herman.uladzimir.service.impl.NewsServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.NEWS;
import static com.gmail.herman.uladzimir.command.AttributeName.NEWS_ID;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_NEWS_EDIT_PAGE;

public class AdminNewsEditViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminNewsEditViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        NewsService newsService = new NewsServiceImpl();

        try {
            News news = newsService.
                    findById(Integer.parseInt(requestWrapper.getRequestParameter(NEWS_ID)));
            requestWrapper.putRequestAttribute(NEWS, news);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_NEWS_EDIT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}