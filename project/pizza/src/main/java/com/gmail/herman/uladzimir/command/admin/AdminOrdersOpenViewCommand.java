package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderService;
import com.gmail.herman.uladzimir.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.ORDERS;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_ORDERS_OPEN_PAGE;

public class AdminOrdersOpenViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminOrdersOpenViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderService orderService = new OrderServiceImpl();

        try {
            List<Order> orders = orderService.findOpenOrders();
            requestWrapper.putRequestAttribute(ORDERS, orders);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_ORDERS_OPEN_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}