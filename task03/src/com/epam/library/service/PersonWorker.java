package com.epam.library.service;

import com.epam.library.model.Book;
import com.epam.library.model.Library;
import com.epam.library.model.Person;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PersonWorker {

    private static final Logger LOG = Logger.getLogger(PersonWorker.class);

    private static final int WAITING_TIME = 3;

    public static void takeBook(Person person) {
        Book tempBook;

        person.getLock().lock();
        try {
            if (person.getBooks().size() < Person.MAX_NUMBER_OF_BOOKS &&
                    Library.getInstance().getBooks().size() != 0) {
                tempBook = Library.getInstance().getBooks().get
                        (new Random().nextInt(Library.getInstance().getBooks().size()));
                Library.getInstance().getBooks().remove(tempBook);
                person.getBooks().add(tempBook);
                LOG.info(person.getName() + " took " + tempBook.getTitle());
            } else if (person.getBooks().size() == Person.MAX_NUMBER_OF_BOOKS) {
                LOG.info(person.getName() + " wanted to take the book, but he has a maximum of books");
            } else if (Library.getInstance().getBooks().size() == 0) {
                LOG.info(person.getName() + " wanted to take the book, but the library is empty");
            }
        } finally {
            person.getLock().unlock();
        }

    }

    public static void returnBook(Person person) {
        Book tempBook;

        person.getLock().lock();
        try {
            if (person.getBooks().size() != 0) {
                tempBook = person.getBooks().get(new Random().nextInt(person.getBooks().size()));
                Library.getInstance().getBooks().add(tempBook);
                person.getBooks().remove(tempBook);
                LOG.info(person.getName() + " returned " + tempBook.getTitle());
            } else {
                LOG.info(person.getName() +
                        " tried to return the book, but he hasn't debts to the library");
            }
        } finally {
            person.getLock().unlock();
        }

    }

    public static void exchangeBook(Person person) {
        Book tempBook, oldBook;

        if (!person.getBooks().isEmpty()) {
            tempBook = defineBook(findMyRoomsBooks(person.getBooks()));

            if (tempBook != null) {
                try {
                    LOG.info(person.getName() + " wants to exchange " + tempBook.getTitle());
                    oldBook = tempBook;
                    tempBook = person.getExchanger().exchange(tempBook, WAITING_TIME, TimeUnit.SECONDS);
                    person.getBooks().remove(oldBook);
                    person.getBooks().add(tempBook);
                    LOG.info(person.getName() + " exchanged " + oldBook.getTitle() +
                            " for " + tempBook.getTitle());
                } catch (InterruptedException e) {
                    LOG.error("An error occurred while the method was running: " + e);
                } catch (TimeoutException e) {
                    LOG.debug(person.getName() +
                            " waited, but no one agreed to make an exchange. The book remained with him");
                }
            }

        }

    }

    public static void leaveLibrary(Person person) {
        List<Book> roomBooks = findMyRoomsBooks(person.getBooks());

        person.getLock().lock();
        try {
            for (Book book : roomBooks) {
                person.getBooks().remove(book);
                Library.getInstance().getBooks().add(book);
            }
            LOG.info(person.getName() + " left the library. He left " +
                    roomBooks.size() + " book(s) in the reading room and took " +
                    person.getBooks().size() + " book(s) with him");
        } finally {
            person.getLock().unlock();
        }

    }

    private static List<Book> findMyRoomsBooks(List<Book> books) {
        List<Book> roomBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isReadingRoom()) {
                roomBooks.add(book);
            }
        }

        return roomBooks;
    }

    private static Book defineBook(List<Book> books) {
        return books.isEmpty() ? null : books.get(new Random().nextInt(books.size()));
    }

}