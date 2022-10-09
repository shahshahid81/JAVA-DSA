package com.shahid.javadsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisjointSet<T> {
  final List<T> nodes;
  private final List<Integer> parent;


  public DisjointSet(ArrayList<T> nodes) {
    if (nodes == null) throw new IllegalArgumentException("Node list is required");
    this.nodes = Collections.unmodifiableList(nodes);
    this.parent = new ArrayList<>(Collections.nCopies(this.nodes.size(), -1));
  }


  public void union(T first, T second) {
    T firstElement = find(first);
    if (firstElement == null) return;

    T secondElement = find(second);
    if (secondElement == null) return;

    if (firstElement == secondElement) return;
    int firstElementIndex = nodes.indexOf(first);
    int secondElementIndex = nodes.indexOf(second);
    parent.set(secondElementIndex, firstElementIndex);
  }

  public T find(T element) {
    int elementIndex = nodes.indexOf(element);
    if (elementIndex == -1) return null;
    while (parent.get(elementIndex) != -1) elementIndex = parent.get(elementIndex);
    return nodes.get(elementIndex);
  }
}
