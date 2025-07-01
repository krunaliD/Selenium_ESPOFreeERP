package com.crm.qa.testcases;

import org.testng.Assert;
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
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
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
	
	@Test (priority = 2)
	public void verifySelectSingleContactTest() {
		boolean flag = contactsPage.selectsContacts("Frederick Devine");
		Assert.assertTrue(flag);
	}
	
	@Test (priority = 3)
	public void verifySelectMultipleContactsTest() {
		boolean flag1 = contactsPage.selectsContacts("Laura Mason");
		boolean flag2 = contactsPage.selectsContacts("Ines Dietrich");
		Assert.assertTrue(flag1);
		Assert.assertTrue(flag2);
		contactsPage.selectsContacts("Frederick Devine");
	}
	
//	@DataProvider
//	public void getContactTestData() {
//		
//	}
	
	
	
	@Test (priority = 4)
	public void validateCreateContactTest() {
		contactsPage.clickNewContact();
		contactsPage.createNewContact( "Dr.","Test", "Check");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
