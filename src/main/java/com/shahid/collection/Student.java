package com.shahid.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparable<Student> {

    static class StudentAgeComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            if (o1.age > o2.age) {
                return 1;
            } else if (o1.age < o2.age) {
                return -1;
            }
            return o1.name.compareTo(o2.name);
        }
    }

    static class StudentNameComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            if (o1.name.equals(o2.name)) {
                return new StudentAgeComparator().compare(o1, o2);
            }
            return o1.name.compareTo(o2.name);
        }
    }

    int age;
    String name;


    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull Student other) {
        if (this.age == other.age) {
            return this.name.compareTo(other.name);
        } else if (this.age > other.age) {
            return 1;
        }
        return -1;

    }
}
