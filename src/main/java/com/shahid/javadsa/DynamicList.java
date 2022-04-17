package com.shahid.javadsa;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class DynamicList<T> {
    private T[] list;
    private int back = -1;

    DynamicList() {
        this.list = (T[]) new Object[10];
    }

    DynamicList(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Initial Capacity must be greater than zero.");
        }

        this.list = (T[])new Object[capacity];
    }

    public void print() {
        StringJoiner sj = new StringJoiner(", ","[ "," ]");
        Arrays.stream(this.list).filter(Objects::nonNull).map(String::valueOf).forEach(sj::add);
        System.out.println(sj.toString());
    }

    public void push(T element) {
        this.resizeArrayIfSizeEquals(this.getCapacity(), this.getCapacity() * 2);
        this.list[++this.back] = element;
    }

    public T pop() {
        if(this.getSize() == 0) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        T element = this.list[this.back--];
        this.resizeArrayIfSizeEquals(this.getCapacity() / 2, this.getCapacity() / 2);
        return element;
    }

    public int getSize() {
        return this.back + 1;
    }

    public int getCapacity() {
        return this.list.length;
    }

    private void resizeArrayIfSizeEquals(int capacityToCheck, int newCapacity) {
        if(this.getSize() == capacityToCheck) {
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(this.list, 0, temp, 0, capacityToCheck);
            this.list = temp;
        }
    }
}