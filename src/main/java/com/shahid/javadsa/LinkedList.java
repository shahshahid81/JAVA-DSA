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

    public class LinkedListNode {
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
            return;
        }

        if (index == this.size) {
            this.addLast(value);
            return;
        }

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

    public LinkedListNode removeFirst() {
        if (head == null) {
            return null;
        }

        if (head == tail) {
            head = tail = null;
            this.size--;
            return null;
        }

        LinkedListNode current = head;
        head = head.next;
        this.size--;
        return current;
    }

    public LinkedListNode removeLast() {
        if (head == null) {
            return null;
        }

        if (head == tail) {
            head = tail = null;
            this.size--;
            return null;
        }


        LinkedListNode elementToRemove = tail;
        LinkedListNode current = head;
        while (current.next != tail) current = current.next;
        tail = current;
        tail.next = null;
        this.size--;
        return elementToRemove;
    }

    public LinkedListNode remove(T value) {
        if (head == null || tail == null) return null;

        if (head.value.equals(value)) {
            return this.removeFirst();
        } else if (tail.value.equals(value)) {
            return this.removeLast();
        }

        LinkedListNode current = head;
        while (current.next != null && !current.next.value.equals(value)) current = current.next;
        LinkedListNode elementToRemove = current.next;
        if (elementToRemove != null) {
            current.next = elementToRemove.next;
            this.size--;
        }

        return elementToRemove;
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

