package ru.aza954.SpringMvc2.models;

public class Book {
    private int book_id;
    private int year;
    private String name;
    private String author;

    public Book(){

    }

    public Book(int book_id, int year, String name, String author) {
        this.book_id = book_id;
        this.year = year;
        this.name = name;
        this.author = author;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
