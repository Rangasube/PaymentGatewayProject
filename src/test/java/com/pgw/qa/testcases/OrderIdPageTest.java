package com.pgw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.OrderIdPage;
import com.pgw.qa.pages.PaymentProcessPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("PaymentGateway")
@Feature("OrderIdPage Test")
public class OrderIdPageTest extends TestBase{

	HomePage homePage;
	PaymentProcessPage paymentProcessPage;
	OrderIdPage orderIdPage;
	
	public OrderIdPageTest()
	{
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setUp(String cardNo,String cvv) {
		initialization();
		homePage= new HomePage();
		paymentProcessPage = homePage.validateBuyButton();
		orderIdPage = paymentProcessPage.paymentProcess(cardNo, cvv);
		
	}
	@Test(priority=1,description="Payment Success Message Test") //we can skip tests by parameterizing @Test as *enabled="false"*
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify Payment Successful Message On Successful Payment")
	@Description("Test Description : Verifying Payment Successful Message as it displays or not")
	public void successMsgTest() {
		boolean flag = orderIdPage.validateSuccessMsg();
		Assert.assertTrue(flag);
	}
	@Test(priority=2,description="OrderId visible Test")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify OrdeerId is displayed or not")
	@Description("Test Description : Verifying OrderId is displayed or not after succefully placing order")
	public void orderIdVisibleTest() {
		boolean flag = orderIdPage.orderId();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3, description="Check Home button in OrderId Page is not working or not")
	@Severity(SeverityLevel.MINOR)
	@Story("By clicking on Home button you should navigate to Home Page")
	@Description("Test Description : Home button would get you a HomePage by clicking it")
	public void backtoHome() {
		orderIdPage.clickHomeButton();
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
