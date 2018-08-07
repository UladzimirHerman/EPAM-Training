package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
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
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_NEWS_PAGE;

public class AdminNewsViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminNewsViewCommand.class);
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

                int pageQuantity =
                        PaginationUtil.definePageQuantity(newsService.count(), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PAGE_QUANTITY, pageQuantity);

                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_ADMIN_NEWS_PAGE);

            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}