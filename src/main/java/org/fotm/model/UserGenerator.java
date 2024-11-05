package org.fotm.model;

import java.util.List;
import java.util.UUID;

public class UserGenerator {

    /**
     * Create an unmodifiable list of Flintstones and Jetsons.
     *
     * @return unmodifiable List of User objects
     */
    public static List<User> createUsers() {
        // it would be nice to use something to generate dummy data
        return List.of(
            User.builder()
                .age(36)
                .city("Bedrock")
                .firstName("Fred")
                .id(UUID.randomUUID())
                .lastName("Flintstone")
                .build(),
            User.builder()
                .age(34)
                .city("Bedrock")
                .firstName("Wilma")
                .id(UUID.randomUUID())
                .lastName("Flintstone")
                .build(),
            User.builder()
                .age(34)
                .city("Bedrock")
                .firstName("Betty")
                .id(UUID.randomUUID())
                .lastName("Rubble")
                .build(),
            User.builder()
                .age(35)
                .city("Bedrock")
                .firstName("Barney")
                .id(UUID.randomUUID())
                .lastName("Rubble")
                .build(),
            User.builder()
                .age(45)
                .city("Zap")
                .firstName("George")
                .id(UUID.randomUUID())
                .lastName("Jetson")
                .build(),
            User.builder()
                .age(8)
                .city("Zap")
                .firstName("Elroy")
                .id(UUID.randomUUID())
                .lastName("Jetson")
                .build(),
            User.builder()
                .age(39)
                .city("Zap")
                .firstName("Jane")
                .id(UUID.randomUUID())
                .lastName("Jetson")
                .build(),
            User.builder()
                .age(15)
                .city("Zap")
                .firstName("Judy")
                .id(UUID.randomUUID())
                .lastName("Jetson")
                .build(),
            User.builder()
                .age(5)
                .city("Bikini Bottom")
                .firstName("Patrick")
                .id(UUID.randomUUID())
                .lastName("Star")
                .build(),
            User.builder()
                .age(5)
                .city("Bikini Bottom")
                .firstName("SpongeBob")
                .id(UUID.randomUUID())
                .lastName("Squarepants")
                .build()
            ,
            User.builder()
                .age(2)
                .city("Bikini Bottom")
                .firstName("Sandy")
                .id(UUID.randomUUID())
                .lastName("Squirrel")
                .build()
        );
    }
}
