package com.pgw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pgw.qa.Listeners.AllureListener;
import com.pgw.qa.base.TestBase;
import com.pgw.qa.pages.HomePage;
import com.pgw.qa.pages.PaymentProcessPage;
import com.pgw.qa.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners({AllureListener.class})
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
	@Test(priority=1,description="HomePage Title Test")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify HomePage Title is correct or not")
	@Description("Test Description : Verifying HomePage Title as it displays corret or not")
	public void homePageTitleTest() {
		String title = homepage.validatePageTitle();
		Assert.assertEquals(title, "Guru99 Payment Gateway");
	}
	@Test(priority=2,description="Application Logo Test")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify App Logo Test")
	@Description("Test Description : Verifying Guru99 app logo as it is present or not")
	public void appLogoTest() {
		boolean flag = homepage.validateAppLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority=3,description="PaymentGateWay Logos Test")
	@Severity(SeverityLevel.NORMAL)
	@Story("PaymentGateway Logo should be displayed")
	@Description("Test Description : Verifying PaymentGateWay Logo is displayed or not")
	public void pgwLogoTest() {
		boolean flag = homepage.validatePGWLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority=4,description="Product Name and Catalogue Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Product Name should be displayed")
	@Description("Test Description : Verifying a Product name is displayed or not")
	public void productNameTest() {
		String productName = homepage.validateProductName();
		Assert.assertEquals(productName, "Mother Elephant With Babies Soft Toy");
	}
	@Test(priority=5,description="Product Catalogue Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Product Catalogue should be displayed")
	@Description("Test Description : Verifying a Product Catalogue is displayed or not")
	public void productCatalogueTest() {
		boolean flag = homepage.validateProductCatalogue();
		Assert.assertTrue(flag);
	}
	@Test(priority=6,description="Product price display Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Product Price should be displayed")
	@Description("Test Description : Verifying a product price tag is displayed or not")
	public void productPriceTest() {
		String price = homepage.validateProductPrice();
		Assert.assertEquals(price, "Price: $20");
	}
	@Test(priority=7,description="Quantity DropDown Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Quantity has to be selected from the DropDown list")
	@Description("Test Description : Verifying  Qty DropDown list")
	public void quantityTest() {
		String quantity=  homepage.validateQuantity();
		Assert.assertEquals(quantity,"5");
	}
	@Test(priority=8,description="Buy Button Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Buy Button should be workable")
	@Description("Test Description : by clicking on Buy button you should be navigate to PaymentProcess Page")
	public void buyButtonTest() {
		paymentprocesspage = homepage.validateBuyButton();
	}
	@DataProvider()
	public Object[][] getPGWTestData() throws Exception {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(dataProvider="getPGWTestData",groups="IntegrationTest",description="verifying a placing order feature")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Placing Order An Elephane Toy ")
	@Description("Test Description : Placing order an elephant toy and fetching its order id on successful payment process")
	public void validatePlaceOrder(String qty,String cardNo,String cvv) {
		homepage.placeOrder(qty,cardNo, cvv );
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
