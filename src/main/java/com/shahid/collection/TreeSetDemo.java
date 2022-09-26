package com.shahid.collection;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo extends SetDemo {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(44);
        treeSet.add(6);
        treeSet.add(9);
        treeSet.add(13);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);

        printValues(treeSet);
        System.out.println("Set is empty: " + treeSet.isEmpty());
        System.out.println("Size of set: " + treeSet.size());
        System.out.println("Set contains 1: " + treeSet.contains(1));
        System.out.println("Set contains 5: " + treeSet.contains(5));

        printValues(treeSet);

        treeSet.remove(2);
        printValues(treeSet);

        treeSet.clear();
        System.out.println("Set is empty: " + treeSet.isEmpty());
        printValues(treeSet);

        Student johnWick = new Student("John Wick", 30);
        Student johnCena = new Student("John Cena", 30);
        Student sheldonCooper = new Student("Sheldon Cooper", 14);
        Student harryPotter = new Student("Harry Potter", 30);

        Set<Student> students = new TreeSet<>();
        students.add(johnWick);
        students.add(sheldonCooper);
        students.add(harryPotter);
        students.add(johnCena);

        printValues(students);
        System.out.println(students.contains(sheldonCooper));
        System.out.println(students.contains(new Student("John Wick", 30)));

        Set<Student> nameSortedStudents = new TreeSet<>(new Student.StudentNameComparator());
        nameSortedStudents.add(johnWick);
        nameSortedStudents.add(sheldonCooper);
        nameSortedStudents.add(harryPotter);
        nameSortedStudents.add(johnCena);

        printValues(nameSortedStudents);
        System.out.println(nameSortedStudents.contains(sheldonCooper));
        System.out.println(nameSortedStudents.contains(new Student("John Wick", 30)));

        Set<Student> ageSortedStudents = new TreeSet<>(new Student.StudentAgeComparator());
        ageSortedStudents.add(johnWick);
        ageSortedStudents.add(sheldonCooper);
        ageSortedStudents.add(harryPotter);
        ageSortedStudents.add(johnCena);

        printValues(ageSortedStudents);
        System.out.println(ageSortedStudents.contains(sheldonCooper));
        System.out.println(ageSortedStudents.contains(new Student("John Wick", 30)));
    }
}
