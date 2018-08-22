package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.entity.UserInfo;
import com.epam.herman.uladzimir.entity.UserRole;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserServiceImpl userService;

    public UserServiceImplTest() {
        userService = new UserServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(10, userService.findAll(0, 10).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(userService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(21);
        userInfo.setName("Name");
        userInfo.setSurname("Surname");
        userInfo.setPhone("375291234567");
        userInfo.setCity("City");
        userInfo.setStreet("Street");
        userInfo.setBuilding("25");
        userInfo.setHousing("A");
        userInfo.setApartment("37");
        userInfo.setNote("Note");

        User user = new User();
        user.setId(21);
        user.setLogin("correct_example@gmail.com");
        user.setPassword("password");
        user.setUserRole(UserRole.ADMIN);
        user.setUserInfo(userInfo);

        userService.insert(user);
        assertEquals(user, userService.findFullInfoById(21));
    }

    @Test
    public void updateTest() throws ServiceException {
        User user = userService.findById(21);
        user.setPassword("Updated password");
        user.setUserRole(UserRole.USER);
        userService.update(user);
        assertEquals(user, userService.findById(21));
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(21, userService.count());
    }

    @Test
    public void isUserExistTest() throws ServiceException {
        assertFalse(userService.isUserExist("correct_example!1@gmail.com"));
    }

    @Test
    public void findByLoginTest() throws ServiceException {
        assertNotNull(userService.findByLogin("correct_example@gmail.com"));
    }

    @Test
    public void findFullInfoTest() throws ServiceException {
        assertEquals(10, userService.findFullInfo(0, 10).size());
    }

    @Test
    public void findFullInfoByIdTest() throws ServiceException {
        assertNotNull(userService.findFullInfoById(1));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        userService.deleteById(21);
        userService.findById(21);
    }

}