package org.fotm.java17.sealed;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MaverickTest {

    @Test
    void instanceOf() {
        var m = new Maverick("yes", "yes yes yes", BigDecimal.valueOf(1_000));
        System.out.println(m);
        assertInstanceOf(Crappy.class, m);
        assertInstanceOf(Cheap.class, m);
        assertInstanceOf(Product.class, m);
        assertInstanceOf(Ford.class, m);
    }

}