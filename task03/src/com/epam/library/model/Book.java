package com.epam.library.model;

import java.util.Objects;

public class Book {

    private String title;
    private boolean readingRoom;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isReadingRoom() {
        return readingRoom;
    }

    public void setReadingRoom(boolean readingRoom) {
        this.readingRoom = readingRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return readingRoom == book.readingRoom &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, readingRoom);
    }

    @Override
    public String toString() {
        StringBuilder book = new StringBuilder(getTitle());
        book.append(", in reading room: ");
        book.append(isReadingRoom());
        return book.toString();
    }

}