package org.fotm.stream.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SimpleFileExamples {

    public static void main(String[] args) {
        readLines();
    }

    public static void readLines() {
        // R.O.T. wrap in try-catch
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/ZombieIpsum.txt"))) {
            stream.forEach(
                System.out::println
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
