package project.com.webtesting;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		a.moveToElement(hp.getSearchBar()).sendKeys(Keys.ENTER).build().perform();
		//a.moveToElement(hp.getSearchIcon()).click().build().perform();
		
		WebDriverWait d = new WebDriverWait(driver, 40);
		d.until(ExpectedConditions.visibilityOf(hp.getSearchResultList()));
		Assert.assertTrue(driver.getTitle().contains("Amit Tiwari"));
				
	}
	
	
	@Test(priority=2)
	public void validateListCount() {
	
		Homepage hp= new Homepage(driver);
		
		//Scrolling down the page to let Ajax call load all the element in the current list
		JavascriptExecutor scrollDown = (JavascriptExecutor) driver;
		scrollDown.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		
		
		//hp.getAllyJumpButton().click();
		
		//Wait until all the elements in the list has been shown by waiting
		//for until the next button at the bottom of the page  becomes clickable
		WebDriverWait d = new WebDriverWait(driver, 40);
		d.until(ExpectedConditions.elementToBeClickable(hp.getNextButton()));

		
		//Asserting the number of name blocks in the list 
		int count = hp.getSearchResultName().size();		
		Assert.assertEquals(count, 10);
	
	
	}
	
	
	@Test(priority=3)
	public void validateConnection() throws InterruptedException {
		
		Homepage hp= new Homepage(driver);
		int count = hp.getSearchResultName().size();

		for (int i = 0; i<count; i++) {
			
			String result = hp.getSearchResultName().get(i).getText();
			
			//For clicking on connect button only
			if(result.contains("Connect")) {
				WebElement newElement = driver.findElements(By.tagName("button")).get(0);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", newElement);
				
			}
		}
		// validate the connect button is working properly by validating add note button is showing.
		Assert.assertTrue(hp.getAddNoteButton().isDisplayed());
	}
	
	
	@Test(priority=4)
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
		driver.close();
		
	}

}
