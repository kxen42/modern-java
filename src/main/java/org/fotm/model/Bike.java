package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 @see <a href="https://github.com/dilipsundarraj1/java-8/blob/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/data/Bike.java">Course Bike</a></a>
 * @author Dilip.Sundarraj
 */
@Data
@AllArgsConstructor
public class Bike {

    private String name;
    private String model;
}
