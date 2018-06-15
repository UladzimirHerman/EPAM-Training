package com.epam.library.model;

import com.epam.library.service.PersonWorker;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable {

    private static final Logger LOG = Logger.getLogger(Person.class);

    public static final int MAX_NUMBER_OF_BOOKS = 3;
    private static final int NUMBER_OF_ITERATIONS = 10;

    private String name;
    private List<Book> books;
    private Exchanger<Book> exchanger;
    private ReentrantLock lock;

    public Person() {
        books = new ArrayList<>();
        exchanger = new Exchanger<>();
        lock = new ReentrantLock();
    }

    public Person(String name, Exchanger<Book> exchanger, ReentrantLock lock) {
        this.name = name;
        this.exchanger = exchanger;
        this.lock = lock;
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Exchanger<Book> getExchanger() {
        return exchanger;
    }

    public void setExchanger(Exchanger<Book> exchanger) {
        this.exchanger = exchanger;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        LOG.info(getName() + " came to the library");

        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    PersonWorker.takeBook(this);
                    break;
                case 1:
                    PersonWorker.returnBook(this);
                    break;
                case 2:
                    PersonWorker.exchangeBook(this);
                    break;
            }
        }

        PersonWorker.leaveLibrary(this);
    }

}