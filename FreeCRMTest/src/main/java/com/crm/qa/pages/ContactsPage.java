package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {
	
	@FindBy (xpath= "//span[text() = 'Create Contact']")
	WebElement createContact;
	
	@FindBy (xpath ="//input[@data-name='firstName']")
	WebElement fName;
	
	@FindBy (xpath ="//input[@data-name='lastName']")
	WebElement lName;
	
	@FindBy (xpath="//button[@data-name='save']")
	WebElement saveContactBtn;
	
	@FindBy (xpath = "//a[text()='Contacts']")
	WebElement backToContactLink;
	
	@FindBy (xpath = "//div[@class ='modal-content']//button[text() = 'Cancel']")
	WebElement cancelBtn;
	
//	@FindBy (xpath ="//div[@class='selectize-input items has-options full has-items']")
	@FindBy(xpath="//div[@data-name='name']//div[contains(@class,'selectize-input') and contains(@class,'has-items')]")
	WebElement salutation;

	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactsPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs("Contacts"));
		String title = driver.getTitle();
		return title;
	}
	
	public boolean selectsContacts(String name) {
		WebElement contactCheckbox = driver.findElement(By.xpath("//a[text() = '"+name+"']//parent::td[@data-name='name']"
				+ "//preceding-sibling::td[@data-name='r-checkbox']//input[@type='checkbox']"));
		contactCheckbox.click();
		return contactCheckbox.isSelected();
	}
	
	public void clickNewContactLink() {
		createContact.click();
	}
	
	public void clickBackToContactPage() {
		backToContactLink.click();
	}
	
	
	public void createNewContact(String title, String ftName, String Surname) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		wait.until(ExpectedConditions.visibilityOf(salutation));
		salutation.click();
		
		WebElement option = driver.findElement(By.xpath("//div[contains(@class,'selectize-dropdown')]//div[text()='" + title + "']"));
	    option.click();
		
//		Select select = new Select(salutation);
//		select.selectByVisibleText(title);
		
		fName.sendKeys(ftName);
		lName.sendKeys(Surname);
		saveContactBtn.click();
//		testUtil.switchToFrame();
//			cancelBtn.click();
		 wait.until(ExpectedConditions.invisibilityOf(saveContactBtn));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		wait.until(ExpectedConditions.visibilityOf(backToContactLink));
	}
	
	public boolean checkAllMultipleContactsAdded(String Name, String Surname) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		backToContactLink.click();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		WebElement contactAdded = driver.findElement(By.xpath("//a[text()='"+Name+" "+Surname+"']"));
		return contactAdded.isDisplayed();
	}
	
	
	
	
}
