package com.rosydawn.suite;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCaseA {
    @Test
    public void testA1() {
        System.out.println("TestCaseA");
        assertEquals("Dummy test-case", 1+1, 2);
    }
}
