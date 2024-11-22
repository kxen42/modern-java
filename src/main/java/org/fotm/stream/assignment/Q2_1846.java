package org.fotm.stream.assignment;

import org.fotm.model.Book;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Assignment (QID 2.1846)
 * <p>
 * 7. Given the {@code Book} class (in the zip file), declare a {@code List} typed for {@code Book} with the following
 * {@code Book}’s:
 * <p>
 * a. title=”Atlas Shrugged”, price=10.0<br>
 * b. title=”Freedom at Midnight”, price=5.0<br>
 * c. title=”Gone with the wind”, price=5.0<br>
 * <p>
 * Stream the books and instantiate a Map named <em>bookMap</em> that maps the book title to its price.
 * To do this use the {@code collect(Collectors.toMap(Function fnToGetKey, Function fnToGetValue))}.
 * Iterate through <em>bookMap</em> (using the {@code Map forEach(BiConsumer)} method). The {@code BiConsumer}
 * only outputs price where the title begins with “A”.
 */
public class Q2_1846 {

    public static void main(String[] args) {
        List<Book> books = List.of(Book.builder()
                                       .title("Atlas Shrugged")
                                       .price(10.0)
                                       .build(),
                                   Book.builder()
                                       .title("Freedom at Midnight")
                                       .price(5.0)
                                       .build(),
                                   Book.builder()
                                       .title("Gone with the wind")
                                       .price(5.0)
                                       .build()
        );

        Map<String, Double> bookMap = books.stream()
                                           .collect(toMap(
                                               Book::getTitle,
                                               Book::getPrice
                                           ));

        bookMap.forEach((k, v) -> {
            if (k.startsWith("A"))
                System.out.println(v);
        });
    }
}
