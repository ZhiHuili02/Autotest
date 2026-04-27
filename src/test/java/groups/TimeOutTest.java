package groups;

import org.testng.annotations.Test;

public class TimeOutTest {//超时测试
    @Test(timeOut=3000)//单位是毫秒,timeOut是期望值,不符合报错
    public void testSuccess() throws InterruptedException {
        Thread.sleep(1000);
            System.out.println("Success");}
    @Test(timeOut=1000)
    public void testFailed() throws InterruptedException {
            Thread.sleep(2000);
            System.out.println("Success");

        }}

