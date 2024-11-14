package org.fotm.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://github.com/dilipsundarraj1/java-8/blob/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/data/Student.java">Course Student</a>
 * @author Dilip.Sundarraj
 * @author kxen42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private String name;
    private int gradeLevel;
    private double gpa;
    private String gender;
    private int noteBooks;
    private Grade grade;
    public enum Grade {A, B, C, D, F}
    private Optional<Bike> bike;
    private List<String> activities = new ArrayList<>();
}
