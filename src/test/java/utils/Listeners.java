package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listeners extends TestBase implements ITestListener {

    private static Logger log = LogManager.getLogger(Listeners.class);

    static ExtentReports extentReport;
    static ExtentSparkReporter sparkReporter;

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started !!");
        extentReport = new ExtentReports();
        File file = new File("target/ExtentReport/Extent_Report.html");
        sparkReporter = new ExtentSparkReporter(file);
        extentReport.attachReporter(sparkReporter);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName =  result.getMethod().getDescription();
        ExtentTest test = extentReport.createTest(testName);
        test.log(Status.PASS," Test Passed");
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(testName)).build());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getDescription();
        ExtentTest test = extentReport.createTest(testName);
        test.log(Status.FAIL," Test failed");
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(testName)).build());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }

}
