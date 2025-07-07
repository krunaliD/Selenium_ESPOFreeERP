package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 10;
	public static long ELEMENT_VISIBLE_WAIT = 20;
	
	public static String ContactTestData_sheet_Path = "C:\\Users\\kruna\\git\\Selenium_ESPOFreeERP\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public Object[][] getContactTestData(String sheetName){
		FileInputStream file = null;
		try {
			file = new FileInputStream(ContactTestData_sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
			try {
				book = WorkbookFactory.create(file);
			} catch (EncryptedDocumentException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum()+ " | "+sheet.getRow(0).getLastCellNum());
		for (int i= 0; i< sheet.getLastRowNum(); i++) {
			for(int j =0; j< sheet.getRow(0).getLastCellNum();j++) {
				data [i][j] = sheet.getRow(i+1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest(String testName) throws IOException{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File (currentDir + "\\screenshots\\" +testName + "_"+ System.currentTimeMillis()+".png"));
	}
	
	public static void presenceWait(String Xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
	}
	
	public static void visibleWait(WebElement element) {                                 // by webElement
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf((element)));
	}
	
	public static void elementClickableWait(WebElement element) {                          //webElement
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void visibleWait(By locator) {                                     // by locator
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void elementClickableWait(By locator) {                       // by locator
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

}
