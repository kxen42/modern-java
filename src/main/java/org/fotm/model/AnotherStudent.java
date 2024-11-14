package org.fotm.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dilip.Sundarraj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnotherStudent {
    private String name;
    private int gradeLevel;
    private double gpa;
    private String gender;
    private int noteBooks;
    private Optional<Bike> bike;
    private List<String> activities = new ArrayList<>();
}
