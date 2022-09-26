package com.shahid.collection;

import java.util.Collections;
import java.util.LinkedList;

public class LinkedListDemo extends ListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(4);

        printValues(linkedList);

        linkedList.add(2, 3);
        printValues(linkedList);

        linkedList.remove(new Integer(1));
        printValues(linkedList);

        linkedList.remove(1);
        printValues(linkedList);

        linkedList.add(2);
        printValues(linkedList);
        System.out.println("Size of list is " + linkedList.size());
        System.out.println("Index of 4 is " + linkedList.indexOf(4));
        System.out.println("Index of 2 is " + linkedList.indexOf(2));
        System.out.println("Last index of 2 is " + linkedList.lastIndexOf(2));
        System.out.println("List has 2: " + linkedList.contains(2));
        System.out.println("List has 3: " + linkedList.contains(3));
        linkedList.sort((number1, number2) -> number1 == number2 ? 0 : number1 > number2 ? 1 : -1);
        printValues(linkedList);

        LinkedList<Student> students = new LinkedList<>();
        students.add(johnWick);
        students.add(johnCena);
        students.add(sheldonCooper);
        students.add(harryPotter);
        printValues(students);

        students.sort(new Student.StudentNameComparator());
        printValues(students);

        students.sort(new Student.StudentAgeComparator());
        printValues(students);
        Collections.sort(students, new Student.StudentAgeComparator());
        printValues(students);
        Student[] studentsArray = students.toArray(new Student[students.size()]);
        for(Student student: studentsArray) {
            System.out.println(student);
        }
    }
}
