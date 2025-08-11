package amazon.com.webdriverutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class WebDriverUtility {
	
	public void timeForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void maximizeScreen(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	
}
