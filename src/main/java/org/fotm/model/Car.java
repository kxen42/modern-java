package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Car {
    private final String make;
    private final Integer year;
    private final Double mileage;
    private final List<String> defects;

//  public Car(List<String> defects, String make, Double mileage, Integer year) {
//    this.defects = defects;
//    this.make = make;
//    this.mileage = mileage;
//    this.year = year;
//  }
}
