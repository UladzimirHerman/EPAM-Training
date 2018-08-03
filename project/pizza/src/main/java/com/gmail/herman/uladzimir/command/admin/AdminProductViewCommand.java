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

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.PRODUCT_LIST;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_PRODUCT_PAGE;

public class AdminProductViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminProductViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        ProductService productService = new ProductServiceImpl();

        try {
            List<Product> products = productService.findAll();
            requestWrapper.putRequestAttribute(PRODUCT_LIST, products);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_PRODUCT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}