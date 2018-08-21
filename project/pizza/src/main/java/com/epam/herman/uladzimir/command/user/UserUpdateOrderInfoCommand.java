package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderInfoService;
import com.epam.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import com.epam.herman.uladzimir.validator.OrderInfoValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_BASKET_PAGE;

/**
 * This class is used to update the product quantity in the basket.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserUpdateOrderInfoCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserUpdateOrderInfoCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setQuantity
                (Integer.parseInt(requestWrapper.getRequestParameter(QUANTITY)));

        OrderInfoValidator orderInfoValidator = new OrderInfoValidator();

        try {

            if (orderInfoValidator.isOrderInfoCorrect(orderInfo)) {
                orderInfo.setId
                        (Integer.parseInt(requestWrapper.getRequestParameter(ORDER_INFO_ID)));

                OrderInfoService orderInfoService = new OrderInfoServiceImpl();
                orderInfoService.update(orderInfo);
            }

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_USER_BASKET_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}