package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.*;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderInfoService;
import com.epam.herman.uladzimir.service.OrderService;
import com.epam.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import com.epam.herman.uladzimir.service.impl.OrderServiceImpl;
import com.epam.herman.uladzimir.validator.OrderInfoValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

import static com.epam.herman.uladzimir.command.AttributeName.PRODUCT_ID;
import static com.epam.herman.uladzimir.command.AttributeName.QUANTITY;
import static com.epam.herman.uladzimir.command.AttributeName.USER;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_PRODUCT_FIRST_PAGE;

/**
 * This class is used to add the product to the basket.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserAddToBasketCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserAddToBasketCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        User user = (User) requestWrapper.getSessionAttribute(USER);

        OrderService orderService = new OrderServiceImpl();

        try {

            if(!orderService.isBasketExist(user.getId())){
                Order order = new Order();
                order.setDate(new Date());
                order.setUser(user);
                order.setOrderStatus(OrderStatus.BASKET);
                orderService.insert(order);
            }

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setQuantity
                    (Integer.parseInt(requestWrapper.getRequestParameter(QUANTITY)));

            OrderInfoValidator orderInfoValidator = new OrderInfoValidator();

            if(orderInfoValidator.isOrderInfoCorrect(orderInfo)){
                orderInfo.setOrder(orderService.findBasket(user.getId()));

                Product product = new Product();
                product.setId(Integer.parseInt(requestWrapper.getRequestParameter(PRODUCT_ID)));
                orderInfo.setProduct(product);

                OrderInfoService orderInfoService = new OrderInfoServiceImpl();
                orderInfoService.insert(orderInfo);
            }

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_USER_PRODUCT_FIRST_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}