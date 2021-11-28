package com.mindcarmel.pageobject;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

public class ContactsPage extends BaseTest {
	By name = By.id("name_contact");
	By email = By.id("email_contact");
	By Message = By.id("message_contact");
	By HomeTab = By.xpath("//li/a[text()='Home']");

	public ContactsPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");

	}

	public void verifyTheContacts() {
		String Name = mcutils.getCellData(1, "Name");
		String Email = mcutils.getCellData(1, "Email");
		String message = mcutils.getCellData(1, "Comment Box");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(Name);
			wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(Email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Message)).sendKeys(message);
			extentTest.pass("Successfully entered the Personal Information");
			extentTest.generateLog(Status.INFO,
					"Successfully entered the Personal Information:: " + Name + ";" + Email + ";" + message);
		} catch (Exception e) {
			extentTest.fail("Failed to enter the Personal Information");
		}
	}

	public void verifyContactUsDetails() {
		try {
			ArrayList<WebElement> helpcenter = new ArrayList<>(
					driver.findElements(By.xpath("//div[contains(@class,'row')]/div/h2")));

			for (j = 0; j < helpcenter.size(); j++) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//div[contains(@class,'row')]/div/h2)[" + (j + 1) + "]"))).isDisplayed();
				overify.assertEquals(driver
						.findElement(By.xpath("(//h2[text()='Help Center']/following-sibling::a)[" + (j + 1) + "]"))
						.getText(), mcutils.getCellData(j + 7, "Event Address").trim());

				overify.assertEquals(
						driver.findElement(By.xpath("//h2[text()='Address']/following-sibling::div")).getText(),
						mcutils.getCellData(15, "Event Address"));
			}
			extentTest.pass("Printed the texts in the page successfully");

			driver.findElement(HomeTab).click();
		} catch (Exception e) {
			extentTest.fail("Could not print the texts in the page");
		}
	}

}
