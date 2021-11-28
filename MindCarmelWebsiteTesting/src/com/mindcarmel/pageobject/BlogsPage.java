package com.mindcarmel.pageobject;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class BlogsPage extends BaseTest {

	By SearchTab = By.id("search_key");
	By SearchBtn = By.xpath("//input[@value='Search']");
	By readmorebtn = By.xpath("(//span[text()='Read More'])");
	By loadmoreBtn = By.xpath("//a[text()='Load More']");
	By HomeTab = By.xpath("//li/a[text()='Home']");

	public BlogsPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");

	}
	public void verifyTheBlogPage() {
		String comments = mcutils.getCellData(2, "Comment Box");
		try {
		captureScreenshot();
		wait.until(ExpectedConditions.visibilityOfElementLocated(SearchTab)).sendKeys(comments);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBtn)).click();
		driver.navigate().back();
		extentTest.pass("Verified the blogs page successfully");
	}catch (Exception e) {
		extentTest.fail("Failed to verify the blogs Page");
	}
	
}

	public void verifyThePosts() {
		try {
			
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,655)");
			
			wait.until(ExpectedConditions.elementToBeClickable(loadmoreBtn)).click();

			ArrayList<WebElement> readBtn = new ArrayList<>(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2/a"))));

				for(i=0; i<readBtn.size(); i++) {
					jse.executeScript("window.scrollBy(0,-655)");
				
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2/a)["+(i+1)+"]"))).click();
					driver.navigate().back();
				}

			wait.until(ExpectedConditions.visibilityOfElementLocated(HomeTab)).click();
			
			extentTest.pass("Successfully verified the Latest Blogs");
		}catch (Exception e) {
			extentTest.fail("Failed to verify the Latest Blog details");
		}
	
}
}