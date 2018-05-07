package com.rosydawn.ignore;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by vincent on 2017-10-09.
 */
public class IgnoreTest {
    @Test
    public void testMath1() {
        assertThat(1 + 1, is(2));
    }

    @Ignore
    @Test
    public void testMath2() {
        assertThat(1 + 2, is(3));
    }

    @Ignore("some one please create a test for Math3!")
    @Test
    public void testMath3() {
        assertThat(1 + 3, is(3));
    }
}
