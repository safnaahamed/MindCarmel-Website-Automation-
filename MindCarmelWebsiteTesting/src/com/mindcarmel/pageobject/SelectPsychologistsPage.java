package com.mindcarmel.pageobject;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class SelectPsychologistsPage extends BaseTest {

	By psychnametab = By.xpath("//p[@class='psychologist-name']/a");
	By bookBtn = By.xpath("//a[text()='Book Now']");
	By onlineBtn = By.xpath("//label[text()='Online']");
	By activedate = By.xpath("//td[@data-event='click']/a");
	By timeslot = By.xpath("//ul[@class='slots-append']/li");
	By NoSlot = By.xpath("//ul[text()='No slots available']");
	By HomeTab = By.xpath("//li/a[text()='Home']");

	public SelectPsychologistsPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 30);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.oactions = new Actions(driver);
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir") + "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
	}

	public void clickOnBookSlot() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(onlineBtn)).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,100)");

			ArrayList<WebElement> activedate = new ArrayList<>(fwait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@data-event='click']"))));

			ArrayList<WebElement> activeweek = new ArrayList<>(wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//td[@data-event='click']/parent::tr"))));

			for (int x = 0; x < activeweek.size(); x++) {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("(//td[@data-event='click']/parent::tr)[" + (x + 1) + "]")));
				for (i = 1; i < activedate.size(); i++) {
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(timeslot)).isDisplayed();
						oactions.moveToElement(driver.findElement(timeslot)).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(timeslot)).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(bookBtn)).click();
						break;
						
					}catch (Exception e) {

						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("(//td[@data-event='click']/a)[" + (i + 1) + "]")))
								.click();
					}

					
					try {
						if (fwait.until(ExpectedConditions.visibilityOfElementLocated(NoSlot)).isEnabled()) {
							activedate = new ArrayList<>(driver.findElements(By.xpath("//td[@data-event='click']/a")));
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//td[@data-event='click']/a)[" + (i + 1) + "]")))
									.click();
							try {
							js.executeScript("window.scrollBy(0,-250)");
							driver.findElement(By.xpath("//a[@title='Next']")).click();
							wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
									By.xpath("(//td[@data-event='click']/parent::tr)[" + (x + 1) + "]")));
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("(//td[@data-event='click']/a)[" + (i + 1) + "]")))
										.click();
							}catch (Exception e) {
								wait.until(ExpectedConditions.visibilityOfElementLocated(timeslot)).isDisplayed();
								oactions.moveToElement(driver.findElement(timeslot)).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(timeslot)).click();
								wait.until(ExpectedConditions.visibilityOfElementLocated(bookBtn)).click();
								break;
							}
						}
						extentTest.pass("Clicked ont the activedate:: " + activedate.get(i));
					} catch (Exception e) {

					wait.until(ExpectedConditions.visibilityOfElementLocated(timeslot)).isDisplayed();
						oactions.moveToElement(driver.findElement(timeslot)).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(timeslot)).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(bookBtn)).click();
						break;
					}
				}

			}
			extentTest.pass("Clicked on the slot:: " + driver.findElement(timeslot).getText());
		} catch (Exception e) {
			extentTest.fail("Failed to select the slot to book the psychologist");

		}

	}
}