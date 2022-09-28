package com.shahid.javadsa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Min Heap Test")
public class MinHeapTest {

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
  @DisplayName("Should add the element in the heap.")
  public void testIfAddAddsElement() {
    MinHeap<Integer> minHeap = new MinHeap<>();
    minHeap.add(3);
    Assertions.assertFalse(minHeap.isEmpty());
    Assertions.assertEquals(minHeap.getMin(), 3);
  }

  @Test
  @DisplayName("Should fetch the smallest element in the heap.")
  public void testIfMinHeapFetchesSmallestElement() {
    MinHeap<Integer> minHeap = new MinHeap<>();
    minHeap.add(3);
    minHeap.add(2);
    minHeap.add(1);
    minHeap.add(10);
    minHeap.add(5);
    minHeap.add(7);
    minHeap.add(4);
    Assertions.assertEquals(minHeap.getMin(), 1);
    Assertions.assertEquals(minHeap.getMin(), 2);
    Assertions.assertEquals(minHeap.getMin(), 3);
    Assertions.assertEquals(minHeap.getMin(), 4);
    Assertions.assertEquals(minHeap.getMin(), 5);
    Assertions.assertEquals(minHeap.getMin(), 7);
    Assertions.assertEquals(minHeap.getMin(), 10);
  }
}
