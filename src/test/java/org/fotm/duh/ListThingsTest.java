package org.fotm.duh;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListThingsTest {

    private final ListThings listThings = new ListThings();

    @Test
    public void subList() {
        List<String> names = listThings.strings();
        assertEquals(List.of("Fred", "Barney", "Wilma", "Betty"), names);
        assertEquals(List.of("Fred", "Barney"), names.subList(0, names.size() - 2));
    }

    @Test
    public void listClear() {
        List<String> names = listThings.strings();
        assertEquals(List.of("Fred", "Barney", "Wilma", "Betty"), names);

        assertEquals("java.util.ImmutableCollections.ListN", names.getClass()
                                                                  .getCanonicalName());
        assertThrows(UnsupportedOperationException.class, () -> names.clear());

        List<String> mutableList = new ArrayList<>(names);
        assertDoesNotThrow(mutableList::clear);
        mutableList = new ArrayList<>(names);
        mutableList.clear();
        assertTrue(mutableList.isEmpty());
    }

}