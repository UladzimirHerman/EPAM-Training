package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.impl.UserInfoDAOImpl;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.service.UserInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserInfoServiceImpl extends AbstractService<UserInfo, UserInfoDAOImpl>
        implements UserInfoService {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfoDAOImpl getObjectDAOImpl() {
        return new UserInfoDAOImpl();
    }

}