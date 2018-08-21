package com.epam.herman.uladzimir.command.common;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_REGISTRATION_PAGE;

/**
 * This class is used to get the registration page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class RegistrationCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        return new Route(ResponseType.FORWARD, FORWARD_TO_REGISTRATION_PAGE);
    }

}