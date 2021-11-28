package com.mindcarmel.pageobject;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class InstitutesPage extends BaseTest {
	By ContactUs = By.xpath("//h3[text()='Contact Us']");
	By name = By.id("name_detail_contact");
	By email = By.name("email_detail_contact");
	By phone = By.name("telephone_detail_contact");
	By Message = By.name("message_detail");
	By HomeTab = By.xpath("//li/a[text()='Home']");
	public InstitutesPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.oactions = new Actions(driver);
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
	}
	public void clickOnInstitutes() {
		String Name = mcutils.getCellData(1, "Name");
		String Email = mcutils.getCellData(1, "Email");
		String Phone = mcutils.getCellData(1, "Phone");
		String message = mcutils.getCellData(1, "Comment Box");
		
		try { 
		ArrayList<WebElement>institutes = new ArrayList<>(driver.findElements(By.xpath("//div[@class='post_info']//h2")));
		
		overify.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'main_title')]/p")).getText(), mcutils.getCellData(6, "Verify Texts"));
	
		for(i=0;i<institutes.size();i++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='View More'])["+(i+1)+"]"))).click();
			overify.assertEquals(driver.findElement(By.xpath("(//div[@class='row']//p)[1]")).getText(), mcutils.getCellData(i+7, "Verify Texts").trim());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Courses']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Programs']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(ContactUs)).isDisplayed();
			wait.until(ExpectedConditions.visibilityOfElementLocated(name)).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(Name);
			driver.findElement(email).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(Email);
			driver.findElement(phone).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(phone)).sendKeys(Phone);
			driver.findElement(Message).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Message)).sendKeys(message);
			driver.navigate().back();
			
			extentTest.generateLog(Status.INFO, "Successfully entered the Personal Information:: "+Name+ ";" +Email+ ";" +Phone+ ";" +message);
		}
		driver.findElement(HomeTab).click();
		extentTest.pass("Successfully entered the Personal Information");
	}catch (Exception e) {
		extentTest.fail("Failed to enter the Personal Information");
		e.printStackTrace();
	}
	}
}
