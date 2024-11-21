package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Book {
    private String title;
    private double price;
    private List<String> genres;
    private List<Person> authors;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
