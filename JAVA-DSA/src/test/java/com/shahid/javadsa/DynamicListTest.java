package com.shahid.javadsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Dynamic List Test")
public class DynamicListTest {

    @DisplayName("Should throw error if capacity is less than 1.")
    @ParameterizedTest
    @ValueSource(ints = {-1,-10,0})
    public void testPositiveCapacity(final int capacity) {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            DynamicList<Integer> dynamicList = new DynamicList<>(capacity);
        });
    }
}