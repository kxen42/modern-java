package org.fotm.java8.function;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class DoingStuffWithBiFunction {

    private static final List<Car> cars = CarGenerator.createCars();

    private static final Predicate<Car> isFord = c -> "Ford".equals(c.getMake());

    private static final BiFunction<Car, Predicate<Car>, Car> increaseMileage = (t, u) ->
    {
        if (isFord.test(t)) {
            Double mileage = t.getMileage();
            return Car.builder()
                      .defects(t.getDefects())
                      .make(t.getMake())
                      .mileage(mileage * 2)
                      .year(t.getYear())
                      .build();
        }
        return t;
    };

    public static void main(String[] args) {
        increaseFordMileage();
    }
    
    public static void increaseFordMileage() {
        System.out.println(" ----- increaseFordMileage");
        List<Car> localCars = new ArrayList<>(cars.size());
        
        for(Car c : cars) {
            localCars.add(increaseMileage.apply(c, isFord));
        }

        cars.stream().filter(isFord).forEach(System.out::println);
        System.out.println("-------------------------");
        localCars.stream().filter(isFord).forEach(System.out::println);
    }
}
