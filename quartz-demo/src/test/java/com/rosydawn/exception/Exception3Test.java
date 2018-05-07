package com.rosydawn.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Created by vincent on 10/9 2017.
 */
public class Exception3Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDivisionWithZero() {
        // test type
        thrown.expect(ArithmeticException.class);
        // if contains some string or not
        thrown.expectMessage("/ by zero");
        int i = 1 / 0;
    }

    @Test
    public void testEmptyList() {
        // test type
        thrown.expect(IndexOutOfBoundsException.class);
        // test given message
        thrown.expectMessage("Index: 0, Size: 0");
        // execution will never get past this line
        new ArrayList<>().get(0);
    }
}
