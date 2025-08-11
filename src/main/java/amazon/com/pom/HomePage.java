package amazon.com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchbox;

	@FindBy(id = "nav-search-submit-button")
	private WebElement searchbtn;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchBox() {
		return searchbox;
	}

	public WebElement getSearchBtn() {
		return searchbtn;
	}

}
