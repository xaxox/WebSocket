package org.xaxox.controller;

import org.xaxox.web.socket.rest.model.RestModel;

@RestModel(name = "player")
public class Player {

    long id;
    String name;
    int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
