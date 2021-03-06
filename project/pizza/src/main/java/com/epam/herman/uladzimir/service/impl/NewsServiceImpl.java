package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.impl.NewsDAOImpl;
import com.epam.herman.uladzimir.entity.News;
import com.epam.herman.uladzimir.service.NewsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class {@link NewsServiceImpl} is used for interacting the entity {@link News}
 * with DAO level. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see NewsDAOImpl
 * @see NewsService
 */
public class NewsServiceImpl extends AbstractService<News, NewsDAOImpl>
        implements NewsService {

    private static final Logger LOGGER = LogManager.getLogger(NewsServiceImpl.class);

    @Override
    protected NewsDAOImpl getObjectDAOImpl() {
        return new NewsDAOImpl();
    }

}