package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.impl.UserInfoDAOImpl;
import com.epam.herman.uladzimir.entity.UserInfo;
import com.epam.herman.uladzimir.service.UserInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class {@link UserInfoServiceImpl} is used for interacting the entity
 * {@link UserInfo} with DAO level. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see UserInfoDAOImpl
 * @see UserInfoService
 */
public class UserInfoServiceImpl extends AbstractService<UserInfo, UserInfoDAOImpl>
        implements UserInfoService {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoServiceImpl.class);

    @Override
    protected UserInfoDAOImpl getObjectDAOImpl() {
        return new UserInfoDAOImpl();
    }

}