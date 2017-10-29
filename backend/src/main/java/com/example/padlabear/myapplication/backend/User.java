package com.example.padlabear.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;


@Entity
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private String location;
    @Ignore
    private String registered;


    public User(final String id,
                final String firstName,
                final String lastName,
                final String age,
                final String location,
                final String registered) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
        this.registered = registered;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getRegistered() {
        return registered;
    }
}
