/*
Create a class named TestCheckPresence to test the functionality of 
checkPresence method [hint: use assertTrue and assertFalse methods]
*/

package com.wipro.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.wipro.task.DailyTasks;

public class TestCheckPresence {

    @Test
    public void testCheckPresence() {
        DailyTasks tasks = new DailyTasks();
        
        String str = "JUnit Framework";
        assertTrue(tasks.checkPresence(str, "Unit"));
        assertFalse(tasks.checkPresence(str, "Python"));
    }
}