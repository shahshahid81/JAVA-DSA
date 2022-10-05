package com.shahid.collection;

import java.util.ArrayList;

public class ArrayListDemo extends ListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(4);

        printValues(arrayList);

        arrayList.add(2, 3);
        printValues(arrayList);

        arrayList.remove(Integer.valueOf(1));
        printValues(arrayList);

        arrayList.remove(1);
        printValues(arrayList);

        arrayList.add(2);
        printValues(arrayList);
        System.out.println("Size of list is " + arrayList.size());
        System.out.println("Index of 4 is " + arrayList.indexOf(4));
        System.out.println("Index of 2 is " + arrayList.indexOf(2));
        System.out.println("Last index of 2 is " + arrayList.lastIndexOf(2));
        System.out.println("List has 2: " + arrayList.contains(2));
        System.out.println("List has 3: " + arrayList.contains(3));
        arrayList.sort(Integer::compareTo);
        printValues(arrayList);

        ArrayList<Student> students = new ArrayList<>();
        students.add(johnWick);
        students.add(johnCena);
        students.add(sheldonCooper);
        students.add(harryPotter);
        printValues(students);

        students.sort(new Student.StudentNameComparator());
        printValues(students);

        students.sort(new Student.StudentAgeComparator());
        printValues(students);
//        Collections.sort(students, new Student.StudentAgeComparator());
        printValues(students);
        Student[] studentsArray = students.toArray(new Student[0]);
        for(Student student: studentsArray) System.out.println(student);
    }
}
