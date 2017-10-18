package com.example.padlabear.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SimpleUser {

    @Id
    private String id;
    private String FirstName;
    private String LastName;
    private String Age;
    private String Location;

    public SimpleUser() {
    }

    public SimpleUser(String id, String FirstName, String LastName, String Age, String Location) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
        this.Location = Location;
    }


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAge() {
        return Age;
    }

    public String getLocation() {
        return Location;
    }
}
