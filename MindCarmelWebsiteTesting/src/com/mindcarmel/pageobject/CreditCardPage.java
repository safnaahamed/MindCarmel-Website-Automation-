package com.mindcarmel.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mindcarmel.tests.BaseTest;

public class CreditCardPage extends BaseTest {
	
	By card = By.xpath("//span[text()='Cards']");
	
	public CreditCardPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
	}
	
	public void clickOnCard() {
		try {
			oactions.moveToElement(driver.findElement(card)).build().perform();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(card)).click();
			extentTest.pass("Successfully clicked on the card image");
		}catch (Exception e) {
			extentTest.fail("Failed to click on the card");
		}
		
	}
} 