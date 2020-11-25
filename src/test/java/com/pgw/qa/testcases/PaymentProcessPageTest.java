package com.pgw.qa.testcases;

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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("PaymentGateway")
@Feature("PaymentProcessPageTest")
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
	@Test(priority=1,description="PaymentPage Title Test")
	@Severity(SeverityLevel.MINOR)
	@Story("Paymentpage title should be displayed")
	@Description("Test Description : PaymentProcessPage title should be displayed at the top of the page")
	public void ppTitleTest() {
		boolean flag= paymentProcessPage.validatePPTitle();
		Assert.assertTrue(flag);
	}
	@Test(priority=2,description="PaymentLabel Test")
	@Severity(SeverityLevel.MINOR)
	@Story("PaymentLable should be displayed")
	@Description("Test Description : Payment Label should be displayed before the total price")
	public void paymentLableTest() {
		boolean flag = paymentProcessPage.validatePaymentLable();
		Assert.assertTrue(flag);
	}
	@Test(priority=3,description="validating payment process")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Price Tag should be visible")
	@Description("Test Description : Price Tage should be visible in Red font colour")
	public void priceTagTest() {
		boolean flag = paymentProcessPage.validatePriceTag();
		Assert.assertTrue(flag);	
	}
	@DataProvider()
	public Object[][] getPGWTestData() throws Exception {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=4,description="validating payment process" )
	@Severity(SeverityLevel.CRITICAL)
	@Story("Payment process should be successful with correct price of the Product and Quantity")
	@Description("Test Description : Payment process should be successful for a particular selected products based on the product quantity")
	public void paymentProcessTest(String qty,String cardNo,String cvv) {
		orderIdPage = paymentProcessPage.paymentProcess(cardNo, cvv);
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
