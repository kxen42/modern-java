package org.fotm.java17.record;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoodlingRecordTest {

    @Test
    void negativePrice() {
        assertThrows(IllegalArgumentException.class, () ->
            new NoodlingRecord("Fred", BigDecimal.valueOf(-1), "test"));
    }

    @Test
    void nollOfEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
            new NoodlingRecord("", BigDecimal.ZERO, "test"));
        assertThrows(IllegalArgumentException.class, () ->
            new NoodlingRecord(null, BigDecimal.ZERO, "test"));
        assertThrows(IllegalArgumentException.class, () ->
            new NoodlingRecord("\t", BigDecimal.ZERO, "test"));
    }

    @Test
    void alternateConstructor() {
        var r = new NoodlingRecord("Fred", BigDecimal.TEN);
        assertEquals("default value", r.purpose());
    }

    @Test
    void comparison() {
        var r1 = new NoodlingRecord("Fred", BigDecimal.ZERO);
        var r2 = new NoodlingRecord("Fred", BigDecimal.ZERO);
        var r3 = new NoodlingRecord("Barney", BigDecimal.ZERO);

        assertNotSame(r1, r2);
        assertEquals(r1, r2);
        assertNotEquals(r1, r3);

        // sorting records are not automatically Comparable; this will not compile
//        Stream.of(r1,r3,r2).sorted().forEach(System.out::println);

        Stream.of(r1, r3, r2)
              .sorted(Comparator.comparing(NoodlingRecord::name))
              .forEach(System.out::println);

        NoodlingRecord.spewSorted(r1, r2, r3);
    }

}