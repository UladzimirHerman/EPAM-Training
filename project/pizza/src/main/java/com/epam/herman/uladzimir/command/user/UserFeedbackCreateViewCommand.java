package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_FEEDBACK_CREATE_PAGE;

/**
 * This class is used to get the feedback create page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserFeedbackCreateViewCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        return new Route(ResponseType.FORWARD, FORWARD_TO_USER_FEEDBACK_CREATE_PAGE);
    }

}