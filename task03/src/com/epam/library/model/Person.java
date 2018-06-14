package com.epam.library.model;

import com.epam.library.service.BookExchanger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Person implements Runnable {

    private static final Logger LOG = Logger.getLogger(Person.class);

    private static final int MAX_NUMBER_OF_BOOKS = 3;
    private static final int NUMBER_OF_ITERATIONS = 10;

    private String name;
    private Library library;
    private List<Book> books;

    public Person() {
        books = new ArrayList<>();
    }

    public Person(String name, Library library) {
        this.name = name;
        this.library = library;
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {

        LOG.info(getName() + " came to the library");

        Book tempBook;
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {

            switch (random.nextInt(3)) {

                //взять книгу в библиотеке
                case 0:

                    if (books.size() < MAX_NUMBER_OF_BOOKS && library.getBooks().size() != 0) {
                        tempBook = library.getBooks().get(random.nextInt(library.getBooks().size()));
                        books.add(tempBook);
                        library.getBooks().remove(tempBook);
                        LOG.info(getName() + " took " + tempBook.getTitle());
                    } else if (library.getBooks().size() == 0) {
                        LOG.info(getName() + " wanted to take the book, but the library is empty");
                    } else {
                        LOG.info(getName() + " wanted to take the book, but he has a maximum of books");
                    }

                    break;

                //отдать книгу в библиотеку
                case 1:

                    if (books.size() != 0) {
                        tempBook = books.get(random.nextInt(books.size()));
                        library.getBooks().add(tempBook);
                        books.remove(tempBook);
                        LOG.info(getName() + " returned " + tempBook.getTitle());
                    } else {
                        LOG.info(getName() + " checked his debts. He hasn't debts, all books are turned over");
                    }

                    break;

                //поменяться книгами в читальном зале
                case 2:
                    Book oldBook;
                    tempBook = null;

                    if (!books.isEmpty()) {

                        for (Book b : books) {
                            if (b.isReadingRoom()) {
                                tempBook = b;
                            }
                        }

                        if (tempBook != null) {
                            try {
                                LOG.info(getName() + " wants to exchange " + tempBook.getTitle());
                                oldBook = tempBook;
                                tempBook = BookExchanger.getInstance().exchangeBook(tempBook);
                                books.remove(oldBook);
                                books.add(tempBook);
                                LOG.info(getName() + " exchanged " + oldBook.getTitle() + " for " + tempBook.getTitle());
                            } catch (InterruptedException e) {
                                LOG.error("An error occurred while the method was running: " + e);
                            } catch (TimeoutException e) {
                                LOG.debug(getName() + " waited, but no one agreed to make an exchange. " +
                                        "The book remained with him");
                            }
                        }

                    }

                    break;

            }

        }

        List<Book> roomBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isReadingRoom()) {
                roomBooks.add(book);
            }
        }

        for (Book book : roomBooks) {
            books.remove(book);
            library.getBooks().add(book);
        }

        LOG.info(getName() + " left the library. He left " + roomBooks.size() +
                " books in the reading room and took " + books.size() + " with him");
    }

}