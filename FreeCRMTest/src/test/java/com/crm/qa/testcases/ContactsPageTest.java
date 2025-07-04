package com.crm.qa.testcases;

import java.io.IOException;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	String sheetName = "contacts";
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		testUtil = new TestUtil(); 
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login();	
		
		contactsPage = homePage.clickContactsLink();
	}
		
	@Test (priority = 1)
	public void verifyContactsPageTitleTest() {
		String contactsPageTitle = contactsPage.verifyContactsPageTitle();
		Assert.assertEquals(contactsPageTitle, "Contacts", "Contacts Page Title not matched");
	}
	
//	@Test (priority = 2)
//	public void verifySelectSingleContactTest() {
//		boolean flag = contactsPage.selectsContacts("Frederick Devine");
//		Assert.assertTrue(flag);
//	}
	
	@Test (priority = 3)
	public void verifySelectMultipleContactsTest() {
		boolean flag1 = contactsPage.selectsContacts("Laura Mason");
		boolean flag2 = contactsPage.selectsContacts("Ines Dietrich");
		Assert.assertTrue(flag1);
		Assert.assertTrue(flag2);
		contactsPage.selectsContacts("Frederick Devine");
	}
	
	
//	@Test (priority = 4)
//	public void verifyAddingSingleContactTest() {
//		contactsPage.clickNewContactLink();
//		contactsPage.createNewContact( "Dr.","Single", "Check");
//	}
//	
//	@Test (priority = 5)
//	public void verifySingleContactAddedTest() {
//		String FrstName = "Single";
//		String Srname = "Check";
//		WebElement newCntact = driver.findElement(By.xpath("//a[text()='"+FrstName+" "+Srname+"']"));
//		Assert.assertTrue(newCntact.isDisplayed(), "Contact not Added");
//	}
	
	@DataProvider
	public Object[][] getContactTestData() {
		Object data[][] = testUtil.getContactTestData(sheetName);
		return data;
	}
		
	@Test (priority = 6, dataProvider = "getContactTestData")
	public void validateCreateandVerifyContactTest(String title, String Name, String Surname) {
		contactsPage.clickNewContactLink();
		contactsPage.createNewContact(title, Name, Surname);
		boolean isAdded = contactsPage.checkAllMultipleContactsAdded(Name, Surname);
		Assert.assertTrue(isAdded, "Contact not added");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
			TestUtil.takeScreenshotAtEndOfTest(result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}
}
