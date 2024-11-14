package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private double price;
    private List<String> genres;
    private List<Person> authors;
}
