package org.fotm.stream;

import org.fotm.model.Member;
import org.fotm.model.MemberGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * flatMap mapper function must return a stream
 */
public class FlatMap {

    private static final List<Member> members = MemberGenerator.createMembers();

    public static void main(String[] args) {
        getCourseNamesForMemebers();
        getWordsFromLinesInFile();
        streamOfLists();
    }

    private static void getCourseNamesForMemebers() {
        members.stream()
               .flatMap(m -> m.getCourses()
                              .stream())
               .forEach(System.out::println);
    }

    private static void getWordsFromLinesInFile() {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/ZombieIpsum.txt"))) {
            lines.flatMap(line ->
                              Stream.of(line.split("[ \n\t.?,]+")))
                 .forEach(System.out::print);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void streamOfLists() {
        List<String> flintstones = List.of("Fred", "Wilma");
        List<String> bradys = List.of("Greg", "Peter", "Bobby", "Cindy", "Jan", "Marcia");
        List<String> castaways = List.of("Skipper", "Gilligan");

        Stream.of(flintstones, bradys, castaways) // this creates a stream of lists
            .flatMap(Collection::stream) // this runs stream on each list to get a list of names
            .forEach(System.out::println);  // spew names
    }
}
