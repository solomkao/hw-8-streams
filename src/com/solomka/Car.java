package com.solomka;

import com.solomka.enums.Brand;

import java.util.UUID;

public class Car {
    private UUID id;
    private Brand brand;
    private int year;
    private int mileage;
    private int price;

    public Car(UUID id, Brand brand, int year, int mileage, int price) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s MY%d %dkm $%d", brand.toString(), year, mileage, price);
    }
}


