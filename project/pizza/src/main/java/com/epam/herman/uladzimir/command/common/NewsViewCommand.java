package com.epam.herman.uladzimir.command.common;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.News;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.service.NewsService;
import com.epam.herman.uladzimir.service.impl.NewsServiceImpl;
import com.epam.herman.uladzimir.util.PaginationUtil;
import com.epam.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.herman.uladzimir.command.AttributeName.NEWS_LIST;
import static com.epam.herman.uladzimir.command.AttributeName.PAGE;
import static com.epam.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_NEWS_PAGE;

/**
 * This class is used to get news page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class NewsViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(NewsViewCommand.class);

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

                route.setResponsePath(FORWARD_TO_NEWS_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }
}
