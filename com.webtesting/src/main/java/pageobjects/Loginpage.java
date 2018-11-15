package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Loginpage {
	
	public WebDriver driver;
	
	By username = By.xpath("//*[@id=\'login-email\']");
	By password = By.xpath("//*[@id=\'login-password\']");
	By loginButton = By.xpath("//*[@id=\'login-submit\']");
	
	
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public WebElement getUsername() {
		
		return driver.findElement(username);
		
	}

	public WebElement getPassword() {
		
		return driver.findElement(password);
		
	}
	
	
	public WebElement getLoginButton() {
	
	return driver.findElement(loginButton);
	
	}
	

}
