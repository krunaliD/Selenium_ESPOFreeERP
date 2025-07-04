package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LeadsPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.OpportunitiesPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	LeadsPage leadsPage;
	OpportunitiesPage opportunitiesPage;
	
	public HomePageTest () {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		leadsPage = new LeadsPage();
		opportunitiesPage = new OpportunitiesPage();
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		
		String homepageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homepageTitle, "EspoCRM Demo", "Homepage title not matched");
	}
	
	@Test(priority = 2)
	public void verifyContactLinkTest() {
//		testUtil.switchToFrame();
		contactsPage = homepage.clickContactsLink();
	}
	
	@Test (priority = 3)
	public void verifyLeadsLinkTest() {
		leadsPage = homepage.clickLeadsLink();
	}
	
	@Test (priority = 4)
	public void verifyOpportunityTest() {
		opportunitiesPage = homepage.clickOpportunitiesLink();
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
