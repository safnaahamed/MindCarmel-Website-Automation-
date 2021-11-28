package com.mindcarmel.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class PaymentPage extends BaseTest {
	
	By name = By.id("client_name");
	By email = By.name("client_email");
	By phone = By.name("client_phone");
	By age = By.name("client_age");
	By gendertype = By.name("client_gender");
	By postalcode = By.name("client_postalcode");
	By address = By.name("client_address");
	By commentbox = By.name("client_expectation");
	By payBtn = By.xpath("//a[text()='Pay Now']");
	By BanktransBtn = By.xpath("//a[text()='UPI/Bank Transfer']");
	By transactionid  = By.name("transaction_id");
	By paymentmethodUPI = By.id("payment_method_UPI");
	By paymentmethodbank = By.id("payment_method_Bank");
	By UPI = By.id("nav-home-tab");
	By banktransfer = By.id("nav-profile-tab");
	By banktransferBtn = By.id("payment_method_Bank");
	By submitBtn = By.xpath("//a[text()='Submit']");
	By proceedBtn = By.xpath("//button[text()='Yes, Proceed']");
	By cancelBtn = By.xpath("//button[text()='Cancel']"); 
	By sendBtn = By.xpath("//button[text()='Send anyway']");
	By psychologisttab =By.xpath("//li/a[text()='Psychologists']");
	
	public PaymentPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
	}
	
	public void personalInfo() {
		String Name = mcutils.getCellData(1, "Name"); 
		String Email = mcutils.getCellData(1, "Email");
		String Phone = mcutils.getCellData(1, "Phone");
		String Age = mcutils.getCellData(1, "Age");
		String zipcode = mcutils.getCellData(1, "Postal Code");
		String Address = mcutils.getCellData(1, "Address");
		String message = mcutils.getCellData(1, "Comment Box");
		
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(name)).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(Name);
			extentTest.pass("Name entered successfully:: "+Name);
			driver.findElement(email).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(Email);
			extentTest.pass("Email address entered successfully:: "+Email);
			driver.findElement(phone).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(phone)).sendKeys(Phone);
			extentTest.pass("Phone number entered successfully:: "+Phone);
			driver.findElement(age).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(age)).sendKeys(Age);
			extentTest.pass("Age entered successfully "+Age);
			wait.until(ExpectedConditions.visibilityOfElementLocated(gendertype)).click();
			Select gender = new Select(fwait.until(ExpectedConditions.elementToBeClickable(gendertype)));
//			Thread.sleep(2000);
			gender.selectByVisibleText(mcutils.getCellData(1, "Gender"));;
			extentTest.pass("Clicked on the correct gender successfully");
			driver.findElement(postalcode).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(postalcode)).sendKeys(zipcode);
			extentTest.pass("Postal Code entered successfully:: "+zipcode);
			driver.findElement(address).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(address)).sendKeys(Address);
			extentTest.pass("Address entered successfully:: "+Address);
			driver.findElement(commentbox).clear();
			fwait.until(ExpectedConditions.visibilityOfElementLocated(commentbox)).sendKeys(message);
			extentTest.pass("Comments entered successfully:: "+message);
			
		}catch (Exception e) {
			extentTest.fail("Failed to enter the personal details ");
			e.printStackTrace();
		}
		
	}
	public void clickOnPayment() {
		String transaction = mcutils.getCellData(1, "Transaction Id");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(payBtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
			CreditCardPage ocard = new CreditCardPage(driver);
			ocard.clickOnCard();
			CreditCardDetails odetails = new CreditCardDetails(driver);
			odetails.enterCardDetails();
			personalInfo();
			wait.until(ExpectedConditions.elementToBeClickable(BanktransBtn)).click();
			String text = driver.findElement(By.xpath("//div[@class='other-payment-area']/p")).getText();
			System.out.println(text);			
			wait.until(ExpectedConditions.visibilityOfElementLocated(transactionid)).sendKeys(transaction);
			wait.until(ExpectedConditions.elementToBeClickable(paymentmethodUPI)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(UPI)).isDisplayed();
			wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(paymentmethodbank)).click();
;
			wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).click(); 	
			
			
			extentTest.pass("Payment completed successfully");
		}catch (Exception e) {
			extentTest.fail("Failed to enter the payment details");
			e.printStackTrace();
		}
			
		}

}
