package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Car {
    private final String make;
    private final Integer year;
    private final Double mileage;
    private final List<String> defects;
}
