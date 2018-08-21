package com.epam.herman.uladzimir.command.admin;

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

import static com.epam.herman.uladzimir.command.AttributeName.ORDER_ID;
import static com.epam.herman.uladzimir.command.AttributeName.ORDER_STATUS;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_ORDERS_OPEN_FIRST_PAGE;

/**
 * This class is used to update the order status.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminUpdateOrderStatusCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminUpdateOrderStatusCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderService orderService = new OrderServiceImpl();

        try {
            Order order = orderService.findById
                    (Integer.parseInt(requestWrapper.getRequestParameter(ORDER_ID)));
            order.setOrderStatus
                    (OrderStatus.valueOf(requestWrapper.getRequestParameter(ORDER_STATUS)));
            orderService.update(order);

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_ADMIN_ORDERS_OPEN_FIRST_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}