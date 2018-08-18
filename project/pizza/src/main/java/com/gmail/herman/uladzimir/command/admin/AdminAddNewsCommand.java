package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.News;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.NewsService;
import com.gmail.herman.uladzimir.service.impl.NewsServiceImpl;
import com.gmail.herman.uladzimir.validator.NewsValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_NEWS_CREATE_PAGE;
import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_NEWS_FIRST_PAGE;

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