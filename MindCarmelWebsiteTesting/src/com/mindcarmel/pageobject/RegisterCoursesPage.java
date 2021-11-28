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

import com.aventstack.extentreports.Status;
import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class RegisterCoursesPage extends BaseTest {
	JavascriptExecutor js;
	By registertext = By.xpath("//h3[text()='Register For Course']");
	By registerBtn = By.xpath("//a[text()='Register Now']");

	public RegisterCoursesPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		this.js = (JavascriptExecutor) driver;
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
 
	} 

	public void clickOnCourses() {
		try {
			ArrayList<WebElement> courses = new ArrayList<>(
					driver.findElements(By.xpath("//div[@id='course-paginate']//h2")));

			js.executeScript("window.scrollBy(0,200)");
			
			for (i = 0; i < courses.size(); i++) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//a[text()='View More'])[" + (i + 1) + "]"))).click();
				ArrayList<WebElement> contactInfo = new ArrayList<>(
						driver.findElements(By.xpath("((//div[@class='row'])[4]//p)")));
			
				for (j = 0; j < contactInfo.size(); j++) {

					js.executeScript("window.scrollBy(0,200)");
					String text = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("((//div[@class='row'])[4]//p)[" + (j + 1) + "]")))
							.getText();
					System.out.println(text);
					overify.assertEquals(
							driver.findElement(By.xpath("((//div[@class='row'])[4]//p)[" + (j + 1) + "]")).getText(),
							mcutils.getCellData(j + 1, "Event Address").trim());
					extentTest.generateLog(Status.INFO, "Contact info printed successfully::" + text);
				}
				js.executeScript("window.scrollBy(0,-500)");
				try {
					if (driver.findElement(registertext).isDisplayed()) {
					
						wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
						captureScreenshot();
						extentTest.addScreenCaptureFromPath(screenshotpath);
						RegisterCoursesNewTabPage onew = new RegisterCoursesNewTabPage(driver);
						onew.openNewTab();
					}

				} catch (Exception e) {
					driver.navigate().back();
				}
			}

			driver.navigate().back();
			extentTest.pass("Clicked on all the courses successfully");
		} catch (Exception e) {
			extentTest.fail("Failed to click and verify the courses");
			e.printStackTrace();
		}

	}
}
