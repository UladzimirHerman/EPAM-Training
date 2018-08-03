package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.entity.UserRole;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserService;
import com.gmail.herman.uladzimir.service.impl.UserServiceImpl;
import com.gmail.herman.uladzimir.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_REGISTRATION_PAGE;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

public class CreateAccountCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateAccountCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        User user = new User();
        user.setLogin(requestWrapper.getRequestParameter(LOGIN));
        user.setPassword(requestWrapper.getRequestParameter(PASSWORD));

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
        UserService userService = new UserServiceImpl();

        try {

            if (!userService.isUserExist(user.getLogin()) &&
                    userValidator.isUserCorrect(user) && userValidator.isUserInfoCorrect(userInfo)) {
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                user.setUserRole(UserRole.USER);
                user.setUserInfo(userInfo);

                userService.insert(user);
                route.setResponsePath(REDIRECT_TO_LOGIN_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_REGISTRATION_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}