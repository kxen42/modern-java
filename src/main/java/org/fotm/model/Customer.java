package org.fotm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private final String name;
    private final Integer[] orders;
}
