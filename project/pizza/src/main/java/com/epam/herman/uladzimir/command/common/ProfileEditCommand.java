package com.epam.herman.uladzimir.command.common;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.entity.UserInfo;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.UserInfoService;
import com.epam.herman.uladzimir.service.impl.UserInfoServiceImpl;
import com.epam.herman.uladzimir.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.command.AttributeName.NOTE;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_PROFILE_PAGE;

/**
 * This class is used to edit the profile.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class ProfileEditCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(ProfileEditCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(requestWrapper.getRequestParameter(NAME));
        userInfo.setSurname(requestWrapper.getRequestParameter(SURNAME));
        userInfo.setPhone(requestWrapper.getRequestParameter(PHONE));
        userInfo.setCity(requestWrapper.getRequestParameter(CITY));
        userInfo.setStreet(requestWrapper.getRequestParameter(STREET));
        userInfo.setBuilding(requestWrapper.getRequestParameter(BUILDING));
        userInfo.setHousing(requestWrapper.getRequestParameter(HOUSING));
        userInfo.setApartment(requestWrapper.getRequestParameter(APARTMENT));
        userInfo.setNote(requestWrapper.getRequestParameter(NOTE));

        UserValidator userValidator = new UserValidator();

        try {

            if (userValidator.isUserInfoCorrect(userInfo)) {
                User user = (User) requestWrapper.getSessionAttribute(USER);
                userInfo.setId(user.getId());

                UserInfoService userInfoService = new UserInfoServiceImpl();
                userInfoService.update(userInfo);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(requestWrapper.getReferer());
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(USER_INFO, userInfo);

                route.setResponsePath(FORWARD_TO_PROFILE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}