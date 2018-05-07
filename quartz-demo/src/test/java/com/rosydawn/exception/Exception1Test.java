package com.rosydawn.exception;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by vincent on 10/5 2017.
 */
public class Exception1Test {
    @Test(expected = ArithmeticException.class)
    public void testDivisionWithZero() {
        int i = 1 / 0;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyList() {
        new ArrayList<>().get(0);
    }
}
