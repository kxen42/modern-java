package org.fotm.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Dilip.Sundarraj
 * @author kxen42
 * @see <a href="https://github.com/dilipsundarraj1/java-8/blob/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/data/Student.java">Course Student</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Comparable<Student> {
    private String name;
    private int gradeLevel;
    private double gpa;
    private String gender;
    private int noteBooks;
    private Grade grade;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Optional<Bike> bike;
    @Builder.Default
    private List<String> activities = new ArrayList<>();

    public Student(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::getGradeLevel)
                         .thenComparing(Student::getGrade)
                         .thenComparing(Student::getName)
                         .compare(this, o);
    }

    public Optional<Bike> getBike() {
        return this.bike;
    }

    public void setBike(Bike bike) {
        this.bike = Optional.ofNullable(bike);
    }

    public enum Grade {A, B, C, D, F}
}
