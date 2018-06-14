package com.epam.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(books, library.books);
    }

    @Override
    public int hashCode() {

        return Objects.hash(books);
    }

    @Override
    public String toString() {
        StringBuilder library = new StringBuilder();

        for(Book book : books){
            library.append(book);
            library.append("; ");
        }

        return library.toString();
    }

}