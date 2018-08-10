package com.gmail.herman.uladzimir.command.user;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.FeedbackService;
import com.gmail.herman.uladzimir.service.impl.FeedbackServiceImpl;
import com.gmail.herman.uladzimir.validator.FeedbackValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.command.AttributeName.FEEDBACK;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_USER_FEEDBACK_EDIT_PAGE;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_USER_FEEDBACK_PAGE;

public class UserUpdateFeedbackCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserUpdateFeedbackCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        Feedback feedback = new Feedback();
        feedback.setId(Integer.parseInt(requestWrapper.getRequestParameter(FEEDBACK_ID)));
        feedback.setComment(requestWrapper.getRequestParameter(COMMENT));
        feedback.setRating(Integer.parseInt(requestWrapper.getRequestParameter(RATING)));

        FeedbackValidator feedbackValidator = new FeedbackValidator();

        try {

            if (feedbackValidator.isFeedbackCorrect(feedback)) {
                FeedbackService feedbackService = new FeedbackServiceImpl();
                feedbackService.update(feedback);
                route.setResponsePath(REDIRECT_TO_USER_FEEDBACK_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(FEEDBACK, feedback);
                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_USER_FEEDBACK_EDIT_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}