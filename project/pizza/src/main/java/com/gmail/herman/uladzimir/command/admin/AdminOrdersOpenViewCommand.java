package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderService;
import com.gmail.herman.uladzimir.service.impl.OrderServiceImpl;
import com.gmail.herman.uladzimir.util.PaginationUtil;
import com.gmail.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.ORDERS;
import static com.gmail.herman.uladzimir.command.AttributeName.PAGE;
import static com.gmail.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_ORDERS_OPEN_PAGE;

public class AdminOrdersOpenViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminOrdersOpenViewCommand.class);
    private static final int ITEMS_ON_THE_PAGE = 8;

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        int page = Integer.parseInt(requestWrapper.getRequestParameter(PAGE));

        PaginationValidator paginationValidator = new PaginationValidator();

        try {

            if (paginationValidator.isPageNumberCorrect(page)) {
                OrderService orderService = new OrderServiceImpl();
                List<Order> orders = orderService.findOpen
                        (PaginationUtil.defineOffset(page, ITEMS_ON_THE_PAGE), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(ORDERS, orders);

                int pageQuantity =
                        PaginationUtil.definePageQuantity(orderService.countOpen(), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PAGE_QUANTITY, pageQuantity);

                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_ADMIN_ORDERS_OPEN_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}