package com.solomka;

import com.solomka.enums.Brand;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        List<Car> cars = CarProducer.getListOfCars(200);
        System.out.println("\tBefore processing...");
        cars.forEach(System.out::println);

        List<Car> sortedCars = cars.stream()
                .filter(car -> car.getBrand().equals(Brand.TESLA) || car.getBrand().equals(Brand.AUDI))
                .filter(car -> car.getYear() >= 2018)
                .filter(car -> car.getMileage() < 40_000)
                .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()))
                .collect(Collectors.toList());
        long size = sortedCars.stream().count();

        System.out.println("\n\tAfter sorting...");
        sortedCars.forEach(System.out::println);
        System.out.println();
        Map<UUID, Car> mapCars = sortedCars.stream()
                .skip(size - 3)
                .collect(Collectors.toMap(car -> car.getId(), car -> car));

        System.out.println("\tAfter processing...");
        for (Map.Entry<UUID, Car> entry : mapCars.entrySet()) {
            System.out.println(entry.getValue() + " [" + entry.getKey() + "]");
        }

//        ..............One stream..............
        Map<UUID, Car> mapCars2 = cars.stream()
                .filter(car -> car.getBrand().equals(Brand.TESLA) || car.getBrand().equals(Brand.AUDI))
                .filter(car -> car.getYear() >= 2018)
                .filter(car -> car.getMileage() < 40_000)
                .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()))
                .sorted(Comparator.comparingInt(Car::getPrice))
                .limit(3)
                .collect(Collectors.toMap(car -> car.getId(), car -> car));

        System.out.println("\tAfter processing 2.0...");
        for (Map.Entry<UUID, Car> entry : mapCars2.entrySet()) {
            System.out.println(entry.getValue() + " [" + entry.getKey() + "]");
        }

//        ..............One stream 2.0..............
//        int i[] = {0};
//        Map<UUID, Car> mapCars3 = cars.stream()
//                .filter(car -> car.getBrand().equals(Brand.TESLA) || car.getBrand().equals(Brand.AUDI))
//                .filter(car -> car.getYear() >= 2018)
//                .filter(car -> car.getMileage() < 40_000)
//                .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()))
//                .peek(car -> ++i[0])
//                .skip(i[0])
//                .collect(Collectors.toMap(car -> car.getId(), car -> car));
//        System.out.println("i[0] = " + i[0]);
//
//        System.out.println("\tAfter processing 2.1...");
//        for (Map.Entry<UUID, Car> entry : mapCars3.entrySet()) {
//            System.out.println(entry.getValue() + " [" + entry.getKey() + "]");

//        ..............5 streams..............
        List<Car> listOfAudiAndTesla = cars.stream()
                .filter(car -> car.getBrand().equals(Brand.TESLA) || car.getBrand().equals(Brand.AUDI))
                .collect(Collectors.toList());
        System.out.println("\n\tTesla and Audi");
        listOfAudiAndTesla.forEach(System.out::println);

        List<Car> listOfCarsMadeByYear = listOfAudiAndTesla.stream()
                .filter(car -> car.getYear() >= 2018)
                .collect(Collectors.toList());
        System.out.println("\n\tTesla and Audi was made after 2018");
        listOfCarsMadeByYear.forEach(System.out::println);

        List<Car> listOfCarsByMileage = listOfCarsMadeByYear.stream()
                .filter(car -> car.getMileage() < 40_000)
                .collect(Collectors.toList());
        System.out.println("\n\tTesla and Audi was made after 2018 and drove less than 40 000 mile");
        listOfCarsByMileage.forEach(System.out::println);

        List<Car> sortedCarsByPrice = listOfCarsByMileage.stream()
                .sorted(Comparator.comparingInt(Car::getPrice))
                .collect(Collectors.toList());
        System.out.println("\n\tSorted list of Tesla and Audi");
        sortedCarsByPrice.forEach(System.out::println);

        Map<UUID, Car> mapCars5 = sortedCarsByPrice.stream()
                .limit(3)
                .collect(Collectors.toMap(car -> car.getId(), car -> car));

        System.out.println("\n\tThe cheapest three Tesla and Audi...");
        for (Map.Entry<UUID, Car> entry : mapCars5.entrySet()) {
            System.out.println(entry.getValue() + " [" + entry.getKey() + "]");
        }


    }
}
