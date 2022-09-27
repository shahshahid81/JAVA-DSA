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
        linkedList.add(2);
        linkedList.add(1);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2 ]");
    }

    @Test
    @DisplayName("Should push the element in the list.")
    public void testIfAddInsertsElementAtBeginning() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.print();
        Assertions.assertEquals(outContent.toString().trim(), "[ 4, 3, 2, 1 ]");
    }

//    @Test
//    @DisplayName("Should insert element at correct index")
//    public void testInsertionAtIndex() {
//        DynamicList<Integer> dynamicList = new DynamicList<>(1);
//        dynamicList.push(1);
//        dynamicList.push(3);
//        dynamicList.insert(0, 0);
//        dynamicList.insert(2, 2);
//        dynamicList.print();
//        Assertions.assertEquals(outContent.toString().trim(), "[ 0, 1, 2, 3 ]");
//        Assertions.assertEquals(dynamicList.getSize(), 4);
//        Assertions.assertEquals(dynamicList.getCapacity(), 4);
//    }
//
//    @Test
//    @DisplayName("Should throw error on incorrect insertion index")
//    public void testIncorrectInsertionIndex() {
//        IllegalArgumentException lowIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            DynamicList<Integer> dynamicList = new DynamicList<>(1);
//            dynamicList.push(1);
//            dynamicList.insert(0, -1);
//        });
//        Assertions.assertEquals(lowIndexException.getMessage(), "Index must be in the range 0-1");
//
//        IllegalArgumentException highIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            DynamicList<Integer> dynamicList = new DynamicList<>(1);
//            dynamicList.push(1);
//            dynamicList.insert(0, 2);
//        });
//        Assertions.assertEquals(highIndexException.getMessage(), "Index must be in the range 0-1");
//    }
//
//    @Test
//    @DisplayName("Should remove element at correct index")
//    public void testRemovalAtIndex() {
//        DynamicList<Integer> dynamicList = new DynamicList<>(1);
//        dynamicList.push(1);
//        dynamicList.push(2);
//        dynamicList.push(3);
//        dynamicList.push(5);
//        dynamicList.push(4);
//        dynamicList.remove(3);
//        dynamicList.print();
//        Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4 ]");
//        Assertions.assertEquals(dynamicList.getSize(), 4);
//        Assertions.assertEquals(dynamicList.getCapacity(), 4);
//    }
//
//    @Test
//    @DisplayName("Should throw error on incorrect removal index")
//    public void testIncorrectRemovalIndex() {
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            DynamicList<Integer> dynamicList = new DynamicList<>(1);
//            dynamicList.push(1);
//            dynamicList.remove(1);
//        });
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            DynamicList<Integer> dynamicList = new DynamicList<>(1);
//            dynamicList.push(1);
//            dynamicList.remove(-1);
//        });
//    }
}
