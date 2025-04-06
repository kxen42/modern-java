package org.fotm.model;

public sealed interface Animal permits Cat, Dog {

    String name();
    void name(String name);
    void move(String message);
}
