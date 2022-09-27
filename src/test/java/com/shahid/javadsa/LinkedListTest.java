package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Linked List Test")
public class LinkedListTest<I extends Number> {


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
    @DisplayName("Should print list.")
    public void testIfListPrints() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2 ]");
    }

    @Test
    @DisplayName("Should add element at beginning of the list.")
    public void testIfAddInsertsElementAtBeginning() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 4, 3, 2, 1 ]");
    }

    @Test
    @DisplayName("Should add element at specified index of the list.")
    public void testIfAddInsertsElementAtIndex() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(2);
        linkedList.addLast(4);
        linkedList.add(1, 0);
        linkedList.add(3, 2);
        linkedList.add(5, 4);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4, 5 ]");
    }

    @Test
    @DisplayName("Should add element at end of the list.")
    public void testIfAddInsertsElementAtEnd() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4 ]");
    }

    @Test
    @DisplayName("Should remove element at beginning of the list.")
    public void testIfAddRemovesElementAtBeginning() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        LinkedList<Integer>.LinkedListNode first = linkedList.removeFirst();
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 2, 1 ]");
        Assertions.assertEquals(first.value, 3);
    }

    @Test
    @DisplayName("Should remove element at end of the list.")
    public void testIfAddRemovesElementAtEnd() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        LinkedList<Integer>.LinkedListNode last = linkedList.removeLast();
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 3, 2 ]");
        Assertions.assertEquals(last.value, 1);
    }

    @Test
    @DisplayName("Should remove element at index.")
    public void testIfAddRemovesElementAtIndex() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.add(8, 1);
        LinkedList<Integer>.LinkedListNode first = linkedList.remove(1);
        LinkedList<Integer>.LinkedListNode last = linkedList.remove(3);
        LinkedList<Integer>.LinkedListNode empty = linkedList.remove(5);
        LinkedList<Integer>.LinkedListNode second = linkedList.remove(2);
        linkedList.print();
        Assertions.assertNotNull(first);
        Assertions.assertEquals(first.value, 1);
        Assertions.assertNotNull(second);
        Assertions.assertEquals(second.value, 2);
        Assertions.assertNotNull(last);
        Assertions.assertEquals(last.value, 3);
        Assertions.assertNull(empty);
        Assertions.assertEquals(outContent.toString().trim(), "[ 8 ]");

    }

    @Test
    @DisplayName("Should return true if element is found else false.")
    public void testSearch() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1);
        Assertions.assertTrue(linkedList.contains(1));
        Assertions.assertFalse(linkedList.contains(5));
    }
}
