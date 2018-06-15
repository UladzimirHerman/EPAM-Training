package com.epam.library.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private static volatile Library instance;

    private Library() {
        books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            synchronized (Library.class) {
                if (instance == null) {
                    instance = new Library();
                }
            }
        }
        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder library = new StringBuilder();

        for (Book book : books) {
            library.append(book);
            library.append("; ");
        }

        return library.toString();
    }

}