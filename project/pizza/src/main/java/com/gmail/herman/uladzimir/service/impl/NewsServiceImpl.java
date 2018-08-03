package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.impl.NewsDAOImpl;
import com.gmail.herman.uladzimir.entity.News;
import com.gmail.herman.uladzimir.service.NewsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class NewsServiceImpl extends AbstractService<News, NewsDAOImpl>
        implements NewsService {

    private static final Logger LOGGER = LogManager.getLogger(NewsServiceImpl.class);

    @Override
    public NewsDAOImpl getObjectDAOImpl() {
        return new NewsDAOImpl();
    }

}