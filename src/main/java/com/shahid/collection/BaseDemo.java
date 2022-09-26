package com.shahid.collection;

public class BaseDemo {
    final static Student johnWick;
    final static Student sheldonCooper;
    final static Student johnCena;
    final static Student harryPotter;

    static {
        johnWick = new Student("John Wick", 30);
        sheldonCooper = new Student("Sheldon Cooper", 14);
        harryPotter = new Student("Harry Potter", 30);
        johnCena = new Student("John Cena", 30);
    }
}
