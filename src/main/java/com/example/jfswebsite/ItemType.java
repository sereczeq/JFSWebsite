package com.example.jfswebsite;

public enum ItemType {
    All("All"),
    Food("Food"),
    Electronic("Electronics"),
    Instrument("Instrument"),
    Toy("Toy");

    String name;

    ItemType(String name)
    {
        this.name = name;
    }
}
