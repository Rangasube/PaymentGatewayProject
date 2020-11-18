package com.pgw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.OrderIdPage;
import com.pgw.qa.pages.PaymentProcessPage;

public class PaymentProcessPageTest extends TestBase {
	HomePage homePage;
	PaymentProcessPage paymentProcessPage;
	OrderIdPage orderIdPage;
	public PaymentProcessPageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		homePage = new HomePage();
		paymentProcessPage = homePage.validateBuyButton();
		orderIdPage = new OrderIdPage(); 
	}
	@Test
	public void ppTitleTest() {
		boolean flag= paymentProcessPage.validatePPTitle();
		Assert.assertTrue(flag);
	}
	@Test
	public void paymentLableTest() {
		boolean flag = paymentProcessPage.validatePaymentLable();
		Assert.assertTrue(flag);
	}
	@Test
	public void priceTagTest() {
		boolean flag = paymentProcessPage.validatePriceTag();
		Assert.assertTrue(flag);	
	}
	@Test(groups = "FunctionalTest")
	public void paymentProcessTest() {
		orderIdPage = paymentProcessPage.paymentProcess();
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
