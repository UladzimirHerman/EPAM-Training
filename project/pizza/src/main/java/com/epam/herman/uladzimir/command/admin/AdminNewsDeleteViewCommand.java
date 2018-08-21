package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.News;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.NewsService;
import com.epam.herman.uladzimir.service.impl.NewsServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.NEWS;
import static com.epam.herman.uladzimir.command.AttributeName.NEWS_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_NEWS_DELETE_PAGE;

/**
 * This class is used to get news delete page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminNewsDeleteViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminNewsDeleteViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        NewsService newsService = new NewsServiceImpl();

        try {
            News news = newsService.
                    findById(Integer.parseInt(requestWrapper.getRequestParameter(NEWS_ID)));
            requestWrapper.putRequestAttribute(NEWS, news);

            route.setResponsePath(FORWARD_TO_ADMIN_NEWS_DELETE_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}