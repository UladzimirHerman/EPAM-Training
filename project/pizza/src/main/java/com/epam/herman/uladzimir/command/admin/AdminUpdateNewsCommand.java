package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.News;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.NewsService;
import com.epam.herman.uladzimir.service.impl.NewsServiceImpl;
import com.epam.herman.uladzimir.validator.NewsValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_NEWS_EDIT_PAGE;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE;

/**
 * This class is used to update news.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminUpdateNewsCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminUpdateNewsCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        News news = new News();
        news.setId(Integer.parseInt(requestWrapper.getRequestParameter(NEWS_ID)));
        news.setUser((User) requestWrapper.getSessionAttribute(USER));
        news.setTitle(requestWrapper.getRequestParameter(TITLE));
        news.setContent(requestWrapper.getRequestParameter(CONTENT));
        news.setPhoto(requestWrapper.getRequestParameter(PHOTO));

        NewsValidator newsValidator = new NewsValidator();

        try {

            if (newsValidator.isNewsCorrect(news)) {
                NewsService newsService = new NewsServiceImpl();
                newsService.update(news);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(NEWS, news);

                route.setResponsePath(FORWARD_TO_ADMIN_NEWS_EDIT_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}