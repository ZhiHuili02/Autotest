package suite;

import org.testng.*;

public class suitConfig implements ISuiteListener,ITestListener {
    @Override
    public void onStart(ISuite suite){
        System.out.println("Before Suite逻辑执行");
    }
    @Override
    public void onTestStart(ITestResult result){
        System.out.println("Before Test执行");}

    @Override
    public void onFinish(ISuite suite){
        System.out.println("After Suite逻辑执行");
    }
    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("after Test执行");}





}











