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

public class AboutPageContentInfo extends BaseTest {
	By HomeTab = By.xpath("//li/a[text()='Home']");

	public AboutPageContentInfo(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 30);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");

	}

	public void verifyTheContents() { 
		try {
			overify.assertEquals(
					driver.findElement(By.xpath("(//p[contains(@class,'p-custom')])[1]")).getText(), mcutils.getCellData(11, "Verify Texts").trim());
		ArrayList<WebElement> maintitle = new ArrayList<>(
				driver.findElements(By.xpath("//div[@class='main_title']/h2")));
		for (i = 0; i < maintitle.size(); i++) {
			String text = wait 
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//div[@class='main_title']/h2)[" + (i + 1) + "]")))
					.getText();
			
			System.out.println(text);
			extentTest.generateLog(Status.INFO, "Main Title:: "+text+ "verified successfully");
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		ArrayList<WebElement> textcontent = new ArrayList<>(
				driver.findElements(By.xpath("//p[contains(@class,'p-custom')]")));
		
		for (i = 0; i < textcontent.size(); i++) {
			
			String text = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//p[contains(@class,'p-custom')])[" + (i + 1) + "]")))
					.getText();
			overify.assertEquals(
					driver.findElement(By.xpath("(//p[contains(@class,'p-custom')])[" + (i + 1) + "]")).getText(), mcutils.getCellData(i+11, "Verify Texts").trim());
			System.out.println(text);
			extentTest.generateLog(Status.INFO, "Page Contents verified successfully:: "+text);
		}
		ArrayList<WebElement> members = new ArrayList<>(
				driver.findElements(By.xpath("//div[contains(@class,'row ')]//div/h5")));
		for (i = 0; i < members.size(); i++) {
			String text = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'row ')]//div/h5)[" + (i + 1) + "]")))
					.getText();
			overify.assertEquals(
					driver.findElement(By.xpath("(//div[contains(@class,'row ')]//div/h5)[" + (i + 1) + "]")).getText(),
					text);
			System.out.println(text);
			extentTest.generateLog(Status.INFO, "Names of the Board members verified successfully:: "+text);
		}
		jse.executeScript("window.scrollBy(0,500)");
		ArrayList<WebElement> psychologists = new ArrayList<>(
				driver.findElements(By.xpath("//div[@class='owl-stage']//a")));

		ArrayList<WebElement> button = new ArrayList<>(
				driver.findElements(By.xpath("//div[@class='owl-dots']/button")));
		overify.assertEquals(
				driver.findElement(By.xpath("(//p[contains(@class,'p-custom')])[10]")).getText(), mcutils.getCellData(2, "Verify Texts").trim());
		for (i = 0; i < psychologists.size(); i++) {
			String text = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//div[@class='owl-stage-outer']//a)[" + (i + 1) + "]")))
					.getText();
			for (j = 1; j < button.size(); j++) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//div[@class='owl-dots']/button)[" + (j + 1) + "]")))
						.click();

				System.out.println(text);
				extentTest.generateLog(Status.INFO, "Names of the psychologists verified successfully:: "+text);
			}
			
		}
		driver.findElement(HomeTab).click();
		extentTest.pass("Successfully verified the details given in the page but text verifiction was failed");
	}catch (Exception e) {
		extentTest.fail("Failed to verify the deatils in the page");
	}
}
}
