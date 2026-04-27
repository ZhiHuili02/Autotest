package paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;



public class DataProvid {
    //testng第二节课
    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age) {
        System.out.println("name=" + name + ";age=" + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        Object[][] o = new Object[][]{
                {"zhangsan", 10},
                {"lisi", 20},
                {"wangwu", 30},
        };
        return o;
    }

    ;

    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1方法name=" + name + ";age=" + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2方法name=" + name + ";age=" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;//根据方法不同对result进行赋值
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"zhangsan", 20},
                    {"lisi", 40},
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"wangwu", 30},
                    {"zhaoliu", 60},
            };
        }
        return result;
    }
}

