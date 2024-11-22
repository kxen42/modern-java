package org.fotm.stream.assignment;

import org.fotm.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Assignment (QID 2.1858)
 * <p>
 * 11. Given the {@code AnotherBook} class (in the zip file), declare a {@code List} typed for {@code AnotherBook} named <em>books</em>
 * with the following {@code AnotherBook}’s:
 * <p>
 * <ul>
 * <li>a. title=”Gone with the wind”, genre=”Fiction”</li>
 * <li>b. title=”Bourne Ultimatum”, genre=”Thriller”</li>
 * <li>c. title=”The Client”, genre=”Thriller”</li>
 * </ul>
 * <p>
 * Declare the following: {@code List<String> genreList = new ArrayList<>();}<br>
 * Stream books so that <em>genreList</em> refers to a {@code List} containing the genres of the books in the <em>books</em> {@code List}.
 */
public class Q2_1858 {
    public static void main(String[] args) {
        // I merged AnotherBook into Book
        // I also made genre a list
        List<Book> books = Arrays.asList(
            new Book("Gone with the wind", "Fiction"),
            new Book("Bourne Ultimatum", "Thriller"),
            new Book("The Client", "Thriller")
        );

        List<String> genreList = new ArrayList<>();
        books.stream()
             .flatMap(book -> book.getGenres()
                                  .stream())
             .forEach(genreList::add);
        System.out.println("genresList: " + genreList);

        Set<String> uniqueGenreList = books.stream()
                                           .flatMap(book -> book.getGenres()
                                                                .stream())
                                           .collect(Collectors.toSet());
        System.out.println("uniqueGenreList: " + uniqueGenreList);
    }
}
