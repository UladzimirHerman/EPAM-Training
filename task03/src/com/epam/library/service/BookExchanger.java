package com.epam.library.service;

import com.epam.library.model.Book;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BookExchanger {

    private static final int TIMEOUT = 3;

    private static BookExchanger instance;
    private Exchanger<Book> exchanger;

    private BookExchanger() {
        exchanger = new Exchanger<>();
    }

    public static BookExchanger getInstance() {
        if (instance == null) {
            instance = new BookExchanger();
        }
        return instance;
    }

    public Book exchangeBook(Book book) throws InterruptedException, TimeoutException {
        return exchanger.exchange(book, TIMEOUT, TimeUnit.SECONDS);
    }

}