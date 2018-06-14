package com.epam.library.controller;

import com.epam.library.model.Book;
import com.epam.library.model.Library;
import com.epam.library.model.Person;
import org.apache.log4j.Logger;

import java.util.Random;

public class Starter {

    private static final Logger LOG = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        Library library = new Library();
        LOG.info("Library successfully created");

        for (int i = 1; i <= 15; i++) {
            Book book = new Book();
            book.setTitle("Book №" + i);
            book.setReadingRoom(new Random().nextBoolean());
            library.getBooks().add(book);
        }
        LOG.info("In library successfully added " + library.getBooks().size() + " books");
        LOG.info("LIBRARY'S BOOKS: " + library);

        Thread personOne = new Thread(new Person("John", library));
        Thread personTwo = new Thread(new Person("Rob", library));
        Thread personThree = new Thread(new Person("Mark", library));
        Thread personFour = new Thread(new Person("Howard", library));
        Thread personFive = new Thread(new Person("Andy", library));

        personOne.start();
        personTwo.start();
        personThree.start();
        personFour.start();
        personFive.start();

    }
}