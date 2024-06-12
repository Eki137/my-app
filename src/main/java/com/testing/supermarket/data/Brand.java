package com.testing.supermarket.data;

import jakarta.persistence.Entity;

@Entity
public class Brand extends AbstractEntity{
    private String name;

    public Brand(){}
    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
