package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.ProductService;
import com.gmail.herman.uladzimir.service.impl.ProductServiceImpl;
import com.gmail.herman.uladzimir.util.PaginationUtil;
import com.gmail.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.PAGE;
import static com.gmail.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.gmail.herman.uladzimir.command.AttributeName.PRODUCT_LIST;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_PRODUCT_PAGE;

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