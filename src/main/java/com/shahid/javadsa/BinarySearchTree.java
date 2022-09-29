package com.shahid.javadsa;

import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class BinarySearchTree<T extends Comparable<T>> {
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
    root = insert(root, value);
  }

  public void remove(T value) {
    root = remove(root, value);
  }

  public boolean contains(T value) {
    return contains(root, value);
  }

  public void print() {
    if (root == null) {
      System.out.println("[]");
      return;
    }

    StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
    print(root, sj);
    System.out.println(sj);
  }

  private Node insert(Node root, T value) {
    if (root == null) {
      return new Node(value);
    } else if (root.value.compareTo(value) > 0) {
      root.left = insert(root.left, value);
      return root;
    } else {
      root.right = insert(root.right, value);
      return root;
    }
  }

  private boolean contains(Node root, T value) {
    if (root == null) return false;
    if (root.value.equals(value)) return true;
    return contains(root.left, value) || contains(root.right, value);
  }

  private void print(Node root, @NotNull StringJoiner sj) {
    if (root == null) return;
    print(root.left, sj);
    sj.add(root.value.toString());
    print(root.right, sj);
  }

  private Node remove(Node root, T value) {
    if (root == null) return null;
    if (root.value.equals(value)) {
      if (root.left == null && root.right == null) {
        return null;
      } else if (root.left != null && root.right == null) {
        return root.left;
      } else if (root.left == null) {
        return root.right;
      } else {
        Node smallestElement = getSmallestElement(root.right);
        root.value = smallestElement.value;
        remove(root.right, root.value);
      }
    }

    if (root.value.compareTo(value) > 0) {
      root.left = remove(root.left, value);
    } else {
      root.right = remove(root.right, value);
    }

    return root;
  }

  private Node getSmallestElement(Node root) {
    if (root == null) return null;
    if (root.left != null) return getSmallestElement(root.left);
    return root;
  }
}
