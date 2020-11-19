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
import com.pgw.qa.pages.PaymentProcessPage;
import com.pgw.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	HomePage homepage;
	PaymentProcessPage paymentprocesspage;
	String sheetName = "PaymentPage";
	public HomePageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		homepage = new HomePage();
	}
	@Test(priority=1)
	public void homePageTitleTest() {
		String title = homepage.validatePageTitle();
		Assert.assertEquals(title, "Guru99 Payment Gateway");
	}
	@Test(groups= "Regression",priority=2)
	public void appLogoTest() {
		boolean flag = homepage.validateAppLogo();
		Assert.assertTrue(flag);
	}
	@Test(groups= "Regression",priority=3)
	public void pgwLogoTest() {
		boolean flag = homepage.validatePGWLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority=4)
	public void productNameTest() {
		String productName = homepage.validateProductName();
		Assert.assertEquals(productName, "Mother Elephant With Babies Soft Toy");
	}
	@Test(priority=5)
	public void productCatalogueTest() {
		boolean flag = homepage.validateProductCatalogue();
		Assert.assertTrue(flag);
	}
	@Test(priority=6)
	public void productPriceTest() {
		String price = homepage.validateProductPrice();
		Assert.assertEquals(price, "Price: $20");
	}
	@Test(groups="SmokeTest")
	public void quantityTest() {
		String quantity=  homepage.validateQuantity();
		Assert.assertEquals(quantity,"5");
	}
	@Test(priority=8)
	public void buyButtonTest() {
		paymentprocesspage = homepage.validateBuyButton();
	}
	@DataProvider()
	public Object[][] getPGWTestData() throws Exception {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(dataProvider="getPGWTestData")
	public void validatePlaceOrder(String cardNo,String cvv) {
		homepage.placeOrder(cardNo, cvv);
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		//	driver.quit();
	}
}
