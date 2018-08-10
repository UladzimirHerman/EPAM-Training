package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.*;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderInfoService;
import com.gmail.herman.uladzimir.service.OrderService;
import com.gmail.herman.uladzimir.service.impl.OrderInfoServiceImpl;
import com.gmail.herman.uladzimir.service.impl.OrderServiceImpl;
import com.gmail.herman.uladzimir.validator.OrderValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

import static com.gmail.herman.uladzimir.command.AttributeName.PRODUCT_ID;
import static com.gmail.herman.uladzimir.command.AttributeName.QUANTITY;
import static com.gmail.herman.uladzimir.command.AttributeName.USER;

public class UserAddToBasketCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UserAddToBasketCommand.class);

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

            int quantity = Integer.parseInt(requestWrapper.getRequestParameter(QUANTITY));

            OrderValidator orderValidator = new OrderValidator();

            if(orderValidator.isQuantityCorrect(quantity)){
                OrderInfoService orderInfoService = new OrderInfoServiceImpl();

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrder(orderService.findBasket(user.getId()));

                Product product = new Product();
                product.setId(Integer.parseInt(requestWrapper.getRequestParameter(PRODUCT_ID)));
                orderInfo.setProduct(product);

                orderInfo.setQuantity(quantity);
                orderInfoService.insert(orderInfo);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}