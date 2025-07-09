//package com.crm.qa.testcases;
//
//import java.util.List;
//
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.crm.qa.base.TestBase;
//import com.crm.qa.pages.ContactsPage;
//import com.crm.qa.pages.HomePage;
//import com.crm.qa.pages.LoginPage;
//import com.crm.qa.pages.OpportunitiesPage;
//import com.crm.qa.util.TestUtil;
//
//public class OpportunitiesPageTest extends TestBase{
//	
//	LoginPage loginPage;
//	HomePage homePage;
//	ContactsPage contactsPage;
//	TestUtil testUtil;
//	OpportunitiesPage opportunitiesPage;
//	
//	public OpportunitiesPageTest() {
//		super();
//	}
//	
//	@BeforeMethod
//	public void startUp() {
//		initialization();
//		loginPage = new LoginPage();
//		homePage = loginPage.login();
//		testUtil = new TestUtil();
//		opportunitiesPage = homePage.clickOpportunitiesLink();
//		opportunitiesPage = new OpportunitiesPage();
//	}
//	
////	@Test (priority = 1)
////	public void verifySearchFunctionalityTest() {
////		String searchText = opportunitiesPage.searchOpportunities("Check");
////		opportunitiesPage.checkFilteredResults(searchText);
////		
////		List<String> titles = opportunitiesPage.checkFilteredResults(searchText);
////		for (String name : titles) {
////			Assert.assertEquals(name.toLowerCase().contains(searchText.toLowerCase()), "search is incorrect");
////		}
////	}
//	
////	@Test (priority = 2)
////	public void verifyOpportunityBackandForthTest() {
////		opportunitiesPage.checkCreateOpportunityNavigation();
////	}
//	
//	@Test (priority = 3)
//	public void verifyCreateOpportunityTest() {
//		opportunitiesPage.createOpportunity();
//		String stage = opportunitiesPage.opportunityDetails("Test","25","08/08/2008");
//		System.out.println(stage);
//		opportunitiesPage.itemDetails("Blender", "30","25");
//		
//		opportunitiesPage.backToOpportunityPage();
//		
//	}
//	
//	
//	
//	@AfterMethod
//	public void packUp() {
////		driver.quit();
//	}
//	
//	
//
//}
