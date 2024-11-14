package org.fotm.stream.assignment;

import org.fotm.model.Book;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Assignment (QID 2.1809)
 * <p>
 * 6. Given the {@code Book} class (in the zip file), declare a {@code List<Book>} with the following Book’s:
 * <p>
 * a. title="Thinking in Java", price=30.0<br>
 * b. title="Java in 24 hrs", price=20.0<br>
 * c. title="Java Recipes", price=10.0<br>
 * <p>
 * Stream the books and calculate the average price of the books whose price is <em>&gt; 10</em>.
 * <p>
 * Change the filter to books whose price is <em>&gt; 90</em>. Ensure you do not get an exception.
 */
public class Q2_1809 {
    public static void main(String[] args) {
        System.out.println(" ----- Q2_1809");
        List<Book> books = List.of(
            new Book("Thinking in Java", 30.0),
            new Book("Java in 24 hrs", 20.0),
            new Book("Java Recipes", 10.0)
        );

        OptionalDouble average = books.stream()
                                      .filter(b -> b.getPrice() > 10.0)
                                      .mapToDouble(Book::getPrice)
                                      .average();
        System.out.println(average.orElse(Double.NaN));

        average = books.stream()
                       .filter(b -> b.getPrice() > 90.0)
                       .mapToDouble(Book::getPrice)
                       .average();
        System.out.println(average.orElse(Double.NaN));
    }
}
