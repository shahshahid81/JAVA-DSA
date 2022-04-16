package com.shahid.javadsa;

public class DynamicList<T> {
    private T[] list;
    private int capacity;
    private int size;

    DynamicList() {
        this.capacity = 10;
        this.list = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    DynamicList(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Initial Capacity must be greater than zero.");
        }

        this.capacity = capacity;
        this.list = (T[])new Object[this.capacity];
        this.size = 0;
    }
}