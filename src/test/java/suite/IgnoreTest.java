package suite;

import org.testng.annotations.Test;
public class IgnoreTest{
    @Test
    public void IgnoreTest1(){
        System.out.println("ignore1 执行!");
    }
    @Test(enabled = false)//enabled=false--不执行
    public void IgnoreTest2(){//enablded=true,运行
        System.out.println("ignore2 执行!");
        }
    @Test(enabled = true)//enabled=false--不执行
    public void IgnoreTest3(){
        System.out.println("ignore3 执行!");
    }
}
