package com.epam.herman.uladzimir.command.common;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

import static com.epam.herman.uladzimir.command.AttributeName.LOCALE;

/**
 * This class is used to change the application language.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class SetLocaleCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        requestWrapper.putSessionAttribute
                (LOCALE, requestWrapper.getRequestParameter(LOCALE));
        return new Route(ResponseType.REDIRECT, requestWrapper.getReferer());
    }

}