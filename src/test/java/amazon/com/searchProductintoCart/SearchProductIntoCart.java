package amazon.com.searchProductintoCart;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import amazon.com.baseclass.AmazonBaseclass;
import amazon.com.fileutility.JavaUtility;
import amazon.com.genericutility.PropertyFile;
import amazon.com.pom.HomePage;
import amazon.com.pom.SearchProductPage;
import amazon.com.webdriverutility.UtilityClassObject;

@Listeners(amazon.com.listenerutility.ListenerUtility.class)
public class SearchProductIntoCart extends AmazonBaseclass {

	JavaUtility javautility = new JavaUtility();

	@Test
	public void searchproductwithcart() throws InterruptedException, IOException {
		try {
			WebElement btn = driver.findElement(By.xpath("//button[.='Continue shopping']"));
			if (btn.isDisplayed()) {
				btn.click();

			}
		} catch (Exception e) {
			System.out.println("Button is not exist");
		}
		HomePage homepage = new HomePage(driver);
		// selective product to search
		UtilityClassObject.getStest().log(Status.INFO, javautility.getSystemDate() + " Selective product to search");
		homepage.getSearchBox().sendKeys("Samsung s23");
		homepage.getSearchBtn().click();

		// get product from property file

		PropertyFile pfile = new PropertyFile();
		String productName = pfile.getDataFromPropertyFile("productname");

		// navigate to search page list;
		UtilityClassObject.getStest().log(Status.INFO, javautility.getSystemDate() + " Navigate to search page list");
		SearchProductPage searchpage = new SearchProductPage(driver);
		searchpage.selectProduct(productName, driver);
		Thread.sleep(1000);

		// add to cart button click

		UtilityClassObject.getStest().log(Status.INFO, javautility.getSystemDate() + " Add to cart");
		// for assertErrorException taking screenshot
//		Assert.assertEquals("Home", "Login");
		searchpage.getAddToCart().click();

	}

}
