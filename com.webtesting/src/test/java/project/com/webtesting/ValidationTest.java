package project.com.webtesting;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobjects.Homepage;
import pageobjects.Loginpage;
import resources.base;

public class ValidationTest extends base {
	
	@BeforeTest
	public void setUP() throws IOException {
		
		driver= initializeDriver();
		
	}
	
	@BeforeClass
	public void setURL() {
		
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test(dataProvider="getLoginCredentials", priority=0)
	public void validateLogin(String username, String password) {
		Loginpage lp = new Loginpage(driver);
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLoginButton().submit();
		Assert.assertEquals("https://www.linkedin.com/feed/", driver.getCurrentUrl());
		
		
	}
	
	
	@Test(priority=1)
	public void searchContext() throws InterruptedException {
		Homepage hp = new Homepage(driver);
		
		Actions a = new Actions(driver);
		a.moveToElement(hp.getSearchBar()).click().build().perform();
		a.moveToElement(hp.getSearchBar()).sendKeys("Amit Tiwari").build().perform();
		a.moveToElement(hp.getSearchIcon()).click().build().perform();
		
		WebDriverWait d = new WebDriverWait(driver, 40);
		d.until(ExpectedConditions.visibilityOf(hp.getSearchResultList()));
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Amit Tiwari"));
				
	}
	
	
	@Test(priority=2)
	public void addConnection() {
		
		Homepage hp = new Homepage(driver);
		WebElement element = hp.getSearchResultList();
		
		int count = element.findElements(By.tagName("button")).size();
		System.out.println(count);
		
		
		for (int i = 0; i<count; i++) {
			String link =  element.findElements(By.tagName("button")).get(i).getAttribute("aria-label");
			System.out.println(link);
			if(link.contains("Connect")) {
				
				WebElement newElement = element.findElements(By.tagName("button")).get(0);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", newElement);
				
			}	
		}
	}
	
	
	@Test(priority=3)
	public void validateNoteSend() {
		Homepage hp = new Homepage(driver);
		hp.getAddNoteButton().click();
		hp.getNoteTextArea().sendKeys(prop.getProperty("NoteMessage"));
		//hp.getSendInvitationButton().click();
		hp.getCancelInvitationButton().click();
	
				
	}
	
	@DataProvider
	public Object[][] getLoginCredentials(){
		
		Object[][] data = new Object[1][2];
		
		data[0][0] = prop.getProperty("Username");
		data[0][1] = prop.getProperty("Password");
		
		return data;
	}
	
	
	
	@AfterTest
	public void tearDown() {
		//driver.close();
		
	}

}
