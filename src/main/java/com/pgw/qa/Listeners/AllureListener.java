package com.pgw.qa.Listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pgw.qa.base.TestBase;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	//Text Attachments for Allure
	@Attachment(value= "Page screenshot",type="image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	//Text Attachments for Allure
	@Attachment(value= "{0}",type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	//HTML Attachments for Allure
	@Attachment(value= "{0}",type="text/html")
	public static String attachHtml(String html) {
		return html;
	}

	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onStart method "+iTestContext.getName());
	}
	

	public void onFinish(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinist method "+iTestContext.getName());
	}
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method "+getTestMethodName(iTestResult)+" start");
	}
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method "+getTestMethodName(iTestResult)+" succeed");
	}
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method "+getTestMethodName(iTestResult)+" failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = TestBase.getDriver();
		//Allure ScreenShotRobot and SaveTestLog
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot captured for TestCase:"+getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		//save a log on allure
		saveTextLog(getTestMethodName(iTestResult)+"failed and Screenshot taken!");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
