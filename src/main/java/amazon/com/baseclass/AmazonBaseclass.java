package amazon.com.baseclass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import amazon.com.genericutility.PropertyFile;
import amazon.com.webdriverutility.UtilityClassObject;
import amazon.com.webdriverutility.WebDriverUtility;

public class AmazonBaseclass {

	ExtentSparkReporter reporter;
	public WebDriver driver = null;
	PropertyFile file = new PropertyFile();
	WebDriverUtility webdriver = new WebDriverUtility();

	@BeforeSuite
	public void beforeSuit() {
//		System.out.println("before suit");
		reporter = new ExtentSparkReporter("./ExtentReports/report.html");
		reporter.config().setDocumentTitle("Amazon Reports");
		reporter.config().setReportName("Error File");
		reporter.config().setTheme(Theme.DARK);

	}

	@BeforeClass
	public void launchBrowser() throws IOException {
		String browser = file.getDataFromPropertyFile("browser");
		String url = file.getDataFromPropertyFile("url");
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		UtilityClassObject.setSdriver(driver);
		webdriver.maximizeScreen(driver);
		webdriver.timeForPageLoad(driver);
		driver.get(url);

		

	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
