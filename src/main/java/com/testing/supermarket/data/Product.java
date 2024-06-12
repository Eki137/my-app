package com.testing.supermarket.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Product extends AbstractEntity{

    @NotEmpty
    private String description = "";

    @NotNull
    @PositiveOrZero
    private Integer stock = 0;

    @NotNull
    @Positive
    private Double price = 0.0;

    @NotNull
    @PositiveOrZero
    @DecimalMax(value = "1.0", inclusive = false)
    private Double discount = 0.0;

    @NotNull
    @ManyToOne
    private Brand brand;

    @NotNull
    @ManyToOne
    private Type type;

    @Override
    public String toString(){
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
