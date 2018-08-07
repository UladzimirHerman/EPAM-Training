package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.UserDAO;
import com.gmail.herman.uladzimir.dao.UserInfoDAO;
import com.gmail.herman.uladzimir.dao.impl.UserDAOImpl;
import com.gmail.herman.uladzimir.dao.impl.UserInfoDAOImpl;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.DAOException;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl extends AbstractService<User, UserDAOImpl>
        implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserDAOImpl getObjectDAOImpl() {
        return new UserDAOImpl();
    }

    @Override
    public boolean isUserExist(String login) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();

        try {
            return userDAO.isUserExist(login);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when checking the existence of a user: ", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();
        User user;

        try {
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding user by login: ", e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User findUserAndUserInfoById(int id) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();
        User user;

        try {
            user = userDAO.findById(id);
            UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
            user.setUserInfo(userInfoDAO.findById(user.getId()));
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding all info about user by id: ", e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public List<User> findAllUserAndUserInfo(int offset, int limit)
            throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();
        List<User> users;

        try {
            users = userDAO.findAll(offset, limit);
            UserInfoDAO userInfoDAO = new UserInfoDAOImpl();

            for (User user : users) {
                user.setUserInfo(userInfoDAO.findById(user.getId()));
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding all info about users: ", e);
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public void deleteUserAndUserInfoById(int id) throws ServiceException {
        UserDAO userDAO = new UserDAOImpl();

        try {
            userDAO.deleteById(id);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when deleting user by id: ", e);
            throw new ServiceException(e);
        }
    }

}