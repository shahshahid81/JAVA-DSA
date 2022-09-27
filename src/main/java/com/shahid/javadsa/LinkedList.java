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
    private LinkedListNode head;

    @Nullable
    private LinkedListNode tail;

    private int size = 0;

    public void addFirst(T value) {
        LinkedListNode node = new LinkedListNode(value);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        this.size++;
    }

    public void addLast(T value) {
        LinkedListNode node = new LinkedListNode(value);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        this.size++;
    }

    public void add(T value, int index) {
        if (index == 0) {
            this.addFirst(value);
        } else if (index == this.size) {
            this.addLast(value);
        } else {
            LinkedListNode node = new LinkedListNode(value);
            LinkedListNode current = head;
            int insertionIndex = 0;
            while (insertionIndex++ != index - 1) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
            this.size++;
        }
    }

    public boolean contains(T value) {
        for (T t : this) {
            if (t.equals(value)) return true;
        }
        return false;
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

