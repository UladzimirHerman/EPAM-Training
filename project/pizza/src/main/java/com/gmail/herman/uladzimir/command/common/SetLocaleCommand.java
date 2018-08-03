package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.command.AttributeName.LOCALE;

public class SetLocaleCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();
        requestWrapper.putSessionAttribute(LOCALE, requestWrapper.getRequestParameter(LOCALE));
        route.setResponsePath(requestWrapper.getReferer());
        return route;
    }

}