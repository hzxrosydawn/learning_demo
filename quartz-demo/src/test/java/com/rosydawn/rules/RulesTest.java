package com.rosydawn.rules;

/**
 * Created by vincent on 2017-10-15.
 */

import java.io.Closeable;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

public class RulesTest {
    @Rule
    public ExternalResource resource = new ExpensiveExternalResource();
    private ManagedResource myManagedResource;
    private static ExpensiveManagedResource MyExpensiveManagedResource;

    static class ExpensiveExternalResource extends ExternalResource {
        ExpensiveExternalResource() {
            System.out.println("ExpensiveExternalResource constructor");
        }

        @Override
        protected void before() throws Throwable {
            System.out.println("ExpensiveExternalResource before");
        }

        @Override
        protected void after() {
            System.out.println("ExpensiveExternalResource after");
        }
    }

    static class ExpensiveManagedResource implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }

    static class ManagedResource implements Closeable {
        @Override
        public void close() throws IOException {
        }
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("RulesTest @BeforeClass setUpClass");
        MyExpensiveManagedResource = new ExpensiveManagedResource();
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        System.out.println("RulesTest @AfterClass tearDownClass");
        MyExpensiveManagedResource.close();
        MyExpensiveManagedResource = null;
    }

    @Before
    public void setUp() {
        System.out.println("RulesTest @Before setUp");
        this.myManagedResource = new ManagedResource();
    }

    @After
    public void tearDown() throws IOException {
        System.out.println("RulesTest @After tearDown()");
        this.myManagedResource.close();
        this.myManagedResource = null;
    }

    @Test
    public void test1() {
        System.out.println("RulesTest @Test test1()");
    }

    @Test
    public void test2() {
        System.out.println("RulesTest @Test test2()");
    }
}
