package com.shahid.javadsa;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedList<T> implements Iterable<T> {

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(this.head);
    }

    private class LinkedListIterator implements Iterator<T> {
        @Nullable LinkedListNode head;

        LinkedListIterator(@Nullable LinkedListNode head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T current = null;
            if (head != null) {
                current = head.value;
                head = head.next;
            }
            return current;
        }
    }

    private class LinkedListNode {
        T value;
        @Nullable LinkedListNode next = null;

        LinkedListNode(T value) {
            this.value = value;
        }
    }

    @Nullable
    private LinkedListNode head = null;

    public void add(T value) {
        LinkedListNode node = new LinkedListNode(value);
        node.next = head;
        head = node;
    }

    public void print() {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
        for (T t : this) {
            sj.add(t.toString());
        }

        System.out.println(sj);
    }
}

