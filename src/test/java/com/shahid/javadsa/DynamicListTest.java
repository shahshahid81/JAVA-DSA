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
    @ValueSource(ints = {-1,-10,0})
    @DisplayName("Should throw error if capacity is less than 1.")
    public void testPositiveCapacity(final int capacity) {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            DynamicList<Integer> dynamicList = new DynamicList<>(capacity);
        });
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
    @DisplayName("Should return the element removed.")
    public void testIfPopReturnsLastElement() {
        DynamicList<Integer> dynamicList = new DynamicList<>(2);
        dynamicList.push(1);
        dynamicList.push(2);
        Assertions.assertEquals(dynamicList.pop(), 2);
        Assertions.assertEquals(dynamicList.pop(), 1);
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
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {
            DynamicList<Integer> dynamicList = new DynamicList<>();
            dynamicList.pop();
        });

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {
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
        Assertions.assertEquals(dynamicList.getSize(),1);
        Assertions.assertEquals(dynamicList.getCapacity(),10);

        dynamicList.push(2);
        Assertions.assertEquals(dynamicList.getSize(),2);
        Assertions.assertEquals(dynamicList.getCapacity(),10);
    }
}