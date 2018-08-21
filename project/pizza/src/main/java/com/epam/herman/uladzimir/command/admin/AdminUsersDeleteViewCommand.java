package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.UserService;
import com.epam.herman.uladzimir.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.USER;
import static com.epam.herman.uladzimir.command.AttributeName.USER_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_USERS_DELETE_PAGE;

/**
 * This class is used to get the user account delete page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminUsersDeleteViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminUsersDeleteViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserService userService = new UserServiceImpl();

        try {
            User user = userService.findFullInfoById
                    (Integer.parseInt(requestWrapper.getRequestParameter(USER_ID)));
            requestWrapper.putRequestAttribute(USER, user);

            route.setResponsePath(FORWARD_TO_ADMIN_USERS_DELETE_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}