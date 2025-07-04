package com.crm.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LeadsPage extends TestBase {
	
	@FindBy (xpath = "//span[@class = 'fas fa-ellipsis-v']")
	WebElement addField;
	
	@FindBy (xpath = "//a[@data-name = 'assignedUser'][@class= 'add-filter']")
	////ul[@class = 'dropdown-menu pull-right filter-list']//a[@data-name='assignedUser']
	WebElement assignedUserFilterOption;
	
	@FindBy (xpath = "//div[@class='selectize-input items full has-options has-items']")
	WebElement conditionDropdown;
	
	@FindBy (xpath = "//div[@class='selectize-input items has-options full has-items']")
	WebElement conditionDropdown2;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='is']")
	WebElement isOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isEmpty']")
	WebElement isEmptyOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isNotEmpty']")
	WebElement isNotEmptyOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isNot']")
	WebElement isNotOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isOneOf']")
	WebElement anyOfOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isNotOneOf']")
	WebElement noneOfOption;
	
	@FindBy (xpath = "//div[@class='selectize-dropdown-content']//child::div[@data-value='isFromTeams']")
	WebElement isFromTeamsOption;
	
	@FindBy (xpath = "//a[@data-action='applyFilters']")
	WebElement applyFilterBtn;
	
	@FindBy (xpath = "//input[@data-name='assignedUserName']")
	WebElement assignedNameTextbox;
	
	@FindBy (xpath = "//button[@data-action='selectLink']")
	WebElement selectAssignedUserBtn;
	
	@FindBy (xpath = "//button[text()='Cancel']")
	WebElement cancelUserSelectBtn;
	
	@FindBy (xpath = "//a[text()='Jack Adams'][@class='link text-warning']")
	WebElement assignedUserLink;
	
	@FindBy (xpath = "//div[@class='filter filter-assignedUser']")            //a[@class='remove-filter pull-right']//i[@class='fas fa-times']
	WebElement removeFilterBtn;
	
	@FindBy (xpath = "//a[@class='remove-filter pull-right']//i[@class='fas fa-times']")
	WebElement crossIcon;
	
	@FindBy (xpath = "//a[@data-action = 'applyFilters']//span[text()='Reset']")
	WebElement resetBtn;
	
	public LeadsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectLeadsCheckbox(String name) {
		WebElement selectLeadCheckbox = driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@data-name='name']"
				+ "//preceding-sibling::td[@data-name ='r-checkbox']//input[@type = 'checkbox']"));
		selectLeadCheckbox.click();
		return selectLeadCheckbox.isSelected();
	}
	
	public void filterLeads(String AssignUser) { //apply filter method
		addField.click();
		
		assignedUserFilterOption.click();
		conditionDropdown.click();
		isEmptyOption.click();
		applyFilterBtn.click();
		
		conditionDropdown2.click();
		isOption.click();
		selectAssignedUserBtn.click();
		
		WebElement assignedUser = driver.findElement(By.xpath("//a[text()='"+AssignUser+"'][@class='link text-warning']"));
		assignedUser.click();
		applyFilterBtn.click();			
	}
	
	public String checkFilteredValueInTextBox() {
		return assignedNameTextbox.getAttribute("value");
	}
	
	public List<String> checkFilteredResults(){	//assert filter method
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(TestUtil.ELEMENT_VISIBLE_WAIT));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@data-name='assignedUser']//a"))); //wait for element to be visible
		List<String> leadname = new ArrayList<String>();
		List<WebElement> filteredNameElements = driver.findElements(By.xpath("//td[@data-name='assignedUser']//a"));
		//td[@data-name='assignedUser']//a[@title='Jack Adams' ]
		for (WebElement name : filteredNameElements) {
			leadname.add(name.getText().trim());
		}
		return leadname;
	}
	
	public void checkRemoveFilter() {                       //remove filter method
		Actions action = new Actions(driver);
		action.moveToElement(removeFilterBtn).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));     // mouse hover and then wait until element is visible
		wait.until(ExpectedConditions.elementToBeClickable(crossIcon));
		crossIcon.click();		
		
//		removeFilterBtn.click();
		resetBtn.click();
	}
	
	public List<String> checkFilterRemoved(){               // remove filter assert method
		
		TestUtil.presenceWait("//td[@data-name='assignedUser']//a");
		
		List<String> unfilteredName = new ArrayList<String>();
		
		List<WebElement> unfilteredNameElements = driver.findElements(By.xpath("//td[@data-name='assignedUser']//a"));
		for (WebElement name : unfilteredNameElements) {
			unfilteredName.add(name.getText().trim());
		}
		return unfilteredName;
	}
	
}
