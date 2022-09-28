package com.shahid.javadsa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class BinaryTree<T> {
  class Node {
    T value;
    Node left;
    Node right;

    Node(T value) {
      this.value = value;
      this.left = this.right = null;
    }
  }

  Node root;

  public void insert(T value) {
    Node node = new Node(value);
    if (root == null) {
      root = node;
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.left == null) {
        current.left = node;
        queue.clear();
      } else if (current.right == null) {
        current.right = node;
        queue.clear();
      } else if (current.left.left == null) {
        current.left.left = node;
        queue.clear();
      } else if (current.left.right == null) {
        current.left.right = node;
        queue.clear();
      } else if (current.right.left == null) {
        current.right.left = node;
        queue.clear();
      } else if (current.right.right == null) {
        current.right.right = node;
        queue.clear();
      } else {
        queue.offer(current.left);
        queue.offer(current.right);
      }
    }
  }

  public Node remove(T value) {
    if (root == null) return null;

    Node elementToDelete;
    if (root.value.equals(value) && root.left == null && root.right == null) {
      elementToDelete = root;
      root = null;
      return elementToDelete;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    Node lastElement = null;
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.left == null && current.right == null && queue.isEmpty()) lastElement = current;
      if (current.left != null) queue.offer(current.left);
      if (current.right != null) queue.offer(current.right);
    }

    elementToDelete = find(value);
    T temp = elementToDelete.value;
    assert lastElement != null;
    elementToDelete.value = lastElement.value;
    lastElement.value = temp;

    queue.add(root);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.left != null) {
        if (current.left == lastElement) {
          current.left = null;
        } else {
          queue.offer(current.left);
        }
      }
      if (current.right != null) {
        if (current.right == lastElement) {
          current.right = null;
        } else {
          queue.offer(current.right);
        }
      }
    }

    return elementToDelete;
  }

  public Node find(T value) {
    if (root == null) return null;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.value.equals(value)) return current;
      if (current.left != null) queue.offer(current.left);
      if (current.right != null) queue.offer(current.right);
    }
    return null;
  }


  public boolean contains(T value) {
    if (root == null) return false;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.value.equals(value)) return true;
      if (current.left != null) queue.offer(current.left);
      if (current.right != null) queue.offer(current.right);
    }
    return false;
  }

  public void print() {
    if (root == null) {
      System.out.println("[]");
      return;
    }

    StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      sj.add(current.value.toString());
      if (current.left != null) queue.offer(current.left);
      if (current.right != null) queue.offer(current.right);
    }

    System.out.println(sj);
  }

  public static void main(String[] args) {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}) {
      binaryTree.insert(i);
    }
    binaryTree.remove(1);
    binaryTree.remove(4);
    binaryTree.remove(11);
    binaryTree.print();
  }
}
