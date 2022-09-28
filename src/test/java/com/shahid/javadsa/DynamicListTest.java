package com.shahid.javadsa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Dynamic List Test")
public class DynamicListTest {


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

  @ParameterizedTest
  @ValueSource(ints = {-1, -10, 0})
  @DisplayName("Should throw error if capacity is less than 1.")
  public void testPositiveCapacity(final int capacity) {
    IllegalArgumentException negativeInitialCapacityException = Assertions.assertThrows(IllegalArgumentException.class, () -> new DynamicList<>(capacity));
    Assertions.assertEquals(negativeInitialCapacityException.getMessage(), "Initial Capacity must be greater than zero.");
  }

  @Test
  @DisplayName("Should print list.")
  public void testIfListPrints() {
    DynamicList<Integer> dynamicList = new DynamicList<>();
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.push(3);
    dynamicList.push(4);
    dynamicList.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4 ]");
  }

  @Test
  @DisplayName("Should increase capacity if list is full.")
  public void testIfArrayGrows() {
    DynamicList<Integer> dynamicList = new DynamicList<>(2);
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.push(3);
    Assertions.assertEquals(dynamicList.getSize(), 3);
    Assertions.assertEquals(dynamicList.getCapacity(), 4);
  }

  @Test
  @DisplayName("Should push the element in the list.")
  public void testIfPushAddsElement() {
    DynamicList<Integer> dynamicList = new DynamicList<>(2);
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.push(3);
    Assertions.assertEquals(dynamicList.getSize(), 3);
    Assertions.assertEquals(dynamicList.getCapacity(), 4);
    dynamicList.pop();
    dynamicList.pop();
    dynamicList.pop();
    dynamicList.push(1);
  }

  @Test
  @DisplayName("Should return the element removed.")
  public void testIfPopReturnsLastElement() {
    DynamicList<Integer> dynamicList = new DynamicList<>(2);
    dynamicList.push(1);
    dynamicList.push(2);
    Assertions.assertEquals(dynamicList.pop(), 2);
    Assertions.assertEquals(dynamicList.pop(), 1);
    Assertions.assertEquals(dynamicList.getSize(), 0);
    Assertions.assertEquals(dynamicList.getCapacity(), 0);
    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, dynamicList::pop);
  }

  @Test
  @DisplayName("Should decrease capacity if list is half empty.")
  public void testIfArrayShrinks() {
    DynamicList<Integer> dynamicList = new DynamicList<>(2);
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.pop();
    Assertions.assertEquals(dynamicList.getSize(), 1);
    Assertions.assertEquals(dynamicList.getCapacity(), 1);
  }

  @Test
  @DisplayName("Should throw error if no element in list to remove.")
  public void testIfExceptionIsThrownOnPop() {
    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>();
      dynamicList.pop();
    });

    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>();
      dynamicList.push(1);
      dynamicList.pop();
      dynamicList.pop();
    });
  }

  @Test
  @DisplayName("Should return correct list size and capacity.")
  public void testListSizeAndCapacity() {
    DynamicList<Integer> dynamicList = new DynamicList<>();
    dynamicList.push(1);
    Assertions.assertEquals(dynamicList.getSize(), 1);
    Assertions.assertEquals(dynamicList.getCapacity(), 10);

    dynamicList.push(2);
    Assertions.assertEquals(dynamicList.getSize(), 2);
    Assertions.assertEquals(dynamicList.getCapacity(), 10);
  }

  @Test
  @DisplayName("Should insert element at correct index")
  public void testInsertionAtIndex() {
    DynamicList<Integer> dynamicList = new DynamicList<>(1);
    dynamicList.push(1);
    dynamicList.push(3);
    dynamicList.insert(0, 0);
    dynamicList.insert(2, 2);
    dynamicList.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 0, 1, 2, 3 ]");
    Assertions.assertEquals(dynamicList.getSize(), 4);
    Assertions.assertEquals(dynamicList.getCapacity(), 4);
  }

  @Test
  @DisplayName("Should throw error on incorrect insertion index")
  public void testIncorrectInsertionIndex() {
    IllegalArgumentException lowIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>(1);
      dynamicList.push(1);
      dynamicList.insert(0, -1);
    });
    Assertions.assertEquals(lowIndexException.getMessage(), "Index must be in the range 0-1");

    IllegalArgumentException highIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>(1);
      dynamicList.push(1);
      dynamicList.insert(0, 2);
    });
    Assertions.assertEquals(highIndexException.getMessage(), "Index must be in the range 0-1");
  }

  @Test
  @DisplayName("Should remove element at correct index")
  public void testRemovalAtIndex() {
    DynamicList<Integer> dynamicList = new DynamicList<>(1);
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.push(3);
    dynamicList.push(5);
    dynamicList.push(4);
    dynamicList.remove(3);
    dynamicList.print();
    Assertions.assertEquals(outContent.toString().trim(), "[ 1, 2, 3, 4 ]");
    Assertions.assertEquals(dynamicList.getSize(), 4);
    Assertions.assertEquals(dynamicList.getCapacity(), 4);
  }

  @Test
  @DisplayName("Should throw error on incorrect removal index")
  public void testIncorrectRemovalIndex() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>(1);
      dynamicList.push(1);
      dynamicList.remove(1);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DynamicList<Integer> dynamicList = new DynamicList<>(1);
      dynamicList.push(1);
      dynamicList.remove(-1);
    });
  }

  @Test
  @DisplayName("Should return element based on index or throw error if index is invalid")
  public void testGetMethod() {
    DynamicList<Integer> dynamicList = new DynamicList<>();
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.push(3);
    dynamicList.push(4);
    Assertions.assertEquals(dynamicList.get(0), 1);
    Assertions.assertEquals(dynamicList.get(1), 2);
    Assertions.assertEquals(dynamicList.get(2), 3);
    dynamicList.remove(2);
    Assertions.assertEquals(dynamicList.get(2), 4);

    IllegalArgumentException lowIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> dynamicList.get(-1));
    Assertions.assertEquals(lowIndexException.getMessage(), "Index must be in the range 0-2");

    IllegalArgumentException highIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> dynamicList.get(-1));
    Assertions.assertEquals(highIndexException.getMessage(), "Index must be in the range 0-2");
  }

  @Test
  @DisplayName("Should set element based on index or throw error if index is invalid")
  public void testSetMethod() {
    DynamicList<Integer> dynamicList = new DynamicList<>();
    dynamicList.push(1);
    dynamicList.push(2);
    dynamicList.set(10, 0);
    dynamicList.set(20, 1);
    Assertions.assertEquals(dynamicList.get(0), 10);
    Assertions.assertEquals(dynamicList.get(1), 20);
    Assertions.assertEquals(dynamicList.getSize(), 2);
    Assertions.assertEquals(dynamicList.getCapacity(), 10);

    IllegalArgumentException lowIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> dynamicList.set(10, 2));
    Assertions.assertEquals(lowIndexException.getMessage(), "Index must be in the range 0-1");

    IllegalArgumentException highIndexException = Assertions.assertThrows(IllegalArgumentException.class, () -> dynamicList.set(10, -1));
    Assertions.assertEquals(highIndexException.getMessage(), "Index must be in the range 0-1");
  }
}