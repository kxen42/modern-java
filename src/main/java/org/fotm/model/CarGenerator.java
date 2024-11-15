package org.fotm.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarGenerator {

    public static List<Car> createCars() {
        return Arrays.asList(
            Car.builder()
               .make("Audi")
               .year(1963)
               .mileage(250000.9)
               .defects(Arrays.asList("rust", "cracked windshield", "no seatbelts", "flat front tires"))
               .build(),
            Car.builder()
               .make("Audi")
               .year(2001)
               .mileage(90000.5)
               .defects(Arrays.asList("torn driver side seat", "AM radio not working", "front springs kaput"))
               .build(),
            Car.builder()
               .make("Audi")
               .year(2015)
               .mileage(50150.4)
               .defects(Collections.singletonList("chip in windshield"))
               .build(),
            Car.builder()
               .make("Ford")
               .year(1988)
               .mileage(16124.3)
               .defects(Arrays.asList("shift knob loose", "short in steering column", "spontaneous combustion", "rust"))
               .build(),
            Car.builder()
               .make("Ford")
               .year(1974)
               .mileage(200000.0)
               .defects(Arrays.asList("can see road through rust hole",
                                      "plastic bags in trunk to keep water out",
                                      "Maverick enough said"))
               .build()
        );
    }
}
