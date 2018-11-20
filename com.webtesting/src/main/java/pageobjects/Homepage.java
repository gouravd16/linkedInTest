package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
	
	
	public WebDriver driver;
	
	By searchBar = By.xpath("//*[@id=\'ember36\']/input");
	By searchIcon = By.xpath("//*[@id=\'nav-search-controls-wormhole\']/button");
	By searchResultList = By.xpath("//h3[@class='search-results__total t-14 t-black--light t-normal pl5 pt4 clear-both']//following-sibling::ul");	
	By skipToSearch = By.xpath("//*[@class='btn-close']");
	By addNoteButton = By.xpath("//div[@class='send-invite__actions']/child::button[1]");
	By noteTextArea = By.xpath("//*[@id=\'custom-message\']");
	By sendInvitationButton = By.xpath("//div[@class='send-invite__actions']/child::button[2]");
	By cancelInvitationButton = By.xpath("//div[@class='send-invite__actions']/child::button[1]");
	By searchResultName = By.xpath("//*[@class='search-result__wrapper']");
	By nextButton = By.className("next-text");
	By allyJumpButton = By.xpath("//ul[@id='a11y-jump-menu-options']/child::li[2]");
	
	
	//*[@id="custom-message"]
	public Homepage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public WebElement getSearchBar() {
		
		return driver.findElement(searchBar);
		
	}

	public WebElement getSearchIcon() {
		
		return driver.findElement(searchIcon);
		
	}
	
	
	public WebElement getSearchResultList() {
	
	return driver.findElement(searchResultList);
	
	}
	
	public WebElement getSkipToSearch() {
		
		return driver.findElement(skipToSearch);
		
	}
	
	public WebElement getAddNoteButton() {
		
		return driver.findElement(addNoteButton);
		
	}
	
	public WebElement getNoteTextArea() {
		
		return driver.findElement(noteTextArea);
		
	}
	
	public WebElement getSendInvitationButton() {
		
		return driver.findElement(sendInvitationButton);
		
	}
	
	public WebElement getCancelInvitationButton() {
		
		return driver.findElement(cancelInvitationButton);
		
	}
	
	public List<WebElement> getSearchResultName() {
		
		return driver.findElements(searchResultName);
		
	}
	
	public WebElement getNextButton() {
		
		return driver.findElement(nextButton);
		
	}
	
	public WebElement getAllyJumpButton() {
		
		return driver.findElement(allyJumpButton);
		
	}

}
