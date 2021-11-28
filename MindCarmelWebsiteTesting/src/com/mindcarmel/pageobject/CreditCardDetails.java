package com.mindcarmel.pageobject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class CreditCardDetails extends BaseTest {
	By cardname = By.id("cardName");
	By cardnumber = By.id("cardNumber");
	By cardexpiry = By.id("cardExpiry");
	By cvv = By.id("cardCVV");
	By mobile = By.id("mobile");
	By email = By.id("emailId");
	By payBtn = By.id("paybtn");
	By backarrowBtn = By.id("back_to_main");
	By backtomerchantBtn = By.id("cancel-transaction");
	By sendBtn = By.xpath("//button[text()='Send anyway']");
	By gobackBtn = By.xpath("//button[text()='Go Back']");
	
	
	public CreditCardDetails(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir") + "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
	}

	public void enterCardDetails() {  
		String Name = mcutils.getCellData(1, "Name");
		String Email = mcutils.getCellData(1, "Email");
		String Phone = mcutils.getCellData(1, "Phone");
		String cnumber = mcutils.getCellData(1, "Card Number");
		String CVV = mcutils.getCellData(1, "CVV");
		String expiry = mcutils.getCellData(1, "Expiry");
		try {
			driver.findElement(cardname).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cardname)).sendKeys(Name);
			driver.findElement(cardnumber).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cardnumber)).sendKeys(cnumber);
			driver.findElement(cardexpiry).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cardexpiry)).sendKeys(expiry);
			driver.findElement(cvv).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cvv)).sendKeys(CVV);
			driver.findElement(mobile).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(mobile)).sendKeys(Phone);
			driver.findElement(email).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(Email);
			driver.findElement(backarrowBtn).click();
			wait.until(ExpectedConditions.elementToBeClickable(backtomerchantBtn)).click();
			Alert oalert = driver.switchTo().alert();
			oalert.accept();
			wait.until(ExpectedConditions.elementToBeClickable(sendBtn)).click();
			extentTest.pass("Card Details Entered successfully");
		}catch (Exception e) {
			extentTest.fail("Failed to enter the card information");
		}
	}

}
