package com.testing.supermarket.data;

import jakarta.persistence.Entity;

@Entity
public class Type extends AbstractEntity{
    private String name;

    public Type() {}
    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
