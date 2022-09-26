package com.shahid.javadsa;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class DynamicList<T> {
    private T[] list;
    private int lastIndex = -1;

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
        System.out.println(sj);
    }

    public void push(T element) {
        this.growArrayIfFull();
        this.list[++this.lastIndex] = element;
    }

    public T pop() {
        if(this.getSize() == 0) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        T element = this.list[this.lastIndex--];
        this.shrinkArrayIfHalf();
        return element;
    }

    public T get(int index) {
        this.checkIfIndexIsValid(index, 0, this.lastIndex);
        return this.list[index];
    }

    public void set(T value, int index) {
        this.checkIfIndexIsValid(index, 0, this.lastIndex);
        this.list[index] = value;
    }

    public void insert(T value, int index) {
        this.checkIfIndexIsValid(index,0,this.lastIndex + 1);
        this.growArrayIfFull();
        for(int i = this.lastIndex; i >= index ;i--) {
            this.list[i + 1] = this.list[i];
        }
        this.list[index] = value;
        this.lastIndex++;
    }

    public T remove(int index) {
        this.checkIfIndexIsValid(index,0,this.lastIndex);
        T element = this.list[index];
        for(int i = index; i <= this.lastIndex ;i++) {
            this.list[i] = this.list[i + 1];
        }
        this.lastIndex--;
        this.shrinkArrayIfHalf();
        return element;
    }

    public int getSize() {
        return this.lastIndex + 1;
    }

    public int getCapacity() {
        return this.list.length;
    }

    private void checkIfIndexIsValid(int index, int start, int end) {
        if(index < start || index > end) {
            throw new IllegalArgumentException(String.format("Index must be in the range %s-%s",start, end));
        }
    }

    private void growArrayIfFull() {
        this.resizeArrayIfSizeEquals(this.getCapacity(), this.getCapacity() == 0 ? 1 : this.getCapacity() * 2);
    }

    private void shrinkArrayIfHalf() {
        this.resizeArrayIfSizeEquals(this.getCapacity() / 2, this.getCapacity() / 2);
    }

    private void resizeArrayIfSizeEquals(int capacityToCheck, int newCapacity) {
        if(this.getSize() == capacityToCheck) {
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(this.list, 0, temp, 0, capacityToCheck);
            this.list = temp;
        }
    }
}