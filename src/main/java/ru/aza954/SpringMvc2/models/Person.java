package ru.aza954.SpringMvc2.models;

import jakarta.validation.constraints.*;
public class Person {
    private int user_id;
    private String fullname;
    @Min(value = 2000,message = "Ti konch")
    private int yearofbirth;

    public Person(){

    }
    public Person(int user_id, String fullname, int yearofbirth) {
        this.user_id = user_id;
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


}
