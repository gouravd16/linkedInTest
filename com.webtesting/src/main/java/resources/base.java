package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {
	public static Properties prop;
	public static WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/configuration");
		
		prop = new Properties();
		
		prop.load(fis);
		
		String browsername = prop.getProperty("browser");
		
		if (browsername.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if (browsername.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/drivers/geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		return driver;
	}
	

}
