package com.shahid.collection;

public abstract class BaseDemo {
    final static Student johnWick;
    final static Student sheldonCooper;
    final static Student johnCena;
    final static Student harryPotter;

    static {
        johnWick = new Student("John Wick", 29);
        sheldonCooper = new Student("Sheldon Cooper", 14);
        harryPotter = new Student("Harry Potter", 16);
        johnCena = new Student("John Cena", 30);
    }
}
