package com.pgw.qa.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.OrderIdPage;
import com.pgw.qa.pages.PaymentProcessPage;
import com.pgw.qa.util.TestUtil;

public class PaymentProcessPageTest extends TestBase {
	HomePage homePage;
	PaymentProcessPage paymentProcessPage;
	OrderIdPage orderIdPage;
	String sheetName = "PaymentPage";
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
	@DataProvider()
	public Object[][] getPGWTestData() throws Exception {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(groups = "FunctionalTest",dataProvider="getPGWTestData")
	public void paymentProcessTest(String cardNo,String cvv) {
		orderIdPage = paymentProcessPage.paymentProcess(cardNo, cvv);
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
