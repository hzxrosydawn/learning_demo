package com.rosydawn.fixture;

/**
 * Created by vincent on 2017-10-15.
 */

import java.io.Closeable;
        import java.io.IOException;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

public class TestFixtures {
    static class ManagedResource implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }

    private ManagedResource managedResource;


    @Before
    public void setUp() {
        System.out.println("@Before setUp");
        this.managedResource = new ManagedResource();
    }

    @After
    public void tearDown() throws IOException {
        System.out.println("@After tearDown");
        this.managedResource.close();
        this.managedResource = null;
    }

    @Test
    public void test1() {
        System.out.println("@Test test1()");
    }

    @Test
    public void test2() {
        System.out.println("@Test test2()");
    }
}