package test.Flipkart;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import test.FlipKart.Element;
import test.FlipKart.Select;

public class TestListener extends Element implements ITestListener {
	  private static Logger Log = Logger.getLogger(TestListener.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Test attachments for Allure
	@Attachment(value = "{0}", type = "test/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println ("in On TestFailure method "+ getTestMethodName (iTestResult)+" failed");

		// Get driver from BaseTest and assigned to local webdriver variable 
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((Element) testClass).getDriver();

		// Allure screenshotRobot and saveTesting 
		if (driver instanceof WebDriver) {
		System.out.println ("screenshot capture for test case:" + getTestMethodName (iTestResult));
		saveScreenshotPNG(driver);
		}

		// save a log on allure 
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}