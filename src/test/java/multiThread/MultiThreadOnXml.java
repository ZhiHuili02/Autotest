package multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
        public void test1() throws InterruptedException {
        Thread.sleep(3000);
        System.out.printf("Thread Id:%s%n",
                Thread.currentThread().getId());}

    @Test
        public void test2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.printf("Thread Id:%s%n",
                Thread.currentThread().getId());}
    @Test
        public void test3() throws InterruptedException {
        Thread.sleep(1000);
        System.out.printf("Thread Id:%s%n",
                Thread.currentThread().getId());

    }

    }
