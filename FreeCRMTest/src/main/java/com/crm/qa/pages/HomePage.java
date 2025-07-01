package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy (xpath = "//span[text() = 'Contacts']")
	@CacheLookup
	WebElement ContactsLink;
	
	@FindBy (xpath = "//span[text() = 'Leads']")
	WebElement LeadsLink;
	
	@FindBy (xpath = "//span[text() = 'Opportunities']")
	WebElement OpportunitiesLink;
	
	//Initializing the page object:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickContactsLink() {		
		ContactsLink.click();
		return new ContactsPage();
	}
	
	public LeadsPage clickLeadsLink() {
		LeadsLink.click();
		return new LeadsPage();
	}
	
	public OpportunitiesPage clickOpportunitiesLink() {
		OpportunitiesLink.click();
		return new OpportunitiesPage();
	}
	
	

}
