package com.shahid.dsa;

public class DynamicList<T> {
    private final int capacity;
    private int size;
    private final T[] list;

    DynamicList() {
        this.capacity = 10;
        this.list = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    DynamicList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be greater than 0");
        }
        this.capacity = capacity;
        this.list = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    void print() {
        for (T item : this.list) {
            System.out.println(item);
        }
    }

    void add(T value) {
        this.list[this.size++] = value;
    }
}
