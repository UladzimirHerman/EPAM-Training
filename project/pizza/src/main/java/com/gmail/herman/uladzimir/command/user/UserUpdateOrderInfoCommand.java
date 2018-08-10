package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.OrderInfo;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderInfoService;
import com.gmail.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import com.gmail.herman.uladzimir.validator.OrderValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_USER_BASKET_PAGE;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_USER_BASKET_PAGE;

public class UserUpdateOrderInfoCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserUpdateOrderInfoCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        int quantity = Integer.parseInt(requestWrapper.getRequestParameter(QUANTITY));

        OrderValidator orderValidator = new OrderValidator();

        try {

            if (orderValidator.isQuantityCorrect(quantity)) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId
                        (Integer.parseInt(requestWrapper.getRequestParameter(ORDER_INFO_ID)));
                orderInfo.setQuantity(quantity);

                OrderInfoService orderInfoService = new OrderInfoServiceImpl();
                orderInfoService.update(orderInfo);

                route.setResponsePath(REDIRECT_TO_USER_BASKET_PAGE);
            } else {
                //requestWrapper.putRequestAttribute(MESSAGE, true);
                //requestWrapper.putRequestAttribute(ORDER_INFO_LIST, orderInfoList);
                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_USER_BASKET_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}