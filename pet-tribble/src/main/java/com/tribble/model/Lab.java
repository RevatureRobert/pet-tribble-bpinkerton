package com.tribble.model;

import java.util.ArrayList;
import java.util.List;

public class Lab {
    private int id;
    private String name;
    //One to many
    private List<Tribble> tribbles;

    public Lab() {
        this.tribbles = new ArrayList<>();
    }

    public Lab(String name) {
        this.name = name;
    }

    public Lab(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Lab(int id, String name, List<Tribble> tribbles) {
        this.id = id;
        this.name = name;
        this.tribbles = tribbles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tribble> getTribbles() {
        return tribbles;
    }

    public void setTribbles(List<Tribble> tribbles) {
        this.tribbles = tribbles;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tribbles=" + tribbles +
                '}';
    }
}
