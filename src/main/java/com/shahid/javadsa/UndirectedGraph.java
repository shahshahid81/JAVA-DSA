package com.shahid.javadsa;

import java.util.LinkedList;
import java.util.*;

public class UndirectedGraph<T extends Comparable<T>> {

  class Node {
    T value;
    List<Node> list;

    Node(T value) {
      this.value = value;
      this.list = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof UndirectedGraph<?>.Node)) return false;
      UndirectedGraph<?>.Node node = (UndirectedGraph<?>.Node) o;
      return value.equals(node.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    @Override
    public String toString() {
      return value.toString();
    }
  }

  List<Node> vertices;

  UndirectedGraph() {
    vertices = new ArrayList<>();
  }

  public void insertEdge(T u, T v) {
    Node uNode = insertVertex(u);
    Node vNode = insertVertex(v);

    if (!uNode.list.contains(vNode)) {
      uNode.list.add(vNode);
    }

    if (!vNode.list.contains(uNode)) {
      vNode.list.add(uNode);
    }
  }

  public void removeEdge(T u, T v) {
    Node uNode = findVertex(u);
    if (uNode == null) return;
    Node vNode = findVertex(v);
    if (vNode == null) return;

    uNode.list.remove(vNode);
    vNode.list.remove(uNode);
  }

  public Node insertVertex(T value) {
    Node node = findVertex(value);
    if (node == null) {
      node = new Node(value);
      vertices.add(node);
    }
    return node;
  }

  public void removeVertex(T value) {
    Node node = findVertex(value);
    if (node == null) return;
    for (Node element : node.list) {
      element.list.remove(node);
    }
    vertices.remove(node);
  }

  public Node findVertex(T value) {
    return vertices.stream().filter(vertex -> vertex.value.equals(value)).findFirst().orElse(null);
  }

  public void print() {
    for (Node vertex : vertices) {
      System.out.print("\n" + vertex);
      for (Node element : vertex.list) {
        System.out.print(" -> " + element);
      }
    }
  }

  public void bfs() {
    if (vertices.isEmpty()) return;

    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();

    for (Node vertex : vertices) {
      if (!visited.contains(vertex)) {
        queue.offer(vertex);
        visited.add(vertex);
      }

      if (!queue.isEmpty()) {
        Node current = queue.poll();
        System.out.print(current + "\t");
        for (Node element : current.list) {
          if (!visited.contains(element)) {
            queue.offer(element);
            visited.add(element);
          }
        }
      }
    }
  }

}
