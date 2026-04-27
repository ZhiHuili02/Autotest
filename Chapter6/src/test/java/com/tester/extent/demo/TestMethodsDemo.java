package com.tester.extent.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodsDemo {
    @Test
    public void test1() {
        Assert.assertEquals(1,  1);
    }

    @Test
    public void test2() {
        Assert.assertEquals( 1,  2);
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void logDemo() {
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是我们自己抛出的异常");
    }
}
