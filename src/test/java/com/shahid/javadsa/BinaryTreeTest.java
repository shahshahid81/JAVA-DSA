package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Binary Tree Test")
public class BinaryTreeTest {
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
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}) {
      binaryTree.insert(i);
    }
    binaryTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ]");
  }

  @Test
  @DisplayName("Should remove the element in the tree.")
  public void testIfRemoveRemovesElement() {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    binaryTree.insert(1);
    binaryTree.remove(1);
    binaryTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[]");
    outContent.reset();

    for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}) {
      binaryTree.insert(i);
    }
    binaryTree.remove(1);
    binaryTree.remove(4);
    binaryTree.remove(11);
    binaryTree.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 13, 2, 3, 12, 5, 6, 7, 8, 9, 10 ]");
  }

  @Test
  @DisplayName("Should return true if element is found else false.")
  public void testSearch() {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    binaryTree.insert(1);
    binaryTree.insert(2);
    binaryTree.insert(3);
    Assertions.assertTrue(binaryTree.contains(1));
    Assertions.assertTrue(binaryTree.contains(2));
    Assertions.assertTrue(binaryTree.contains(3));
    Assertions.assertFalse(binaryTree.contains(5));
  }
}
