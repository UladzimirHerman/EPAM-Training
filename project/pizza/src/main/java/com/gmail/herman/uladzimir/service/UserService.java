package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link UserService} contains specific methods
 * for entity {@link User}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface UserService extends GenericService<User> {

    /**
     * Check the existence of a user by login
     * @param login user's login
     * @return true, if user exists or false, if user doesn't exist
     * @throws ServiceException exception of service level
     */
    boolean isUserExist(String login) throws ServiceException;

    /**
     * Search user by login
     * @param login user's login
     * @return particular user
     * @throws ServiceException exception of service level
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Search all information about user by user's identifier
     * @param id user's identifier
     * @return particular user
     * @throws ServiceException exception of service level
     */
    User findUserAndUserInfoById(int id) throws ServiceException;

    /**
     * Search all information about all users
     * @return list of users
     * @throws ServiceException exception of service level
     */
    List<User> findAllUserAndUserInfo() throws ServiceException;

    /**
     * Delete all information about user by user's identifier
     * @param id users's identifier for deleting
     * @throws ServiceException exception of service level
     */
    void deleteUserAndUserInfoById(int id) throws ServiceException;

}