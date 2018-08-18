package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.News;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.NewsService;
import com.gmail.herman.uladzimir.service.impl.NewsServiceImpl;
import com.gmail.herman.uladzimir.util.PaginationUtil;
import com.gmail.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.NEWS_LIST;
import static com.gmail.herman.uladzimir.command.AttributeName.PAGE;
import static com.gmail.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_NEWS_PAGE;

/**
 * This class is used to get news page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserNewsViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserNewsViewCommand.class);

    private static final int ITEMS_ON_THE_PAGE = 4;

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        int page = Integer.parseInt(requestWrapper.getRequestParameter(PAGE));

        PaginationValidator paginationValidator = new PaginationValidator();

        try {

            if (paginationValidator.isPageNumberCorrect(page)) {
                NewsService newsService = new NewsServiceImpl();
                List<News> newsList = newsService.findAll
                        (PaginationUtil.defineOffset(page, ITEMS_ON_THE_PAGE), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(NEWS_LIST, newsList);

                int pageQuantity = PaginationUtil.definePageQuantity
                        (newsService.count(), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PAGE_QUANTITY, pageQuantity);

                route.setResponsePath(FORWARD_TO_USER_NEWS_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}