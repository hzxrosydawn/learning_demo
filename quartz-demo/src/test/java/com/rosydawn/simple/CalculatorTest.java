package com.rosydawn.simple;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(20, 20);
        assertEquals(40, result, 0);
    }
}
