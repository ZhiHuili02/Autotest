package groups;

import org.testng.annotations.Test;

@Test(groups="stu")//全局变量写在类的上面
public class GroupsOnClass2 {
    public void Stu1(){
        System.out.println("GroupOnClass2中的stu1111运行");
    }
    public void Stu2(){
        System.out.println("GroupOnClass2中的stu2222运行");
    }
}
