package org.fotm.stream.assignment;

import org.fotm.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Assignment (QID 2.1847)
 * <p>
 * 8. Given the {@code Book} class (in the zip file), declare a List typed for {@code Book} with the following {@code Book}’s:
 * <p>
 * a. title="Gone with the wind", price=5.0<br>
 * b. title="Gone with the wind", price=10.0<br>
 * c. title="Atlas shrugged", price=15.0<br>
 * <p>
 * In a pipeline which has no return type:
 * <ol>
 * <li>stream the books</li>
 * <li>using the {@code collect()} method, generate a {@code Map} that maps the book title to its price</li>
 * <li>using {@code forEach()}, output the title and price of each entry in the map</li>
 * </ol>
 * What happened and why? Fix this by using the {@code Collectors.toMap(Function, Function, BinaryOperator)} method.
 */
public class Q_1847 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(new Book("Gone with the wind", 5.0),
                                        new Book("Gone with the wind", 10.0),
                                        new Book("Atlas shrugged", 15.0)
        );

        // causes
        // Exception in thread "main" java.lang.IllegalStateException: Duplicate key Gone with the wind (attempted merging values 5.0 and 10.0)
//        Map<String, Double> collect = books.stream()
//                                           .collect(toMap(
//                                               Book::getTitle,
//                                               Book::getPrice
//                                           ));

        Map<String, Double> collect = books.stream()
                                           .collect(toMap(
                                               Book::getTitle,
                                               Book::getPrice,
                                               (x, y) -> x * y
                                           ));
        System.out.println(collect);
    }
}
