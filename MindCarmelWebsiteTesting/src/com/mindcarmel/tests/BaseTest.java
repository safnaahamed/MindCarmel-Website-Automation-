package com.mindcarmel.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utils.MindCarmelExcelUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public static WebDriverWait wait;
	public static FluentWait<WebDriver> fwait;
	public static SoftAssert overify;
	public static ArrayList<WebElement> tabs;
	public static Actions oactions;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentreport;
	public static ExtentTest extentTest;
	public static MindCarmelExcelUtils mcutils;
	public static WebDriver driver;
	
	public static int i; 
	public static int j;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy/hh-mm");
	public static Date date = new Date(); 
	public static String randomNumber = sdf.format(date);
	public static String screenshotpath = System.getProperty("user.dir")+ "\\screenshots\\Screenshot_" + randomNumber + ".png";
	
	public static void calender() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		sdf.format(cal.getTime());
	}
	public static void captureScreenshot() {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
			Date date = new Date();
			String randomNumber = sdf.format(date);
			TakesScreenshot oshot = (TakesScreenshot) driver;
			File screenshot = oshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+ "\\screenshots\\Screenshot_" + randomNumber + ".png"));
			extentTest.addScreenCaptureFromPath(screenshotpath+".png");
		} catch (Exception e) {
			e.printStackTrace();
		} 
 
	}

	@BeforeSuite
	public void ExtentReportsClass() {

		sparkReporter = new ExtentSparkReporter(
		System.getProperty("user.dir") + "//reports//" + sdf.format(date) + " extent Report");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setDocumentTitle("MindCarmel Website testing Report");
		sparkReporter.config().setReportName("Automation Execution Report");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extentreport = new ExtentReports();
		extentreport.attachReporter(sparkReporter);
		extentreport.setSystemInfo("Author", "Safna");
		extentreport.setSystemInfo("OS", "Windows 11");
		extentreport.setSystemInfo("Browser", "Chrome");
		extentreport.setSystemInfo("Testing Website", "MindCarmel");

	}
	@Parameters("Browser")
	@BeforeTest
	public void beforeTest(String sbrowser) throws Exception {
		if(sbrowser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
		BaseTest.driver = new ChromeDriver();
		}else if(sbrowser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\driver\\msedgedriver.exe");
			BaseTest.driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get("http://rogersoftapps.com/test_env/jyotirmind/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterTest
	public void afterTest() {
		
		extentreport.flush();
		overify.assertAll();
		driver.close();
		driver.quit();
	}
	@AfterSuite
	public void closeTabs() {
		driver.quit();
	}
}
