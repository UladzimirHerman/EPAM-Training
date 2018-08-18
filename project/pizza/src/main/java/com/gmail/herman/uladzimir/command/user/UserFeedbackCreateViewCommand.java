package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_FEEDBACK_CREATE_PAGE;

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