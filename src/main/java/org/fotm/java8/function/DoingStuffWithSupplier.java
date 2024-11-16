package org.fotm.java8.function;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DoingStuffWithSupplier {

    public static void main(String[] args) {

        // Things are more interesting with Stream.collect()
        Supplier<List<Car>> cars = CarGenerator::createCars;
        Supplier<Car> car = () -> Car.builder().build();
        Supplier<List<Car>> newList = ArrayList::new;

        System.out.println(cars.get());
        System.out.println("-------------------------");
        System.out.println(car.get());
        System.out.println("-------------------------");
        System.out.println(newList.get());
    }
}
