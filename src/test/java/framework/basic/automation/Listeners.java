package framework.basic.automation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterTool;
import resources.Utilities;

public class Listeners extends Utilities implements ITestListener {

	ExtentReports extent = ExtentReporterTool.getReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> testObjects = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		testObjects.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		testObjects.get().log(Status.PASS, "Test Case passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		testObjects.get().fail(result.getThrowable());
		WebDriver driver;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getSuperclass().getField("driver")
					.get(result.getInstance());
			testObjects.get().addScreenCaptureFromPath(takeScreenshot(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException | IllegalArgumentException | SecurityException | IllegalAccessException
				| NoSuchFieldException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		testObjects.get().log(Status.SKIP, "Test Case skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
