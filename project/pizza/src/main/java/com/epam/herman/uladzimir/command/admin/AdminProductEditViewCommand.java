package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.ProductService;
import com.epam.herman.uladzimir.service.impl.ProductServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.PRODUCT;
import static com.epam.herman.uladzimir.command.AttributeName.PRODUCT_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_PRODUCT_EDIT_PAGE;

/**
 * This class is used to get the product edit page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminProductEditViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminProductEditViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        ProductService productService = new ProductServiceImpl();

        try {
            Product product = productService.findById
                    (Integer.parseInt(requestWrapper.getRequestParameter(PRODUCT_ID)));
            requestWrapper.putRequestAttribute(PRODUCT, product);

            route.setResponsePath(FORWARD_TO_ADMIN_PRODUCT_EDIT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}