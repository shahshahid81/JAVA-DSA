package com.shahid.dsa;

import jdk.jfr.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class DynamicListTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, 0})
    @Description("Should throw error if initial capacity is not positive.")
    public void checkPositiveCapacity(int capacity) {
        assertThrows(IllegalArgumentException.class, () -> {
            DynamicList<Integer> dynamicList = new DynamicList<>(capacity);
        });
    }
}