package groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups="server")//添加group属性
    public void test1(){
        System.out.println("这是服务端组测试方法一");

    }
    @Test(groups="server")//添加group属性
    public void test2(){
        System.out.println("这是服务端组测试方法二");

    }
    @Test(groups="client")//添加group属性
    public void test3(){
        System.out.println("这是客户端组测试方法三");

    }
    @Test(groups="client")//添加group属性
    public void test4(){
        System.out.println("这是客户端组测试方法四");}
    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("这是服务端运行之前运行的方法");
    }
    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("这是服务端运行之后运行的方法");
    }//多个测试方法归到一个组,统一进行属性
    @BeforeGroups("client")
        public void beforeGroupOnClient(){
        System.out.println("这是服务端运行之前运行的方法");
    }
    @AfterGroups("client")
    public void afterGroupOnClient(){
        System.out.println("这是服务端运行之后运行的方法");
    }//多个测试方法归到一个组,统一进行属性
}

