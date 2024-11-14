package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private Grade grade;
    public enum Grade {A, B, C, D, F}
}
