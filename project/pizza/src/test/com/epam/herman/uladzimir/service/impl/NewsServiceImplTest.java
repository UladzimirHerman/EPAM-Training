package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.News;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class NewsServiceImplTest {

    private NewsServiceImpl newsService;

    public NewsServiceImplTest() {
        newsService = new NewsServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(3, newsService.findAll(4, 4).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(newsService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        News news = new News();
        news.setId(8);
        User user = new User();
        user.setId(1);
        news.setUser(user);
        news.setTitle("Correct title");
        news.setContent("Correct content");
        news.setPhoto("Correct photo name");
        news.setDate(new Date());
        newsService.insert(news);
        News newsNew = newsService.findById(8);
        news.setDate(newsNew.getDate());
        assertEquals(news, newsNew);
    }

    @Test
    public void updateTest() throws ServiceException {
        News news = newsService.findById(8);
        news.setTitle("Updated title");
        news.setContent("Updated content");
        news.setPhoto("Updated photo name");
        newsService.update(news);
        assertEquals(news, newsService.findById(8));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        newsService.deleteById(8);
        newsService.findById(8);
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(7, newsService.count());
    }

}