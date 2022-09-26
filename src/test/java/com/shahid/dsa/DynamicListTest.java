package com.shahid.dsa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class DynamicListTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, 0})
    @DisplayName("Should throw error if initial capacity is not positive.")
    public void checkPositiveCapacity(int capacity) {
        assertThrows(IllegalArgumentException.class, () -> {
            DynamicList<Integer> dynamicList = new DynamicList<>(capacity);
        });
    }
}