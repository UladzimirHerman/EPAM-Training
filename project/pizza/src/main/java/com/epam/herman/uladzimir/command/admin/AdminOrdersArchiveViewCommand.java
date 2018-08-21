package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderService;
import com.epam.herman.uladzimir.service.impl.OrderServiceImpl;
import com.epam.herman.uladzimir.util.PaginationUtil;
import com.epam.herman.uladzimir.validator.PaginationValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.herman.uladzimir.command.AttributeName.ORDERS;
import static com.epam.herman.uladzimir.command.AttributeName.PAGE;
import static com.epam.herman.uladzimir.command.AttributeName.PAGE_QUANTITY;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_ORDERS_ARCHIVE_PAGE;

/**
 * This class is used to get the archive orders page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminOrdersArchiveViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminOrdersArchiveViewCommand.class);

    private static final int ITEMS_ON_THE_PAGE = 8;

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        int page = Integer.parseInt(requestWrapper.getRequestParameter(PAGE));

        PaginationValidator paginationValidator = new PaginationValidator();

        try {

            if (paginationValidator.isPageNumberCorrect(page)) {
                OrderService orderService = new OrderServiceImpl();
                List<Order> orders = orderService.findFullInfoArchive
                        (PaginationUtil.defineOffset(page, ITEMS_ON_THE_PAGE), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(ORDERS, orders);

                int pageQuantity = PaginationUtil.definePageQuantity
                        (orderService.countArchive(), ITEMS_ON_THE_PAGE);
                requestWrapper.putRequestAttribute(PAGE_QUANTITY, pageQuantity);

                route.setResponsePath(FORWARD_TO_ADMIN_ORDERS_ARCHIVE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}