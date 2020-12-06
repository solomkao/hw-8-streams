package com.solomka;

import com.solomka.enums.Brand;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        List<Car> cars = CarProducer.getListOfCars(200);
        System.out.println("\tBefore processing");
        cars.forEach(System.out::println);

        List<Car> sortedCars = cars.stream()
                .filter(car -> car.getBrand().equals(Brand.TESLA) || car.getBrand().equals(Brand.AUDI))
                .filter(car -> car.getYear() >= 2018)
                .filter(car -> car.getMileage() < 40_000)
                .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()))
                .collect(Collectors.toList());
        long size = sortedCars.stream().count();

        Map<UUID, Car> mapCars = sortedCars.stream()
                .skip(size - 3)
                .collect(Collectors.toMap(car -> car.getId(), car -> car));

        System.out.println("\tAfter processing");
        for (Map.Entry<UUID, Car> entry : mapCars.entrySet()) {
            System.out.println(entry.getValue() + " [" + entry.getKey() + "]");
        }
    }
}
