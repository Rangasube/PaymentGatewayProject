package com.pgw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.PaymentProcessPage;

public class HomePageTest extends TestBase{
	HomePage homepage;
	PaymentProcessPage paymentprocesspage;

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
	@Test(groups="IntegrationTest")
	public void validatePlaceOrder() {
		homepage.placeOrder();
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
	//	driver.quit();
	}
}
