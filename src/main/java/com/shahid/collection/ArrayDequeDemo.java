package com.shahid.collection;

import java.util.ArrayDeque;

public class ArrayDequeDemo extends QueueDemo {
    public static void main(String[] args) {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        arrayDeque.add('h');
        arrayDeque.add('e');
        arrayDeque.add('l');
        arrayDeque.add('l');
        arrayDeque.addLast('o');
        printValues(arrayDeque);

        System.out.println("Queue contains e: " + arrayDeque.contains('e'));
        System.out.println("Queue contains w: " + arrayDeque.contains('w'));
        System.out.println(
                arrayDeque.stream()
                        .filter(character -> character == 'l')
                        .count()
        );

        System.out.println("First char: "+ arrayDeque.peekFirst());
        System.out.println("Last char: "+ arrayDeque.peekLast());
        System.out.println("Inserted char ' ': "+ arrayDeque.offer(' '));
        System.out.println("Inserted char ' ': "+ arrayDeque.offerFirst(' '));
        printValues(arrayDeque);
    }
}
