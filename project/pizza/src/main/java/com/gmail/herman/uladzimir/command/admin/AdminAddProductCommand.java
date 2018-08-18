package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.ProductService;
import com.gmail.herman.uladzimir.service.impl.ProductServiceImpl;
import com.gmail.herman.uladzimir.validator.ProductValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_PRODUCT_CREATE_PAGE;
import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_PRODUCT_FIRST_PAGE;

/**
 * This class is used to add a new product to the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminAddProductCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminAddProductCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        Product product = new Product();
        product.setName(requestWrapper.getRequestParameter(NAME));
        product.setDescription(requestWrapper.getRequestParameter(DESCRIPTION));
        product.setPrice(new BigDecimal(requestWrapper.getRequestParameter(PRICE)));
        product.setPhoto(requestWrapper.getRequestParameter(PHOTO));
        product.setSale(Boolean.parseBoolean(requestWrapper.getRequestParameter(SALE)));

        ProductValidator productValidator = new ProductValidator();

        try {

            if (productValidator.isProductCorrect(product)) {
                ProductService productService = new ProductServiceImpl();
                productService.insert(product);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(REDIRECT_TO_ADMIN_PRODUCT_FIRST_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(PRODUCT, product);

                route.setResponsePath(FORWARD_TO_ADMIN_PRODUCT_CREATE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}