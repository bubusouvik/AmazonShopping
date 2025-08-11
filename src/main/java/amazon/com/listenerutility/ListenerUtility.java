package amazon.com.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import amazon.com.fileutility.JavaUtility;
import amazon.com.webdriverutility.UtilityClassObject;

public class ListenerUtility implements ITestListener, ISuiteListener {

	public ExtentSparkReporter reporter;
	public ExtentReports reports;
	public ExtentTest test;
	JavaUtility jutility = new JavaUtility();

	public void onStart(ISuite res) {

		reporter = new ExtentSparkReporter("./ExtentReports/report.html");
		reporter.config().setDocumentTitle("Amazon Reports");
		reporter.config().setReportName("Report File");
		reporter.config().setTheme(Theme.STANDARD);
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("OS", "Windows-11");
		reports.setSystemInfo("BROWSER", "Chrome");
	}

	public void onTestStart(ITestResult res) {

		String testName = res.getMethod().getMethodName();
		test = reports.createTest(testName);
		UtilityClassObject.setStest(test);
		test.log(Status.INFO, " Test is started ==> " + testName);

	}

	public void onTestFailure(ITestResult res) {

		String testName = res.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getSdriver();

		// extent report attach screenshot
		
		String srcfile = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(srcfile, testName+" "+jutility.getSystemDate());

		// file added in screenshots folder
		File srcplace = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/" + " " + testName + " " + jutility.getSystemDate() + ".png");
		try {
			FileHandler.copy(srcplace, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.log(Status.FAIL, "Test is faled ==> " + testName);

	}

	public void onTestSuccess(ITestResult res) {
		String testName = res.getMethod().getMethodName();
		test.log(Status.PASS, "Test is passed ==> " + testName);
	}

	public void onTestFinish(ITestResult res) {
		String testName = res.getMethod().getMethodName();
		test.log(Status.INFO, "Test is finished ==> " + testName);
	}

	public void onFinish(ISuite res) {
		reports.flush();
		test.log(Status.INFO, reports.getReport() + " Reports saved");
	}

}
