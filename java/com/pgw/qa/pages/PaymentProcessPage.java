package com.pgw.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.pgw.qa.base.TestBase;

public class PaymentProcessPage extends TestBase {

	@FindBy(xpath="//h2[normalize-space()='Payment Process']")
	WebElement ppTitle;
	
	@FindBy(xpath="//font[normalize-space()='Pay Ammount']")
	WebElement PaymentLabel;
	
	@FindBy(xpath="//font[normalize-space()='$100.00']")
	WebElement PriceTag;
	@FindBy(xpath="//input[@id='card_nmuber']")
	WebElement cardNum;
	@FindBy(xpath="//select[@id='month']")
	WebElement selectMonth;
	@FindBy(xpath="//select[@name='year']")
	WebElement selectYear;
	@FindBy(xpath="//input[@name='cvv_code']")
	WebElement cvvCode;
	@FindBy(xpath="//input[@name='submit']")
	WebElement payButton;

	
	public PaymentProcessPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public boolean validatePPTitle() {
		return ppTitle.isDisplayed();
	}
	public boolean validatePaymentLable() {
		return PaymentLabel.isDisplayed();
	}
	public boolean validatePriceTag() {
		return PriceTag.isDisplayed();
	}
	public OrderIdPage paymentProcess() {
	
		Select select1,select2;
		cardNum.sendKeys("1234567891234567");
		selectMonth.click();
		select1 = new Select(selectMonth);
		select1.selectByVisibleText("04");
		
		selectYear.click();
		select2 = new Select(selectYear);
		select2.selectByVisibleText("2026");
		
		cvvCode.sendKeys("201");
		payButton.click();
		
		return new OrderIdPage();
	}
}
