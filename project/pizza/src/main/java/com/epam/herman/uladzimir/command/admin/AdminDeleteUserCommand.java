package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.UserService;
import com.epam.herman.uladzimir.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.USER_ID;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_USERS_FIRST_PAGE;

/**
 * This class is used to delete the user account from the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminDeleteUserCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(AdminDeleteUserCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserService userService = new UserServiceImpl();

        try {
            userService.deleteById
                    (Integer.parseInt(requestWrapper.getRequestParameter(USER_ID)));

            route.setResponseType(ResponseType.REDIRECT);
            route.setResponsePath(REDIRECT_TO_ADMIN_USERS_FIRST_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}