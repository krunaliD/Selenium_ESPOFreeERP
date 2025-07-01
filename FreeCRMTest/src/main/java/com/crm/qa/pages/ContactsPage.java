package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy (xpath= "//span[text() = 'Create Contact']")
	WebElement createContact;
	
	@FindBy (xpath ="//input[@data-name='firstName']")
	WebElement fName;
	
	@FindBy (xpath ="//input[@data-name='lastName']")
	WebElement lName;
	
	@FindBy (xpath="//button[@data-name='save']")
	WebElement saveContactBtn;
	
//	@FindBy (xpath ="//div[@class='selectize-input items has-options full has-items']")
	@FindBy(xpath="//div[@data-name='name']//div[contains(@class,'selectize-input') and contains(@class,'has-items')]")
	WebElement salutation;

	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactsPageTitle() {
		return driver.getTitle();
	}
	
	public boolean selectsContacts(String name) {
		WebElement contactCheckbox = driver.findElement(By.xpath("//a[text() = '"+name+"']//parent::td[@data-name='name']"
				+ "//preceding-sibling::td[@data-name='r-checkbox']//input[@type='checkbox']"));
		contactCheckbox.click();
		return contactCheckbox.isSelected();
	}
	
	public void clickNewContact() {
		createContact.click();
	}
	
	public void createNewContact(String title, String ftName, String Surname) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(salutation));
		salutation.click();
		
		WebElement option = driver.findElement(By.xpath("//div[contains(@class,'selectize-dropdown')]//div[text()='" + title + "']"));
	    option.click();
		
//		Select select = new Select(salutation);
//		select.selectByVisibleText(title);
		
		fName.sendKeys(ftName);
		lName.sendKeys(Surname);
		saveContactBtn.click();
	}
	
	
	
	
}
