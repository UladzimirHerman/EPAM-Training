package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.UserDAO;
import com.epam.herman.uladzimir.dao.impl.UserDAOImpl;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.DAOException;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.UserInfoService;
import com.epam.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@link UserServiceImpl} is used for interacting the entity {@link User}
 * with DAO level. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see UserDAOImpl
 * @see UserService
 */
public class UserServiceImpl extends AbstractService<User, UserDAOImpl>
        implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Filling a list of users with information
     *
     * @param users list of users
     * @throws ServiceException exception of service level
     */
    private void fillWithInfo(List<User> users) throws ServiceException {
        UserInfoService userInfoService = new UserInfoServiceImpl();

        for (User user : users) {
            user.setUserInfo(userInfoService.findById(user.getId()));
        }

        LOGGER.info("Successful fill a list of users with info");
    }

    /**
     * Filling the user with information
     *
     * @param user particular user for filling with information
     * @throws ServiceException exception of service level
     */
    private void fillWithInfo(User user) throws ServiceException {
        UserInfoService userInfoService = new UserInfoServiceImpl();
        user.setUserInfo(userInfoService.findById(user.getId()));
        LOGGER.info("Successful fill user with info");
    }

    @Override
    protected UserDAOImpl getObjectDAOImpl() {
        return new UserDAOImpl();
    }

    @Override
    public boolean isUserExist(String login) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();
        boolean isUserExist;

        try {
            isUserExist = userDAO.isUserExist(login);
            LOGGER.info("Successful check the existence of a user");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when checking the existence of a user: ", e);
            throw new ServiceException("Error in checking the existence of a user", e);
        }

        return isUserExist;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();
        User user;

        try {
            user = userDAO.findByLogin(login);
            LOGGER.info("Successful search user by login");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching user by login: ", e);
            throw new ServiceException("Error in searching user by login", e);
        }

        return user;
    }

    @Override
    public List<User> findFullInfo(int offset, int limit) throws ServiceException {
        List<User> users = findAll(offset, limit);

        if (!users.isEmpty()) {
            fillWithInfo(users);
        }

        LOGGER.info("Successful search list of users with full info");
        return users;
    }

    @Override
    public User findFullInfoById(int userId) throws ServiceException {
        User user = findById(userId);
        fillWithInfo(user);
        LOGGER.info("Successful fill a user with info");
        return user;
    }

}