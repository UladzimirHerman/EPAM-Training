package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.command.AttributeName.LOCALE;

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