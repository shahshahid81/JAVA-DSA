package com.shahid.collection;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo extends SetDemo {
    public static void main(String[] args) {
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(44);
        linkedHashSet.add(6);
        linkedHashSet.add(9);
        linkedHashSet.add(13);
        linkedHashSet.add(1);
        linkedHashSet.add(3);
        linkedHashSet.add(2);

        printValues(linkedHashSet);
        System.out.println("Set is empty: " + linkedHashSet.isEmpty());
        System.out.println("Size of set: " + linkedHashSet.size());
        System.out.println("Set contains 1: " + linkedHashSet.contains(1));
        System.out.println("Set contains 5: " + linkedHashSet.contains(5));

        printValues(linkedHashSet);

        linkedHashSet.remove(2);
        printValues(linkedHashSet);

        linkedHashSet.clear();
        printValues(linkedHashSet);

        Set<Student> students = new LinkedHashSet<>();

        students.add(johnWick);
        students.add(sheldonCooper);
        students.add(harryPotter);

        printValues(students);
        System.out.println(students.contains(sheldonCooper));
        System.out.println(students.contains(new Student("John Wick", 30)));
    }
}
