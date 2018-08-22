package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.UserInfo;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoServiceImplTest {

    private UserInfoServiceImpl userInfoService;

    public UserInfoServiceImplTest() {
        userInfoService = new UserInfoServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(4, userInfoService.findAll(16, 8).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(userInfoService.findById(1));
    }

    @Test
    public void updateTest() throws ServiceException {
        UserInfo userInfo = userInfoService.findById(20);
        userInfo.setNote("Updated note");
        userInfoService.update(userInfo);
        assertEquals(userInfo, userInfoService.findById(20));
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(20, userInfoService.count());
    }

}