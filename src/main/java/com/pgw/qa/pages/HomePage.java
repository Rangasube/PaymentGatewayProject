package com.pgw.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.pgw.qa.base.TestBase;

public class HomePage extends TestBase{
	PaymentProcessPage paymentProcessPage = new PaymentProcessPage();
	OrderIdPage orderIdPage = new OrderIdPage();
	//PageFactory - ObjectRepository
	@FindBy(xpath="//img[@alt='Guru99 Demo Sites']")
	WebElement Guru99Logo;

	@FindBy(xpath="//a[@class='logo']")
	WebElement PGWLogo;

	@FindBy(xpath="//h2[normalize-space()='Mother Elephant With Babies Soft Toy']")
	WebElement productName;

	@FindBy(xpath="//img[@src='images/Toy.jpg']")
	WebElement productCatalogue;

	@FindBy(xpath="//h3[normalize-space()='Price: $20']")
	WebElement productPrice;

	@FindBy(xpath="//select[@name='quantity']")
	WebElement quantityDropdown;

	@FindBy(xpath="//input[@value='Buy Now']")
	WebElement buyButton;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	//Actions:
	public String validatePageTitle() {
		return driver.getTitle();
	}
	public boolean validateAppLogo() {
		return Guru99Logo.isDisplayed();
	}
	public boolean validatePGWLogo() {
		return PGWLogo.isDisplayed();
	}
	public String validateProductName() {
		return productName.getText();
	}
	public boolean validateProductCatalogue() {
		return productCatalogue.isDisplayed();
	}
	public String validateProductPrice() {
		return productPrice.getText();
	}
	public String validateQuantity() {
		String quantity;
		Select select;
		quantityDropdown.click();
		select = new Select(quantityDropdown);
		select.selectByVisibleText("5");
		quantity=select.getFirstSelectedOption().getText();
		System.out.println("Quantity selected : "+quantity);
		return quantity;

	}
	public PaymentProcessPage validateBuyButton() {
		buyButton.click();
		return new PaymentProcessPage();
	}
	public void placeOrder(String qty,String cardNo,String cvv) {


		Select select;
		quantityDropdown.click();
		select = new Select(quantityDropdown);
		String value = qty;
		select.selectByValue(value);
		String quantity=select.getFirstSelectedOption().getText();
		System.out.println("Quantity selected : "+quantity);

		buyButton.click();

		String totalPrice = paymentProcessPage.PriceTag.getText();
		System.out.println();
		System.out.println("------------------------------< Total Price : "+totalPrice+" >------------------------------");
		System.out.println();
		paymentProcessPage.paymentProcess(cardNo, cvv);
		String msg = orderIdPage.successMessage.getText();
		System.out.println();
		System.out.println("<<<<< "+msg+" >>>>>");
		System.out.println();
		String orderid = orderIdPage.orderId.getText();
		System.out.println("Your Order is placed... & Your OrderId is : "+orderid);

	}
}
