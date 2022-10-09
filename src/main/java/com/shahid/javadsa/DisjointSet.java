package com.shahid.javadsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisjointSet<T> {
  final List<T> nodes;
  private final List<Integer> parent;
  private final List<Integer> rank;


  public DisjointSet(ArrayList<T> nodes) {
    if (nodes == null) throw new IllegalArgumentException("Node list is required");
    this.nodes = Collections.unmodifiableList(nodes);
    this.parent = new ArrayList<>(Collections.nCopies(this.nodes.size(), -1));
    this.rank = new ArrayList<>(Collections.nCopies(this.nodes.size(), 1));
  }


  public void union(T first, T second) {
    T firstElementParent = find(first);
    if (firstElementParent == null) return;

    T secondElementParent = find(second);
    if (secondElementParent == null) return;

    if (firstElementParent == secondElementParent) return;

    int firstElementIndex = nodes.indexOf(first);
    int firstElementParentIndex = nodes.indexOf(firstElementParent);
    int firstElementRank = rank.get(firstElementParentIndex);

    int secondElementIndex = nodes.indexOf(second);
    int secondElementParentIndex = nodes.indexOf(secondElementParent);
    int secondElementRank = rank.get(secondElementParentIndex);

    if (firstElementRank >= secondElementRank) {
      parent.set(secondElementIndex, firstElementIndex);
      rank.set(firstElementIndex, rank.get(firstElementIndex) + rank.get(firstElementIndex));
    } else {
      parent.set(firstElementIndex, secondElementIndex);
      rank.set(secondElementIndex, rank.get(secondElementIndex) + rank.get(firstElementIndex));
    }
  }

  public T find(T element) {
    int elementIndex = nodes.indexOf(element);
    if (elementIndex == -1) return null;
    while (parent.get(elementIndex) != -1) elementIndex = parent.get(elementIndex);
    return nodes.get(elementIndex);
  }
}
