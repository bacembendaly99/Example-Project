package com.example.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "added")
    private boolean added;

    public Food() {

    }
    public Food(String name, String description, boolean added) {
        this.name = name;
        this.description = description;
        this.added = added;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean isadded) {
        this.added = isadded;
    }
    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", desc=" + description + ", added=" + added + "]";
    }
}