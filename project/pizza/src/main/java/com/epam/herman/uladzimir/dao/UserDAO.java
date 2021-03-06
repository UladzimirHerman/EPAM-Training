package com.epam.herman.uladzimir.dao;

import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.DAOException;

/**
 * Interface {@link UserDAO} contains special methods
 * for entity {@link User}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Check the existence of a user by login
     *
     * @param login user's login
     * @return true, if user exists or false, if user doesn't exist
     * @throws DAOException exception of database level
     */
    boolean isUserExist(String login) throws DAOException;

    /**
     * Search user by login
     *
     * @param login user's login
     * @return particular user
     * @throws DAOException exception of database level
     */
    User findByLogin(String login) throws DAOException;

}