package com.shahid.javadsa;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
  List<T> heap;

  MinHeap() {
    heap = new ArrayList<>();
  }

  MinHeap(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Initial Capacity must be greater than zero.");
    heap = new ArrayList<>(capacity);
  }

  public void add(T value) {
    if (heap.contains(value)) return;
    heap.add(value);
    bubbleUp(heap.lastIndexOf(value));
  }

  public boolean contains(T value) {
    return heap.contains(value);
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public T getMin() {
    if (isEmpty()) return null;
    T value = heap.get(0);
    bubbleDown(0);
    return value;
  }

  private void bubbleUp(int index) {
    while (index > 0) {
      int parentIndex = getParentIndex(index);
      T parent = heap.get(parentIndex);
      T child = heap.get(index);
      if (parent.compareTo(child) < 0) {
        break;
      }
      swap(parentIndex, index);
      index = parentIndex;
    }
  }

  private void bubbleDown(int index) {
    while (index < heap.size()) {
      int leftChildIndex = getLeftChildIndex(index);
      int rightChildIndex = getRightChildIndex(index);
      if (leftChildIndex >= heap.size()) {
        heap.remove(index);
        break;
      }
      if (rightChildIndex >= heap.size()) {
        swap(index, leftChildIndex);
        heap.remove(leftChildIndex);
        break;
      }
      T leftChild = heap.get(leftChildIndex);
      T rightChild = heap.get(rightChildIndex);
      if (leftChild.compareTo(rightChild) < 0) {
        swap(index, leftChildIndex);
        index = leftChildIndex;
      } else {
        swap(index, rightChildIndex);
        index = rightChildIndex;
      }
    }
  }

  private int getLeftChildIndex(int index) {
    return (index * 2) + 1;
  }

  private int getRightChildIndex(int index) {
    return (index * 2) + 2;
  }

  private int getParentIndex(int index) {
    return Math.floorDiv(index % 2 == 0 ? index - 1 : index, 2);
  }

  private void swap(int index1, int index2) {
    T firstChild = heap.get(index1);
    T secondChild = heap.get(index2);
    heap.set(index1, secondChild);
    heap.set(index2, firstChild);
  }
}
