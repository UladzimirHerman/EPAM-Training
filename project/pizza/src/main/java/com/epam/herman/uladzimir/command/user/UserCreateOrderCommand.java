package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.entity.OrderStatus;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderService;
import com.epam.herman.uladzimir.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

import static com.epam.herman.uladzimir.command.AttributeName.ORDER_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_BASKET_PAGE;

/**
 * This class is used to create the order.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserCreateOrderCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserCreateOrderCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderService orderService = new OrderServiceImpl();

        try {
            Order order = orderService.findById
                    (Integer.parseInt(requestWrapper.getRequestParameter(ORDER_ID)));
            order.setDate(new Date());
            order.setOrderStatus(OrderStatus.NEW);
            orderService.update(order);

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_USER_BASKET_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}