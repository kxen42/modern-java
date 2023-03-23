package org.fotm.java8.nio;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Some examples come from blogs
 * https://mkyong.com/java/java-files-find-examples/
 */
public class NioFiles {

    public static void main(String[] args) throws IOException {
        System.out.println("Find files by name using Files.find()");
        System.out.println("Expect nothing");
        Path path = Paths.get("/Users/Bug");
        List<Path> found = findByFileName(path, "no-there");
        found.forEach(System.out::println);
        System.out.println("Expect something");
        found = findByFileName(path, ".zshrc");
        found.forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        System.out.println("Find files by size");
        List<Path> bySize = findBySize(Paths.get("/Users/Bug/Downloads"), 1024 * 1024);
        bySize.forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        System.out.println("Simple list directories");
        List<Path> ls = listDir(Paths.get("/Users/Bug/Downloads"));
        ls.forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        System.out.println("Walk file tree");
        Path root = Paths.get("/Users/Bug/tmp");
        List<Path> leafs = walkTree(root, 1);
        leafs.forEach(System.out::println);
    }

    // from https://mkyong.com/java/java-files-find-examples/
    public static List<Path> findByFileName(Path path, String fileName) throws IOException {
        List<Path> found;

        try (Stream<Path> pathStream =
                     Files.find(path,
                             1,
                             (aPath, fileAttributes) ->
                                     Files.isReadable(aPath) &&
                                             aPath.getFileName().toString().equalsIgnoreCase(fileName)
                     )) {
            found = pathStream.collect(Collectors.toList());
        }
        // and empty list is created when nothing is found
        return found;
    }

    // using the BasicFileAttributes.size() is much cleaner than using Files.size(p) which
    // can throw IOException
    public static List<Path> findBySize(Path path, Integer size) throws IOException {
        //skip directories

        List<Path> found;
        try (Stream<Path> pathStream =
                     Files.find(path,
                             1,
                             (aPath, fileAttributes) -> {
                                 if (Files.isDirectory(aPath)) return false;
                                 return fileAttributes.size() >= size;
                             }
                     )) {
            found = pathStream.collect(Collectors.toList());
        }
        // and empty list is created when nothing is found
        return found;
    }

    public static List<Path> listDir(Path path) throws IOException {
        List<Path> found;
        try (Stream<Path> pathStream = Files.list(path)) {
            found = pathStream.collect(Collectors.toList());
        }
        return found;
    }

    public static List<Path> walkTree(Path path, int maxDepth) throws IOException {
        List<Path> found;

        try (Stream<Path> pathStream = Files.walk(path, maxDepth, FileVisitOption.FOLLOW_LINKS)) {
            found = pathStream.collect(Collectors.toList());
        }
        return found;
    }
}
