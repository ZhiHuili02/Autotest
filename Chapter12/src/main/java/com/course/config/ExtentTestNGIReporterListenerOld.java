package com.course.config;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExtentTestNGIReporterListenerOld implements IReporter {
    // 报告生成路径以及文件名（和你原来保持一致）
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "index.html";

    private ExtentReports extent;

    /**
     * 【核心监听方法】
     * TestNG全部用例执行完毕后，自动调用此方法
     * 修复TestNG7+ 3个参数接口，解决@Override爆红问题
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // 初始化Extent报告
        init();

        boolean createSuiteNode = false;
        // 多个测试套件，就分开展示节点
        if (suites.size() > 1) {
            createSuiteNode = true;
        }

        // 遍历TestNG传过来的所有测试套件结果
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> resultMap = suite.getResults();
            if (resultMap.isEmpty()) {
                // 没有用例，直接结束不生成报告
                return;
            }

            ExtentTest suiteTest = null;
            // 遍历套件里所有测试上下文（测试类）
            for (ISuiteResult suiteResult : resultMap.values()) {
                ITestContext testContext = suiteResult.getTestContext();

                // 创建套件节点
                if (createSuiteNode) {
                    suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
                }

                // 处理成功用例
                handleTestResult(testContext.getPassedTests(), suiteTest, ITestResult.SUCCESS);
                // 处理失败用例（你截图里2个failed就是在这里处理）
                handleTestResult(testContext.getFailedTests(), suiteTest, ITestResult.FAILURE);
                // 处理跳过用例
                handleTestResult(testContext.getSkippedTests(), suiteTest, ITestResult.SKIP);
            }
        }

        // 生成最终html报告！必须调用！
        extent.flush();
    }

    /**
     * 初始化Extent报告配置（你原来代码里只调用没实现）
     */
    private void init() {
        // 创建报告文件夹
        File reportDir = new File(OUTPUT_FOLDER);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdirs();
        }

        // 创建html报告对象
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        // 修复离线报告css样式打不开、加载慢问题
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        htmlReporter.config().setDocumentTitle("接口自动化测试报告");
        htmlReporter.config().setReportName("TestNG自动化测试执行结果");

        // 绑定报告对象
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // 报告首页自定义信息
        extent.setSystemInfo("测试环境", "测试环境");
        extent.setSystemInfo("测试人员", "自动化测试");
        extent.setSystemInfo("测试框架", "TestNG + ExtentReports");
    }

    /**
     * 统一处理用例结果（成功/失败/跳过）
     * @param results 用例结果集合
     * @param suiteTest 套件节点
     * @param status 用例状态
     */
    private void handleTestResult(IResultMap results, ExtentTest suiteTest, int status) {
        for (ITestResult result : results.getAllResults()){
            ExtentTest methodTest;
            // 多个套件就挂载到套件下，单个套件直接创建
            if (suiteTest != null) {
                methodTest = suiteTest.createNode(result.getMethod().getMethodName());
            } else {
                methodTest = extent.createTest(result.getMethod().getMethodName());
            }

            // 给用例打标签：类名+方法名
            methodTest.assignCategory(result.getTestClass().getRealClass().getSimpleName());
            methodTest.info("测试方法：" + result.getMethod().getMethodName());

            // 根据状态设置结果：成功/失败/跳过
            switch (status) {
                case ITestResult.SUCCESS:
                    methodTest.pass("用例执行成功");
                    break;
                case ITestResult.FAILURE:
                    // 失败自动打印异常堆栈！你截图2个失败的原因会自动显示在这里
                    methodTest.fail("用例执行失败，异常原因：" + result.getThrowable());
                    break;
                case ITestResult.SKIP:
                    methodTest.skip("用例跳过执行");
                    break;
            }
        }
    }
}
