package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    //最基本的注解,用来把方法标记为测试的一部分
    @Test
    public void testCase1() {
        System.out.println("这是测试用例一");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }

    @Test
    public void testCase2() {//单独runtest2,方法之前之后依然运行,方法一不运行
        System.out.println("这是测试用例二");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }

    @BeforeMethod//静态对象赋值等等
    public void beforeMethod() {
        System.out.println("BeforeMethod这是在测试方法之前运行的");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod这是在方法之后运行的");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass这是在类运行之前的方法");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass这是在类运行之后的方法");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite测试套件");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
    @AfterSuite//suit测试套件在前后各一处,也就是说,好几个类,但只有一个suit
    public void afterSuite() {
        System.out.println("afterSuite测试套件");
        System.out.printf("id:%s%n",
                Thread.currentThread().getId());
    }
}
