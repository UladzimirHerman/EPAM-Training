package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.ProductService;
import com.epam.herman.uladzimir.service.impl.ProductServiceImpl;
import com.epam.herman.uladzimir.util.PaginationUtil;
import com.epam.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.herman.uladzimir.command.AttributeName.PAGE;
import static com.epam.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.epam.herman.uladzimir.command.AttributeName.PRODUCT_LIST;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_PRODUCT_PAGE;

/**
 * This class is used to get the products for sale page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserProductViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserProductViewCommand.class);

    private static final int ITEMS_ON_THE_PAGE = 8;

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        int page = Integer.parseInt(requestWrapper.getRequestParameter(PAGE));

        PaginationValidator paginationValidator = new PaginationValidator();

        try {

            if (paginationValidator.isPageNumberCorrect(page)) {
                ProductService productService = new ProductServiceImpl();
                List<Product> products = productService.findForSale
                        (PaginationUtil.defineOffset(page, ITEMS_ON_THE_PAGE), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PRODUCT_LIST, products);

                int pageQuantity = PaginationUtil.definePageQuantity
                        (productService.countForSale(), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PAGE_QUANTITY, pageQuantity);

                route.setResponsePath(FORWARD_TO_USER_PRODUCT_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}