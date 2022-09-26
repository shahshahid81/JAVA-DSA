package com.shahid.collection;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo extends QueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        printValues(priorityQueue);

        priorityQueue.remove(3);
        printValues(priorityQueue);

        System.out.println("Head of queue: " + priorityQueue.peek());
        printValues(priorityQueue);

        PriorityQueue<Student> sortedAgeStudents = new PriorityQueue<>(new Student.StudentAgeComparator());
        sortedAgeStudents.add(johnWick);
        sortedAgeStudents.add(sheldonCooper);
        sortedAgeStudents.add(harryPotter);
        sortedAgeStudents.add(johnCena);

        printValues(sortedAgeStudents);

        while(!sortedAgeStudents.isEmpty()) {
            System.out.println(sortedAgeStudents.poll());
        }

        System.out.println(priorityQueue.comparator());
        System.out.println(sortedAgeStudents.comparator());
    }
}
