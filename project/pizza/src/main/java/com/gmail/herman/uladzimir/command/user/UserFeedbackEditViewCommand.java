package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.FeedbackService;
import com.gmail.herman.uladzimir.service.impl.FeedbackServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.FEEDBACK;
import static com.gmail.herman.uladzimir.command.AttributeName.FEEDBACK_ID;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_FEEDBACK_EDIT_PAGE;

/**
 * This class is used to get the feedback edit page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserFeedbackEditViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserFeedbackEditViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        FeedbackService feedbackService = new FeedbackServiceImpl();

        try {
            Feedback feedback = feedbackService.findById
                    (Integer.parseInt(requestWrapper.getRequestParameter(FEEDBACK_ID)));
            requestWrapper.putRequestAttribute(FEEDBACK, feedback);

            route.setResponsePath(FORWARD_TO_USER_FEEDBACK_EDIT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}