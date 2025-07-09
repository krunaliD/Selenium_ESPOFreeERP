package com.crm.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class OpportunitiesPage extends TestBase {
	
	@FindBy (xpath = "//input[@data-name='textFilter']")
	WebElement searchTextBox;
	
	@FindBy (xpath = "//button[@data-action='search']")
	WebElement searchBtn;
	
	@FindBy (xpath = "//a[@data-name='create']")
	WebElement createOppBtn;
	
	@FindBy (xpath = "//button[@data-name='cancel']")
	WebElement cancelBtn;
	
	@FindBy (xpath = "//a[text()='Opportunities']")
	WebElement opportunityLink;                                                              //going back to opportunity page
	
	@FindBy (xpath = "//div[@data-name='name']//input[@data-name='name']")
	WebElement nameTxtbox;                                                                 // name of opportunity textbox
	
	@FindBy (xpath = "//div[@data-name='amount']//input[@data-name='amount']")
	WebElement amountTxtbox;                                                              	
	
	@FindBy (xpath = "//div[@data-name='closeDate']//input[@data-name='closeDate']")
	WebElement closeDateTxtbox;
	
	@FindBy (xpath = "//button[@data-action='addItem']")
	WebElement addItemBtn;
	
	@FindBy (xpath = "//div[@class='input-group']//input[@data-name='name']")
	WebElement itemNameTxtbox;
	
	@FindBy (xpath = "//div[@data-name='stage'][@class='field']")
	WebElement stagePhaseTxtbox;
	
	@FindBy (xpath = "//div[@data-name='item-listPrice']//input[@data-name='listPrice']")
	WebElement listPriceTxtbox;
	
	@FindBy (xpath = "//div[@data-name='item-unitPrice']//input[@data-name='unitPrice']")
	WebElement unitPriceTxtbox;
	
	@FindBy (xpath = "//button[@data-name='save']")
	WebElement saveOppBtn;
	
	@FindBy (xpath = "//a[text()='Opportunities']")
	WebElement navigateBackLink;                                                                           // after saving navigating back to opportunity page
	
	
	public OpportunitiesPage(){
		PageFactory.initElements(driver,this);
	}
	
	public String searchOpportunities(String searchText) {
		
		searchTextBox.click();
		searchTextBox.sendKeys(searchText);
		
		searchBtn.click();
		
		return searchText;
	}
	
	public List<String> checkFilteredResults(String searchText) {
		List<WebElement> search = driver.findElements(By.xpath("//a[contains(normalize-space(text()), '"+searchText+" ')]"));
		
		if(!search.isEmpty()) {
		TestUtil.visibleWait(search);
		}

		List<String> searchResults = new ArrayList<String>();
				
		for (WebElement names : search) {
			searchResults.add(names.getText().trim());
		}
		return searchResults;
	}
	
	public void checkCreateOpportunityNavigation() {
		TestUtil.visibleWait(createOppBtn);
		createOppBtn.click();
		cancelBtn.click();
		
		TestUtil.visibleWait(createOppBtn);
		createOppBtn.click();
		opportunityLink.click();
		
		TestUtil.visibleWait(createOppBtn);
		createOppBtn.click();
		
	}
	
	public void createOpportunity() {
		TestUtil.visibleWait(createOppBtn);
		createOppBtn.click();
	}
	
	public String opportunityDetails(String OppName, String Amount, String closeDate) {
		nameTxtbox.sendKeys(OppName);
		
		TestUtil.visibleWait(stagePhaseTxtbox);
		String stageName= stagePhaseTxtbox.getText().trim();
		
		amountTxtbox.sendKeys(Amount);
		closeDateTxtbox.sendKeys(closeDate);
		
		
		return stageName;
		
	}
	
	public void itemDetails(String itemName, String listPrice, String unitPrice ) {
		addItemBtn.click();                                                    //adding item inside opportunity
		itemNameTxtbox.sendKeys(itemName);
		
//		System.out.println("Is displayed:" +listPriceTxtbox.isDisplayed());
		
//		TestUtil.visibleWait(listPriceTxtbox);	
		TestUtil.elementClickableWait(listPriceTxtbox);
//		System.out.println("is enabled:" +listPriceTxtbox.isEnabled());
		listPriceTxtbox.click();
		listPriceTxtbox.sendKeys(Keys.CONTROL+ "a");
		listPriceTxtbox.sendKeys(listPrice);
		
		unitPriceTxtbox.click();
		unitPriceTxtbox.sendKeys(Keys.CONTROL + "a");
		unitPriceTxtbox.sendKeys(unitPrice);
		saveOppBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(saveOppBtn));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(navigateBackLink));
		
	}
	
	public void backToOpportunityPage() {

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		navigateBackLink.click();

//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
//		WebElement opportunityAdded = driver.findElement(By.xpath(""));
//		return opportunityAdded.isDisplayed();
	}
	
}
