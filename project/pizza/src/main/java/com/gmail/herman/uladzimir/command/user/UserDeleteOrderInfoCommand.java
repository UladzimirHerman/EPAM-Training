package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderInfoService;
import com.gmail.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.ORDER_INFO_ID;
import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_BASKET_PAGE;

/**
 * This class is used to delete the product from the order.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserDeleteOrderInfoCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserDeleteOrderInfoCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderInfoService orderInfoService = new OrderInfoServiceImpl();

        try {
            orderInfoService.deleteById
                    (Integer.parseInt(requestWrapper.getRequestParameter(ORDER_INFO_ID)));

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_USER_BASKET_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}