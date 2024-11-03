package org.fotm.stream.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Examples of the most basic uses of the {@code Files} stream related methods.
 * <p>
 * Also demonstrates that variables in the context of a lambda are effectively final.
 */
public class SimpleFileExamples {
    private List<String> instanceList = new ArrayList<>();


    public static void main(String[] args) {
        SimpleFileExamples simpleFileExamples = new SimpleFileExamples();
        simpleFileExamples.readLines();

        simpleFileExamples.readIntoInstanceList();

        List<String> argList = new ArrayList<>();
        simpleFileExamples.readIntoArgument(argList);

        simpleFileExamples.readIntoLocalVar();

    }

    public void readLines() {
        System.out.println(" ----- readLines");
        // R.O.T. wrap in try-catch
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/ZombieIpsum.txt"))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readIntoInstanceList() {
        System.out.println(" ----- readIntoInstanceList");
        // R.O.T. wrap in try-catch
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/ZombieIpsum.txt"))) {
            instanceList = new ArrayList<>();  // <-- has no effect
            stream.forEach(instanceList::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // prints all lines
        System.out.println(instanceList);
    }

    public void readIntoArgument(List<String> arg) {
        System.out.println(" ----- readIntoArgument");
        // R.O.T. wrap in try-catch
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/ZombieIpsum.txt"))) {
            arg = new ArrayList<>();  // <-- has no effect
            stream.forEach(arg::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // prints all lines
        System.out.println(arg);
    }

    public void readIntoLocalVar() {
        System.out.println(" ----- readIntoLocalVar");
        List<String> localArg = new ArrayList<>();
        // R.O.T. wrap in try-catch
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/ZombieIpsum.txt"))) {
            localArg = new ArrayList<>();  // <-- has no effect
            stream.forEach(localArg::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // prints all lines
        System.out.println(localArg);
    }
}
