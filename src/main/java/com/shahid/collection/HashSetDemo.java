package com.shahid.collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo extends SetDemo {
    public static void main(String[] args) {
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(44);
        hashSet.add(6);
        hashSet.add(9);
        hashSet.add(13);
        hashSet.add(1);
        hashSet.add(3);
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(3);


        printValues(hashSet);
        System.out.println("Set is empty: " + hashSet.isEmpty());
        System.out.println("Size of set: " + hashSet.size());
        System.out.println("Set contains 1: " + hashSet.contains(1));
        System.out.println("Set contains 5: " + hashSet.contains(5));

        printValues(hashSet);

        hashSet.remove(2);
        printValues(hashSet);

        hashSet.clear();
        System.out.println("Set is empty: " + hashSet.isEmpty());
        printValues(hashSet);

        Set<Student> students = new HashSet<>();
        Student johnWick = new Student("John Wick", 30);
        Student sheldonCooper = new Student("Sheldon Cooper", 14);
        Student harryPotter = new Student("Harry Potter", 30);

        students.add(johnWick);
        students.add(harryPotter);
        students.add(sheldonCooper);

        printValues(students);
        System.out.println(students.contains(sheldonCooper));
        System.out.println(students.contains(new Student("John Wick", 30)));
    }
}
