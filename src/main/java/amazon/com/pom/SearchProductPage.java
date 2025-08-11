package amazon.com.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import amazon.com.webdriverutility.WebDriverUtility;

public class SearchProductPage {

	WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'Samsung Galaxy S23 FE 5G (Graphite, ')]")
	private WebElement product;
	
	@FindBy(xpath = "(//input[@id='add-to-cart-button'])[2]")
	private WebElement addToCart;
	
	public SearchProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getProduct() {
		return product;
	}
	
	public WebElement getAddToCart() {
		return addToCart;
	}
	
	

	public void selectProduct(String productname, WebDriver driver) throws InterruptedException {
		WebDriverUtility webdriver = new WebDriverUtility();
		webdriver.timeForPageLoad(driver);
		List<WebElement> products = driver.findElements(By.xpath("//div[@role='listitem']"));
		for (WebElement product : products) {
			product.findElement(By.xpath("//span[.='" + productname + "']")).click();
			break;
		}
	}
}
