package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//page factory - Object repository of login page
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(id="btn-login")
	WebElement loginButton;

	@FindBy(xpath="//img[contains(@class, 'logo')]")
	WebElement crmLogo;
	
	
	//Initializing page objects:
	public LoginPage() {
		PageFactory.initElements(driver, this); // how to initialize page factory? by initElements we can initialize above elements
	}
		
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	public HomePage login() {
		loginButton.click();
		return new HomePage();
	}
}
