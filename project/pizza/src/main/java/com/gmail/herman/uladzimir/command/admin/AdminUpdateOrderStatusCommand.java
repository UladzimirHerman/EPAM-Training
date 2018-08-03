package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.entity.OrderStatus;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderService;
import com.gmail.herman.uladzimir.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.ORDER_ID;
import static com.gmail.herman.uladzimir.command.AttributeName.ORDER_STATUS;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_ADMIN_ORDERS_OPEN_PAGE;

public class AdminUpdateOrderStatusCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminUpdateOrderStatusCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderService orderService = new OrderServiceImpl();

        try {
            Order order = new Order();
            order.setId(Integer.parseInt(requestWrapper.getRequestParameter(ORDER_ID)));
            order.setOrderStatus
                    (OrderStatus.valueOf(requestWrapper.getRequestParameter(ORDER_STATUS)));
            orderService.update(order);
            route.setResponsePath(REDIRECT_TO_ADMIN_ORDERS_OPEN_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}