package com.onlytest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report extends Base implements ITestListener {
	    private static ExtentReports extent = createInstance();
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    private static ExtentReports createInstance() {
	        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
	        spark.config().setDocumentTitle("Automation Report");
	        spark.config().setReportName("Parallel Test Execution");
	        ExtentReports ext = new ExtentReports();
	        ext.attachReporter(spark);
	        return ext;
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
	        extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        extentTest.get().log(Status.PASS, "Test passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        extentTest.get().log(Status.FAIL, result.getThrowable());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        extentTest.get().log(Status.SKIP, "Test skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
	}

