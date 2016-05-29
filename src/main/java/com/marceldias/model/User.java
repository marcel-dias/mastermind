package com.marceldias.model;

public class User {

    private String name;

    public User() {
        //The default constructor is used in json deserialization
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
