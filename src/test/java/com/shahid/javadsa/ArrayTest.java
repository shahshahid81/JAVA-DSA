package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Array Test")
public class ArrayTest {


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
    @DisplayName("Should return array without duplicates.")
    public void testIfWithoutDuplicates() {
        Assertions.assertArrayEquals(Array.withoutDuplicates(new int[]{1, 2, 3, 3, 2, 1, 5, 4, 3, 1}), new int[]{1, 2, 3, 4, 5});
    }

    @Test
    @DisplayName("Should return pascal triangle of size 5.")
    public void testIfPascalTriangle() {
        int[][] expectedResult = {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}};
        int[][] actualResult = Array.pascalTriangle(5);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Should print factor.")
    public void testIfFactor() {
        Assertions.assertEquals(Array.factor(100), "2*2*5*5");
        Assertions.assertEquals(Array.factor(13), "13");
    }

    @Test
    @DisplayName("Should check if array is sorted.")
    public void testIfSorted() {
        Assertions.assertEquals(Array.isSorted(new int[]{1, 2, 3}), true);
        Assertions.assertEquals(Array.isSorted(new int[]{1, 2, 3, 2}), false);
    }
}