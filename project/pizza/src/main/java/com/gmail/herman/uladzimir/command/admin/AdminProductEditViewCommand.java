package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.ProductService;
import com.gmail.herman.uladzimir.service.impl.ProductServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.PRODUCT;
import static com.gmail.herman.uladzimir.command.AttributeName.PRODUCT_ID;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_PRODUCT_EDIT_PAGE;

public class AdminProductEditViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminProductEditViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        ProductService productService = new ProductServiceImpl();

        try {
            Product product = productService.
                    findById(Integer.parseInt(requestWrapper.getRequestParameter(PRODUCT_ID)));
            requestWrapper.putRequestAttribute(PRODUCT, product);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_PRODUCT_EDIT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}