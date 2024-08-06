package ru.aza954.SpringMvc2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "fullname")

    private String fullname;
    @Min(value = 1950,message = "Минимум 1950")
    @Max(value = 2023,message = "Максимум 2023")
    @Column(name = "yearofbirth")
    private int yearofbirth;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private List<Book> books;

    public Person(){

    }
    public Person(String fullname, int yearofbirth) {

        this.fullname = fullname;
        this.yearofbirth = yearofbirth;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getYearofbirth() {
        return yearofbirth;
    }

    public void setYearofbirth(int yearofbirth) {
        this.yearofbirth = yearofbirth;
    }

    public List<Book> getBooks() {
        if (this.books == null){
            this.books = new ArrayList<>();
        }
        return books;
    }

    public void setBooks(List<Book> books) {

    }

    @Override
    public String toString() {
        return "Person{" +
                "user_id=" + user_id +
                ", fullname='" + fullname + '\'' +
                ", yearofbirth=" + yearofbirth +
                '}';
    }
}
