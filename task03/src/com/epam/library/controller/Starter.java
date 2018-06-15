package com.epam.library.controller;

import com.epam.library.model.Book;
import com.epam.library.model.Library;
import com.epam.library.model.Person;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.ReentrantLock;

public class Starter {

    private static final Logger LOG = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        Exchanger<Book> exchanger = new Exchanger<>();
        ReentrantLock lock = new ReentrantLock();

        for (int i = 1; i <= 15; i++) {
            Book book = new Book();
            book.setTitle("Book №" + i);
            book.setReadingRoom(new Random().nextBoolean());
            Library.getInstance().getBooks().add(book);
        }
        LOG.info("In library successfully added " +
                Library.getInstance().getBooks().size() + " books");
        LOG.info("LIBRARY'S BOOKS: " + Library.getInstance());

        Thread personOne = new Thread(new Person("John", exchanger, lock));
        Thread personTwo = new Thread(new Person("Rob", exchanger, lock));
        Thread personThree = new Thread(new Person("Mark", exchanger, lock));
        Thread personFour = new Thread(new Person("Howard", exchanger, lock));
        Thread personFive = new Thread(new Person("Andy", exchanger, lock));

        personOne.start();
        personTwo.start();
        personThree.start();
        personFour.start();
        personFive.start();

    }
}