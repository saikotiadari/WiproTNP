/*
Create a test suite for all the 3 classes created under the 
com.wipro.test package and execute the same
*/

package com.wipro.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestStringConcat.class,
    TestSort.class,
    TestCheckPresence.class
})
public class AllTests {
  
}