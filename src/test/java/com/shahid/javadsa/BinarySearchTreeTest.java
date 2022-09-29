package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Binary Search Tree Test")
public class BinarySearchTreeTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  @DisplayName("Should add the element in the tree.")
  public void testIfInsertAddsElement() {
    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
    for (int i : new int[]{8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 6, 7}) {
      binarySearchTree.insert(i);
    }
    binarySearchTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ]");
  }

  @Test
  @DisplayName("Should remove the element in the tree.")
  public void testIfRemoveRemovesElement() {
    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
    binarySearchTree.insert(1);
    binarySearchTree.remove(1);
    binarySearchTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[]");
    outContent.reset();

    for (int i : new int[]{8, 9, 10, 3, 4, 5, 11, 12, 13, 1, 2, 6, 7}) {
      binarySearchTree.insert(i);
    }
    binarySearchTree.remove(1);
    binarySearchTree.remove(4);
    binarySearchTree.remove(11);
    binarySearchTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 2, 3, 5, 6, 7, 8, 9, 10, 12, 13 ]");
  }

  @Test
  @DisplayName("Should return true if element is found else false.")
  public void testSearch() {
    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
    binarySearchTree.insert(1);
    binarySearchTree.insert(2);
    binarySearchTree.insert(3);
    Assertions.assertTrue(binarySearchTree.contains(1));
    Assertions.assertTrue(binarySearchTree.contains(2));
    Assertions.assertTrue(binarySearchTree.contains(3));
    Assertions.assertFalse(binarySearchTree.contains(5));
  }
}
