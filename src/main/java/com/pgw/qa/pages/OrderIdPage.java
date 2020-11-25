package com.pgw.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pgw.qa.base.TestBase;

public class OrderIdPage extends TestBase{

	
	//ObjectRepo:
	@FindBy(xpath="//h2[normalize-space()='Payment successfull!']")
	WebElement successMessage;
	
	@FindBy(xpath="/html[1]/body[1]/section[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/h3[1]/strong[1]")
	WebElement orderId;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement homeButton;
	
	//Initializing the PageObjects:
	public OrderIdPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public boolean validateSuccessMsg() {
		return successMessage.isDisplayed();
	}
	public boolean orderId() {
		return orderId.isDisplayed();
	}
	
	public HomePage clickHomeButton()
	{
		homeButton.click();
		return new HomePage();
	}
}
