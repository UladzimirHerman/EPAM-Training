package com.epam.herman.uladzimir.service;

import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link UserService} contains special methods for entity {@link User}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface UserService extends GenericService<User> {

    /**
     * Check the existence of a user by login
     *
     * @param login user's login
     * @return true, if user exists or false, if user doesn't exist
     * @throws ServiceException exception of service level
     */
    boolean isUserExist(String login) throws ServiceException;

    /**
     * Search user by login
     *
     * @param login user's login
     * @return particular user
     * @throws ServiceException exception of service level
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Search full information about all users
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of users
     * @throws ServiceException exception of service level
     */
    List<User> findFullInfo(int offset, int limit) throws ServiceException;

    /**
     * Search full information about user by user's identifier
     *
     * @param userId user's identifier
     * @return particular user
     * @throws ServiceException exception of service level
     */
    User findFullInfoById(int userId) throws ServiceException;

}