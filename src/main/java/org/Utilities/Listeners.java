package org.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

    public class Listeners extends TestListenerAdapter {
        public ExtentSparkReporter htmlReporter;
        public ExtentReports extent;
        public ExtentTest test;
        public void onStart(ITestContext testContext) {
            htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "./src/main/resources/extentReport/index.html");
            extent=new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Name","PitPeo");
            extent.setSystemInfo("Environment","https://www.fitpeo.com/");
            extent.setSystemInfo("User","Vrushbah Umale");
            htmlReporter.config().setDocumentTitle("Automation Extent Report");
            htmlReporter.config().setReportName(" Test Result ");
            htmlReporter.config().setTheme(Theme.STANDARD);
        }
        public void onTestSuccess(ITestResult tr)    {
            test=extent.createTest(tr.getName());
            test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
            test.pass("Test passed");
        }
        public void onTestFailure(ITestResult tr)    {
            test=extent.createTest(tr.getName());
            test.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
            test.fail(tr.getThrowable());

        }
        public void onTestSkipped(ITestResult tr)    {
            test=extent.createTest(tr.getName());
            test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
            test.skip("Test skipped");
        }
        public void onFinish(ITestContext testContext)    {
            test.info("Test completed");
            extent.flush();
        }
    }
