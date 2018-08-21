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

import java.util.Date;

import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_NEWS_CREATE_PAGE;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE;

/**
 * This class is used to add new news to the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminAddNewsCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminAddNewsCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        News news = new News();
        news.setUser((User) requestWrapper.getSessionAttribute(USER));
        news.setTitle(requestWrapper.getRequestParameter(TITLE));
        news.setContent(requestWrapper.getRequestParameter(CONTENT));
        news.setPhoto(requestWrapper.getRequestParameter(PHOTO));
        news.setDate(new Date());

        NewsValidator newsValidator = new NewsValidator();

        try {

            if (newsValidator.isNewsCorrect(news)) {
                NewsService newsService = new NewsServiceImpl();
                newsService.insert(news);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(NEWS, news);

                route.setResponsePath(FORWARD_TO_ADMIN_NEWS_CREATE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}