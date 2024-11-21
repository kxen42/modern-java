package org.fotm.stream;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.List;

public class Match {

    private static final List<Car> cars = CarGenerator.createCars();

    public static void main(String[] args) {
        allMatch();
        noneMatch();
        anyMatch();
    }

    public static void allMatch() {
        System.out.println(" ----- allMatch");
        boolean allMatch = cars.stream()
                               .allMatch(car -> car.getMake()
                                                   .equals("Ford"));
        System.out.println("allMatch Ford: " + allMatch);
    }

    public static void noneMatch() {
        System.out.println(" ----- noneMatch");
        boolean noneMatch = cars.stream()
                                .noneMatch(car -> car.getMake()
                                                     .equals("Ford"));
        System.out.println("noneMatch Ford: " + noneMatch);
    }

    public static void anyMatch() {
        System.out.println(" ----- noneMatch");
        boolean anyMatch = cars.stream()
                               .anyMatch(car -> car.getMake()
                                                   .equals("Ford"));
        System.out.println("anyMatch Ford: " + anyMatch);
    }
}
