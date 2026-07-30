/*
Create a Project named JunitLearning 
1. Create a package named com.wipro.task
2. Copy the given class into the com.wipro.task package
3. Create a new package called com.wipro.test;
4. Create a class named TestStringConcat to test the functionality of doStringConcat method [hint: use assertEquals method]
*/

package com.wipro.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.wipro.task.DailyTasks;

public class TestStringConcat {

    @Test
    public void testDoStringConcat() {
        DailyTasks tasks = new DailyTasks();
        String result = tasks.doStringConcat("Hello","World");
        assertEquals("Hello World", result);
    }
}