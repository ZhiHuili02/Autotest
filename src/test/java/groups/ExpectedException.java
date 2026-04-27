package groups;

import org.testng.annotations.Test;

public class ExpectedException {
    /*什么时候会用用到异常测试
    在期望结果为某个异常,比如传入不合法参数,程序抛出异常,一个预期结果异常

    */
    @Test(expectedExceptions=RuntimeException.class)//预期失败
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }
    //这是一个成功的预期失败
    @Test(expectedExceptions=RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的异常测试");//在throw后无法表达
        throw new RuntimeException();


    }
}
