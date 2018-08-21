package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Feedback;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.FeedbackService;
import com.epam.herman.uladzimir.service.impl.FeedbackServiceImpl;
import com.epam.herman.uladzimir.validator.FeedbackValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_FEEDBACK_CREATE_PAGE;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_FEEDBACK_FIRST_PAGE;

/**
 * This class is used to add new feedback to the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserAddFeedbackCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserAddFeedbackCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        Feedback feedback = new Feedback();
        feedback.setUser((User) requestWrapper.getSessionAttribute(USER));
        feedback.setComment(requestWrapper.getRequestParameter(COMMENT));
        feedback.setRating(Integer.parseInt(requestWrapper.getRequestParameter(RATING)));
        feedback.setDate(new Date());

        FeedbackValidator feedbackValidator = new FeedbackValidator();

        try {

            if (feedbackValidator.isFeedbackCorrect(feedback)) {
                FeedbackService feedbackService = new FeedbackServiceImpl();
                feedbackService.insert(feedback);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(REDIRECT_TO_USER_FEEDBACK_FIRST_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);

                route.setResponsePath(FORWARD_TO_USER_FEEDBACK_CREATE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}