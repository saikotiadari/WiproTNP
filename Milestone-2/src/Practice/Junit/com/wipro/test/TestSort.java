package com.wipro.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.wipro.task.DailyTasks;

public class TestSort {

    @Test
    public void testSortValues() {

        DailyTasks tasks = new DailyTasks();

        int[] input = {5, 2, 8, 1, 3};
        int[] expected = {1, 2, 3, 5, 8};

        int[] result = tasks.sortValues(input);

        assertArrayEquals(expected, result);
    }
}