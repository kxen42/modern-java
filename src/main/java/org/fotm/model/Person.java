package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String firstName, lastName;
    private Integer age;

    public Person(Integer age, String firstName) {
        this.age = age;
        this.firstName = firstName;
    }
}
