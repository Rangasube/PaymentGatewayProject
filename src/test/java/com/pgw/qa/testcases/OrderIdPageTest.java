package com.pgw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.OrderIdPage;
import com.pgw.qa.pages.PaymentProcessPage;

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
	@Test(priority=1,enabled=false) //we can skip tests by parameterizing @Test as *enabled-false*
	public void successMsgTest() {
		boolean flag = orderIdPage.validateSuccessMsg();
		Assert.assertTrue(flag);
	}
	@Test(priority=2,enabled = false)
	public void orderIdVisibleTest() {
		boolean flag = orderIdPage.orderId();
		Assert.assertTrue(flag);
	}
	@Test(priority=3)
	public void verifyOrderIdTest() {
		boolean flag= orderIdPage.verifyOrderId();
		Assert.assertTrue(flag);
	}
	@Test(priority=4)
	public void backtoHome() {
		orderIdPage.clickHomeButton();
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
