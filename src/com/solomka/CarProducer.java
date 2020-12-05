package com.solomka;

import com.solomka.enums.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CarProducer {

    public static Car getNewCar() {
        UUID uuid = geId();
        Brand brand = getBrand();
        int year = getYear();
        int mileage = getMileage(year);
        int price = getPrice(year);

        return new Car(uuid, brand, year, mileage, price);
    }

    public static List<Car> getListOfCars(int size) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cars.add(CarProducer.getNewCar());
        }
        return cars;
    }

    private static UUID geId() {
        return UUID.randomUUID();
    }

    private static Brand getBrand() {
        return Brand.randomBrand();
    }

    private static int getYear() {
        int random = new Random().nextInt(31);
        return random + 1990;
    }

    private static int getMileage(int year) {
        return (2021 - year) * new Random().nextInt(10_000);
    }

    private static int getPrice(int year) {
        int random = 0;
        if (year >= 1990 && year <= 2000) {
            random = 500 + new Random().nextInt(3000);
        } else if (year > 2000 && year < 2010) {
            random = 3000 + new Random().nextInt(6000);
        } else {
            random = 9000 + new Random().nextInt(70_000);
        }
        return random;
    }
}
