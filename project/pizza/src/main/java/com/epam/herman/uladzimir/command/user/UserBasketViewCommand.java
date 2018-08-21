package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderInfoService;
import com.epam.herman.uladzimir.service.OrderService;
import com.epam.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import com.epam.herman.uladzimir.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.herman.uladzimir.command.AttributeName.ORDER_INFO_LIST;
import static com.epam.herman.uladzimir.command.AttributeName.USER;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_BASKET_PAGE;

/**
 * This class is used to get the basket page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserBasketViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserBasketViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        User user = (User) requestWrapper.getSessionAttribute(USER);
        OrderService orderService = new OrderServiceImpl();

        try {

            if (orderService.isBasketExist(user.getId())) {
                OrderInfoService orderInfoService = new OrderInfoServiceImpl();
                List<OrderInfo> orderInfoList = orderInfoService.findFullInfoByOrderId
                        (orderService.findBasket(user.getId()).getId());
                requestWrapper.putRequestAttribute(ORDER_INFO_LIST, orderInfoList);
            }

            route.setResponsePath(FORWARD_TO_USER_BASKET_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}